package com.kkpc.rpa.web.util;

import com.kkpc.rpa.web.exception.MultiUserLoginException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kkpc.rpa.web.enums.ErrorMessage.RPA_EXE_NOT_FOUND_ERROR;

@Component
public class ExeRunner {

    private static final String MULTI_USER_LOGIN_MSG = "[ERROR] MULTI_USER_LOGIN";

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExeRunner.class);

    @Value("${rpa.exe.name}")
    private String exe_file;

    @Value("${rpa.exe.save.dir}")
    private String saveDir;

    public void runExe(int numBusinessNumbers) throws IOException, InterruptedException {
        Path exePath = Paths.get(saveDir, exe_file);
        File exeFile = exePath.toFile();

        log.info("ExeRunner - RPA EXE 실행 준비: {}", exeFile.getAbsolutePath());

        if (!exeFile.exists()) {
            log.error("ExeRunner - RPA EXE 파일을 찾을 수 없음: {}", exeFile.getAbsolutePath());
            throw new IOException(RPA_EXE_NOT_FOUND_ERROR.getMessage() + exeFile.getAbsolutePath());
        }

        ProcessBuilder processBuilder = new ProcessBuilder(exeFile.getAbsolutePath());
        processBuilder.directory(new File(saveDir));    // 프로세스가 동작하는 기준 디렉토리 지정
        processBuilder.redirectErrorStream(true);       // 정상 출력 + 오류 출력을 하나의 스트림으로

        Process process = processBuilder.start();
        AtomicBoolean isMultiUserLogined = new AtomicBoolean(false);

        // 출력 스트림을 별도 스레드에서 비동기 로깅
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "MS949"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.contains(MULTI_USER_LOGIN_MSG)) {
                        isMultiUserLogined.compareAndSet(false, true);
                    }
                    log.info("[RPA EXE OUTPUT] {}", line);
                }
            } catch (IOException e) {
                throw new RuntimeException("RPA EXE 출력 로깅 중 오류 발생", e);
            }
        });

        boolean finished = process.waitFor(5L * numBusinessNumbers, TimeUnit.MINUTES);  // 최대 작업시간 = (사업자 등록번호 개수) * 5분

        try {
            future.get();
        } catch (ExecutionException |  InterruptedException e) {
            log.info("ExeRunner - RPA 로그 처리 중 예외 발생", e);
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }

        if (finished) {
            int exitCode = process.exitValue();

            if (exitCode == 0) {
                log.info("ExeRunner - RPA EXE 실행 완료: {}", exeFile.getAbsolutePath());
            } else if (isMultiUserLogined.get()) {
                throw new MultiUserLoginException("중복 로그인이 감지되었습니다.");
            } else {
                throw new RuntimeException("RPA EXE가 비정상 종료되었습니다. 종료 코드: " + exitCode);
            }
        } else {
            log.error("ExeRunner - RPA EXE 실행이 시간 내 종료되지 않음");
        }
    }
}

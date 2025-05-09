package com.kkpc.rpa.web.queue;

import com.kkpc.rpa.web.exception.MultiUserLoginException;
import com.kkpc.rpa.web.model.UserInput;
import com.kkpc.rpa.web.util.ConfigFileWriter;
import com.kkpc.rpa.web.util.ExeRunner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@RequiredArgsConstructor
public class RequestQueueManager {
    
    private static final int MAX_RETRY_COUNT = 3;
    private static final long RETRY_DELAY_MILLIS = 3 * 60 * 1000;    // 3분

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RequestQueueManager.class);

    private final ConfigFileWriter configFileWriter;
    private final ExeRunner exeRunner;

    private final BlockingQueue<RequestTask> queue = new LinkedBlockingQueue<>();

    @Getter
    private volatile boolean workerFailed = false;

    @PostConstruct
    public void init() {
        log.info("RequestQueueManager - 워커 초기화 시작");
        startWorker();  // 서버 부팅시 worker 시작
    }

    public void startWorker() {
        Thread worker = new Thread(() -> {
            log.info("RequestQueueManager - 워커 스레드 시작");
            while (true) {
                RequestTask task = null;
                try {
                    task = queue.take();
                    processTask(task);
                } catch (MultiUserLoginException e) {
                    handleMultiUserLogin(task);
                } catch (Exception e) {
                    log.error("RequestQueueManager - 워커 처리 중 예외 발생", e);
                    workerFailed = true;
                    restartWorker();    // worker 죽으면 바로 재시작
                    break;              // 죽은 worker는 break하고 끝냄
                }
            }
        });

        worker.setDaemon(true); // 서버 종료시 같이 종료
        worker.start();
    }

    private void processTask(RequestTask task) throws Exception {
        UserInput userInput = task.getUserInput();
        int numBusinessNumbers = userInput.getBusinessNumbers().size();
        log.info("RequestQueueManager - 요청 수신: {}", task);

        long startTime = System.currentTimeMillis();

        configFileWriter.saveUserInputAsJson(userInput);
        exeRunner.runExe(numBusinessNumbers);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("RequestQueueManager - 요청 처리 완료: {} (소요시간: {} ms)", task, duration);
        log.info("RequestQueueManager - config 저장 및 EXE 실행 성공");
    }

    private void handleMultiUserLogin(RequestTask task) {
        log.info("MultiUserLoginException 발생 - 3분간 대기 후 재시도");

        if (freeze()) return;

        if (task != null && task.getRetryCount() < MAX_RETRY_COUNT) {
            RequestTask retried = task.incrementRetry();
            addRequest(retried);
        }
    }

    private static boolean freeze() {
        try {
            Thread.sleep(RETRY_DELAY_MILLIS);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            log.error("대기 중 인터럽트 발생", interruptedException);
            return true;
        }
        return false;
    }

    private void restartWorker() {
        log.info("RequestQueueManager - 워커가 예외로 종료되어 재시작 중");
        workerFailed = false;
        startWorker();
    }

    public void addRequest(RequestTask task) {
        boolean success = queue.offer(task);
        if (success) {
            log.info("RequestQueueManager - 요청 큐에 추가됨: {}", task);
        } else {
            log.error("RequestQueueManager - 요청 큐 추가 실패: {}", task);
        }
    }
}

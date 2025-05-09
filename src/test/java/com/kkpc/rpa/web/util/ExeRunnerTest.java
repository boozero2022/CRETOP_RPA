package com.kkpc.rpa.web.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

class ExeRunnerTest {

    private ExeRunner exeRunner;
    private Path tempDir;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IOException, IllegalAccessException {
        exeRunner = new ExeRunner();

        tempDir = Files.createTempDirectory("testExeRunner");


        Field saveDirField = ExeRunner.class.getDeclaredField("saveDir");
        saveDirField.setAccessible(true);
        saveDirField.set(exeRunner, tempDir.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        if (tempDir != null) {
            Files.walk(tempDir)
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

//    @Test
//    void testRunExe_success() throws IOException, InterruptedException {
//        // Windows 시스템에 기본으로 있는 실행 파일 사용
//        File notepad = new File(System.getenv("WINDIR") + "\\System32\\notepad.exe");
//
//        if (!notepad.exists()) {
//            fail("notepad.exe가 존재하지 않습니다. 테스트 불가능");
//        }
//
//        // 임시 saveDir 안에 notepad.exe 복사
//        File exeFile = new File(tempDir.toFile(), "rpa.exe");
//        Files.copy(notepad.toPath(), exeFile.toPath());
//        exeRunner.runExe();
//    }
//
//    @Test
//     void testRunExe_exeFileNotFound() throws IOException, InterruptedException {
//        Assertions.assertThrows(IOException.class, () -> exeRunner.runExe());
//    }
}
package com.kkpc.rpa.web.util;

import com.kkpc.rpa.web.model.UserInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigFileWriterTest {

    private ConfigFileWriter configFileWriter;
    private static final String TEST_SAVE_DIR = "test-data";
    private static final String CONFIG_FILE = "config.json";

    @BeforeEach
    public void setUp() {
        configFileWriter = new ConfigFileWriter();
        // set private field 'saveDir'
        ReflectionTestUtils.setField(configFileWriter, "saveDir", TEST_SAVE_DIR);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Path path = Path.of(TEST_SAVE_DIR, CONFIG_FILE);
        Files.deleteIfExists(path);
        Files.deleteIfExists(Path.of(TEST_SAVE_DIR));
    }

    @Test
    public void testSaveUserInputAsJson() throws Exception {
        // given
        UserInput input = new UserInput();
        input.setBusinessNumbers(Arrays.asList("123-45-67890", "456-78-12345"));
        input.setEmailAddresses(Arrays.asList("user1@kkpc.com", "user2@kkpc.com"));

        // when
        configFileWriter.saveUserInputAsJson(input);

        // then
        File file = Path.of(TEST_SAVE_DIR, CONFIG_FILE).toFile();
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}

package com.kkpc.rpa.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkpc.rpa.web.model.UserInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ConfigFileWriter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ConfigFileWriter.class);

    @Value("${config.name}")
    private String fileName;

    @Value("${config.save.dir}")
    private String saveDir;

    public void saveUserInputAsJson(UserInput userInput) throws IOException {
        log.info("ConfigFileWriter - 사용자 입력값을 JSON 파일로 저장 시작");

        ObjectMapper mapper = new ObjectMapper();
        Path dirPath = Paths.get(saveDir);

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
            log.debug("ConfigFileWriter - 저장 디렉터리 생성: {}", saveDir);
        }

        File file = new File(saveDir, fileName);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, userInput);

        log.info("ConfigFileWriter - 사용자 입력값 JSON 저장 완료: {}", file.getAbsolutePath());
    }
}

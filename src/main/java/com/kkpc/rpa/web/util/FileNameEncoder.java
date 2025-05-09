package com.kkpc.rpa.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileNameEncoder {

    private static final Logger log = LoggerFactory.getLogger(FileNameEncoder.class);

    public static String encode(String fileName) {
        log.info("FileNameEncoder - 파일명 인코딩 시도: {}", fileName);

        try {
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            log.debug("FileNameEncoder - 인코딩 완료: {}", encodedFileName);
            return encodedFileName;
        } catch (Exception e) {
            log.error("FileNameEncoder - 파일명 인코딩 실패: {}", fileName, e);
            throw e;
        }
    }
}

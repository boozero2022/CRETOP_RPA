package com.kkpc.rpa.web.controller;

import com.kkpc.rpa.web.service.ManualService;
import com.kkpc.rpa.web.util.FileNameEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.kkpc.rpa.web.enums.ErrorMessage.UNPROCESSABLE_FILE_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manual")
public class ManualDownloadController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ManualDownloadController.class);

    private final ManualService manualService;

    @GetMapping
    public ResponseEntity<?> downloadManual() throws IOException {
        String fileName = "CRETOP_RPA_사용자_메뉴얼.pptx";
        log.info("GET /manual - 메뉴얼 다운로드 요청 수신: {}", fileName);

        Resource manual = manualService.getManual(fileName);

        if (!manual.exists() || !manual.isReadable()) {
            log.error("GET /manual - 파일 없음 또는 읽기 불가: {}", fileName);

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, UNPROCESSABLE_FILE_ERROR.getMessage());
        }

        InputStreamResource inputStreamResource = new InputStreamResource(manual.getInputStream());
        String encodedFileName = FileNameEncoder.encode(fileName);

        log.debug("GET /manual - 다운로드 시작: {}, size={} bytes", encodedFileName, manual.contentLength());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(manual.contentLength())
                .body(inputStreamResource);

    }
}

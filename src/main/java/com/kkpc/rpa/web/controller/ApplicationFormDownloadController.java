package com.kkpc.rpa.web.controller;

import com.kkpc.rpa.web.service.ApplicationFormService;
import com.kkpc.rpa.web.util.FileNameEncoder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/application-form")
public class ApplicationFormDownloadController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationFormDownloadController.class);

    private final ApplicationFormService applicationFormService;

    @GetMapping
    public ResponseEntity<?> downloadApplicationForm() throws IOException {
        String fileName = "[IT전략실] RPA 신규 과제 신청서.xlsx";
        log.info("GET /application-form - 신청서 다운로드 요청 수신: {}", fileName);

        Resource applicationForm = applicationFormService.getApplicationForm(fileName);

        if (!applicationForm.exists() || !applicationForm.isReadable()) {
            log.error("GET /application-form - 파일 없음 또는 읽기 불가: {}", fileName);

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, UNPROCESSABLE_FILE_ERROR.getMessage());
        }

        InputStreamResource inputStreamResource = new InputStreamResource(applicationForm.getInputStream());
        String encodedFileName = FileNameEncoder.encode(fileName);

        log.debug("GET /application-form - 다운로드 시작: {}, size={} bytes", encodedFileName, applicationForm.contentLength());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(applicationForm.contentLength())
                .body(inputStreamResource);

    }
}

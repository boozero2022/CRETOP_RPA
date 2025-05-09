package com.kkpc.rpa.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationFormService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApplicationFormService.class);

    private final ResourceLoader resourceLoader;

    public Resource getApplicationForm(String fileName) {
        log.info("ApplicationFormService - 신청서 파일 로드 시도: {}", fileName);

        Resource resource = resourceLoader.getResource("classpath:excel/" + fileName);

        try {
            log.debug("ApplicationFormService - 파일 리소스 로드 완료: {}", resource.getURI());
        } catch (Exception e) {
            log.warn("ApplicationFormService - 리소스 경로 확인 실패: {}", fileName, e);
        }

        return resource;
    }
}

package com.kkpc.rpa.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationFormServiceTest {

    @Test
    void 리소스_정상_반환() {
        // given
        String fileName = "[IT전략실] RPA 신규 과제 신청서.xlsx";
        String resourcePath = "classpath:excel/" + fileName;

        ResourceLoader mockLoader = mock(ResourceLoader.class);
        ClassPathResource expectedResource = new ClassPathResource("excel/" + fileName);
        when(mockLoader.getResource(resourcePath)).thenReturn(expectedResource);

        ApplicationFormService service = new ApplicationFormService(mockLoader);

        // when
        var result = service.getApplicationForm(fileName);

        // then
        assertTrue(result.exists(), "리소스가 존재하지 않습니다.");
        assertEquals(expectedResource, result);
    }
}

package com.kkpc.rpa.web.service;

import com.kkpc.rpa.web.model.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }

    @Test
    public void 빈_입력_테스트() {
        UserInput input = new UserInput();

        assertFalse(validationService.isValid(input));
    }

    @Test
    public void 사업자_등록번호_개수_10개_초과_테스트() {
        UserInput input = new UserInput();
        for (int i = 0; i < 20; i++) {
            input.setBusinessNumbers(List.of("123-45-67890"));
        }

        assertFalse(validationService.isValid(input));
    }

    @Test
    public void testValidInput() {
        UserInput input = new UserInput();
        input.setBusinessNumbers(List.of("123-45-67890"));
        input.setEmailAddresses(List.of("test@kkpc.com"));

        assertTrue(validationService.isValid(input));
    }

    @Test
    public void testInvalidBusinessNumber() {
        UserInput input = new UserInput();
        input.setBusinessNumbers(List.of("12-345-67890")); // 틀린 포맷
        input.setEmailAddresses(List.of("test@kkpc.com"));

        assertFalse(validationService.isValid(input));
    }

    @Test
    public void testInvalidEmail() {
        UserInput input = new UserInput();
        input.setBusinessNumbers(List.of("123-45-67890"));
        input.setEmailAddresses(List.of("test@gmail.com")); // 다른 도메인

        assertFalse(validationService.isValid(input));
    }
}

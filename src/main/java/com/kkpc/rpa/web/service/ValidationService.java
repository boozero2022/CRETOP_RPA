package com.kkpc.rpa.web.service;

import com.kkpc.rpa.web.model.UserInput;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ValidationService.class);

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    private static final Pattern BUSINESS_NUMBER_PATTERN = Pattern.compile("^\\d{3}-\\d{2}-\\d{5}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@kkpc\\.com$");

    public boolean isValid(UserInput userInput) {
        log.info("ValidationService - 사용자 입력값 검증 시작");

        if (userInput == null || userInput.getBusinessNumbers() == null || userInput.getEmailAddresses() == null) {
            log.warn("ValidationService - 입력값이 null입니다.");
            return false;
        }

        if (!isValidSize(userInput.getBusinessNumbers().size())) {
            log.warn("ValidationService - 사업자 등록번호 입력 개수 오류: {}개 입력", userInput.getBusinessNumbers().size());
            return false;
        }

        if (!isValidSize(userInput.getEmailAddresses().size())) {
            log.warn("ValidationService - 이메일 입력 개수 오류: {}개 입력", userInput.getEmailAddresses().size());
            return false;
        }

        boolean allBusinessNumbersValid = userInput.getBusinessNumbers().stream()
                .allMatch(bn -> {
                    boolean valid = isValidBusinessNumber(bn);
                    if (!valid) log.warn("ValidationService - 사업자등록번호 포맷 오류: {}", bn);
                    return valid;
                });

        boolean allEmailsValid = userInput.getEmailAddresses().stream()
                .allMatch(email -> {
                    boolean valid = isValidEmail(email);
                    if (!valid) log.warn("ValidationService - 이메일 포맷 오류: {}", email);
                    return valid;
                });

        boolean result = allBusinessNumbersValid && allEmailsValid;

        if (result) {
            log.debug("ValidationService - 사용자 입력값 검증 통과");
        }

        return result;
    }

    private boolean isValidSize(int size) {
        return MIN_SIZE <= size && size <= MAX_SIZE;
    }

    private boolean isValidBusinessNumber(String number) {
        return BUSINESS_NUMBER_PATTERN.matcher(number).matches();
    }

    private boolean isValidEmail(String mail) {
        return EMAIL_PATTERN.matcher(mail).matches();
    }
}


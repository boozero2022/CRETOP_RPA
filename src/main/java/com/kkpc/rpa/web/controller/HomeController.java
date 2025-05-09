package com.kkpc.rpa.web.controller;

import com.kkpc.rpa.web.model.UserInput;
import com.kkpc.rpa.web.queue.RequestQueueManager;
import com.kkpc.rpa.web.queue.RequestTask;
import com.kkpc.rpa.web.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import static com.kkpc.rpa.web.enums.ErrorMessage.INVALID_INPUT_ERROR;
import static com.kkpc.rpa.web.enums.ErrorMessage.SAVE_INPUT_ERROR;


@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final ValidationService validationService;
    private final RequestQueueManager requestQueueManager;

    public HomeController(ValidationService validationService, RequestQueueManager requestQueueManager) {
        this.validationService = validationService;
        this.requestQueueManager = requestQueueManager;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        log.info("GET / - Home 화면 요청 처리 시작");
        model.addAttribute("userInput", new UserInput());
        return "home";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute UserInput userInput) {
        log.info("POST /submit - 사용자 입력 수신: {}", userInput);

        if (!validationService.isValid(userInput)) {
            log.warn("POST /submit - 입력값 검증 실패: {}", userInput);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_INPUT_ERROR.getMessage());
        }

        if (requestQueueManager.isWorkerFailed()) {
            log.error("POST /submit - RequestQueueManager 워커 실패 감지");

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SAVE_INPUT_ERROR.getMessage());
        }

        requestQueueManager.addRequest(new RequestTask(userInput));
        log.debug("POST /submit - 요청 큐 추가 완료: {}", userInput);

        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmation() {
        log.info("GET /confirmation - 확인 화면 요청 처리 시작");

        return "confirmation";
    }
}

package com.kkpc.rpa.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    INVALID_INPUT_ERROR("입력값이 유효하지 않습니다."),
    SAVE_INPUT_ERROR("입력값 저장 중 오류가 발생했습니다."),
    UNPROCESSABLE_FILE_ERROR("해당 파일을 찾을 수 없거나 처리할 수 없습니다."),
    FAILED_TO_CREATE_CONFIG_FILE_ERROR("config.json 파일 생성에 실패했습니다."),
    RPA_EXE_NOT_FOUND_ERROR("rpa.exe 파일이 존재하지 않습니다: ");

    private final String message;
}

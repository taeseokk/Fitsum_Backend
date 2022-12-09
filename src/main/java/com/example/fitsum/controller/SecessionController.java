package com.example.fitsum.controller;

import com.example.fitsum.model_response.CommonResult;
import com.example.fitsum.service.ResponseService;
import com.example.fitsum.service.SecessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "delete user", description = "회원 탈퇴")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SecessionController {

    private final ResponseService responseService;
    private final SecessionService secessionService;

    @PostMapping("/secession")
    @Operation(summary = "나의 일기장 리스트", description = "내가 작성한 일기 리스트의 정보를 가져옴.")
    public CommonResult secession() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 사용자 인증 후 꺼내오기(pagable이 페이지 단위로 불러오기는 효과적)
        secessionService.secession(userId);

        return responseService.getSuccessResult();
    }
}

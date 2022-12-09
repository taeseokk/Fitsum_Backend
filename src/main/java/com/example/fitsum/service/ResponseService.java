package com.example.fitsum.service;


import com.example.fitsum.model_response.CommonResult;
import com.example.fitsum.model_response.ListResult;
import com.example.fitsum.model_response.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service // 해당 클래스가 Service임을 명시한다.
public class ResponseService {

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    // enum으로 API 요청 결과에 대한 code, message를 정의한다.
    public enum CommonResponse {
        SUCCESS(201, "성공하였습니다. HappyPepe! XD"),
        FAIL(-1, "실패하였습니다. SadPepe :(");

        private int code;
        private String msg;
    }

    // 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() { // CommonResult 응답 결과를 알려주는 클래스
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        result.setCode(201);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false); // setSuccess : 응답 성공 여부 (true/false)
        result.setCode(code); // setCode : 응답 코드 번호 >= 0 정상, < 0 비정상
        result.setMsg(msg); // setMsg 응답 메시지
        return result;
    }

    // 결과 모델에 API 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
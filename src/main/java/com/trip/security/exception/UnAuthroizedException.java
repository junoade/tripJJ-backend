package com.trip.security.exception;

public class UnAuthroizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnAuthroizedException() {
        super("계정 권한이 유효하지 않습니다. 다시 로그인을 하세요.");
    }
}

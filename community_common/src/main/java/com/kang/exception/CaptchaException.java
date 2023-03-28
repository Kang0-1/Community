package com.kang.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kang
 * @description 自定义异常
 * @date 2023/3/16 18:21
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}

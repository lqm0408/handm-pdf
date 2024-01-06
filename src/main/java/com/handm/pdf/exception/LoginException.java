package com.handm.pdf.exception;

/**
 * @author lqm
 * @date 2024/1/6 17:55
 */
public class LoginException extends RuntimeException {

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}

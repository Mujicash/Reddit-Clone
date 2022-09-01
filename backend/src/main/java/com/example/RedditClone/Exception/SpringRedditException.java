package com.example.RedditClone.Exception;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String message, Exception ex) {
        super(message, ex);
    }

    public SpringRedditException(String message) {
        super(message);
    }
}

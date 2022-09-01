package com.example.RedditClone.Model;

import lombok.Data;

@Data
public class NotificationEmail {

    private String subject;
    private String recipient;
    private String body;

    public NotificationEmail(String subject, String recipient, String body) {
        this.subject   = subject;
        this.recipient = recipient;
        this.body      = body;
    }
}

package com.example.RedditClone.Service;

import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Model.NotificationEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender     mailSender;
    private final MailContentBuilder mailContentBuilder;

    public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender         = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springredditclone@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Activation mail send!!!");
        } catch(MailException me) {
            log.error("Exception occurred when sending mail", me);
            throw new SpringRedditException(
                    "Exception occurred when sending mail to " + notificationEmail.getRecipient(), me);
        }
    }
}

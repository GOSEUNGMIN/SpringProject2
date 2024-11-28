package com.example.project2.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    private boolean isLogin = false;
    private int minute = 0;

    private final JavaMailSender javaMailSender;

    public BatchService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void loginSuccess() {
        isLogin = true;
        minute = 0;
    }

    @Scheduled(fixedRate = 30000)
    public void sendLogin() {
        if (isLogin && minute < 3) {
            sendEmail();
            minute++;
        }

        if (minute >= 3) {
            isLogin = false;
        }
    }

    private void sendEmail() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("kcj8977@gmail.com");
            helper.setTo("kcj8977@gmail.com");
            helper.setSubject("로그인 알림");
            helper.setText("로그인 성공 하였습니다. 현재 이 메일은 " +
                    (minute + 1) + "번째 메일이며, 총 3번 전송 됩니다.", true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

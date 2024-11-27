package com.example.project2.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendReserMail(String to, String userId, String name, String resertime, String reserdetail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("kcj8977@gmail.com");
        helper.setTo(to);
        helper.setSubject(name + "가게의 새로운 예약 알림이 도착했습니다.");

        String content = "<h3>새로운 예약이 접수 되었습니다</h3>" +
                         "<p><strong>예약자 : </strong> " + userId + "</p>" +
                         "<p><strong>예약 음식점 : </strong> " + name + "</p>" +
                         "<p><strong>예약 시간 : </strong> " + resertime + "</p>" +
                         "<p><strong>요청 사항 : </strong> " + reserdetail + "</p>";

        helper.setText(content, true);

        javaMailSender.send(message);
    }
}

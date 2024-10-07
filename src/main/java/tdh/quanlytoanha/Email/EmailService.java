package tdh.quanlytoanha.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail (Email email){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setFrom("trinhdoanhauu@gmail.com");
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubJect());
            helper.setText(email.getBody());
            // Gửi email dưới dạng HTML
            helper.setText(email.getBody(), true); // true để cho phép HTML
            mailSender.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

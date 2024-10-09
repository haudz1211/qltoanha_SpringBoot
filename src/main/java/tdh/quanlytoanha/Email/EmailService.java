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


    // Phương thức gửi mã OTP
    public void sendOtpEmail(String toEmail, String otp) {
        String subject = "Mã xác nhận OTP của bạn";
        String body = "<h3>Xin chào!</h3>"
                + "<p>Mã OTP của bạn là: <strong>" + otp + "</strong></p>"
                + "<p>Vui lòng nhập mã này để xác nhận đăng ký.</p>";

        Email email = new Email();
        email.setToEmail(toEmail);
        email.setSubJect(subject);
        email.setBody(body);

        sendEmail(email); // Gọi phương thức gửi email đã có
    }
}

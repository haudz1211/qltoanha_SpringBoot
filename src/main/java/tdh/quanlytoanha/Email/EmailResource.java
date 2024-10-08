package tdh.quanlytoanha.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/email")
public class EmailResource {
    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody Email email) {
        emailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }
}

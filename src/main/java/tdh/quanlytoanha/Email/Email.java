package tdh.quanlytoanha.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @NotBlank(message = "Email cannot be blank")
    private String toEmail;
    @NotBlank(message = "subJect cannot be blank")
    private String subJect;
    @NotBlank(message = "body cannot be blank")
    private String body;
}

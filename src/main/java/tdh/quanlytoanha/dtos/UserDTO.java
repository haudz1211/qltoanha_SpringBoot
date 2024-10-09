package tdh.quanlytoanha.dtos;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank   (message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Password không được để trống")
    private String password;

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;
    private String email; // Thêm trường email
}

package tdh.quanlytoanha.dtos;

public class OtpDTO {
    private String email;      // Email người dùng
    private String otp;        // Mã OTP đã gửi
    private String username;   // Tên người dùng
    private String password;   // Mật khẩu người dùng
    private String fullName;   // Họ tên người dùng

    // Getters và Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

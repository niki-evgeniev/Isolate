package shop.DTO.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {

    @NotEmpty(message = "{fieldCannotBeEmpty}")
    @Size(min = 3, max = 50, message = "{fieldSize}")
    @Email
    private String email;
    @NotEmpty(message = "{fieldCannotBeEmpty}")
    @Size(min = 3, max = 30, message = "{fieldSize}")
    private String password;
    @NotEmpty(message = "{fieldCannotBeEmpty}")
    @Size(min = 3, max = 30, message = "{fieldSize}")
    private String confirmPassword;


    public RegisterUserDTO() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

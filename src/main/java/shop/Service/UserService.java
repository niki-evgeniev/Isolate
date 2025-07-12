package shop.Service;

import jakarta.validation.Valid;
import shop.DTO.UserDto.RegisterUserDTO;

public interface UserService {

    void addAdminProfile();

    boolean checkEmailExist(String email);

    boolean registerNewUser(RegisterUserDTO registerUserDTO);
}

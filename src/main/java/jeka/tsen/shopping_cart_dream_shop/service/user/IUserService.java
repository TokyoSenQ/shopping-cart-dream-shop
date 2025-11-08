package jeka.tsen.shopping_cart_dream_shop.service.user;

import jeka.tsen.shopping_cart_dream_shop.dto.UserDto;
import jeka.tsen.shopping_cart_dream_shop.model.User;
import jeka.tsen.shopping_cart_dream_shop.request.CreateUserRequest;
import jeka.tsen.shopping_cart_dream_shop.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}

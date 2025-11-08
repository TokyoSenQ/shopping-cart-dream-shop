package jeka.tsen.shopping_cart_dream_shop.service.user;

import jeka.tsen.shopping_cart_dream_shop.dto.UserDto;
import jeka.tsen.shopping_cart_dream_shop.exceptions.AlreadyExistsExcpetion;
import jeka.tsen.shopping_cart_dream_shop.exceptions.ResourseNotFoundException;
import jeka.tsen.shopping_cart_dream_shop.model.User;
import jeka.tsen.shopping_cart_dream_shop.repository.UserRepository;
import jeka.tsen.shopping_cart_dream_shop.request.CreateUserRequest;
import jeka.tsen.shopping_cart_dream_shop.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourseNotFoundException("User not found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return  Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return  userRepository.save(user);
                }) .orElseThrow(() -> new AlreadyExistsExcpetion("Oops!" +request.getEmail() +" already exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return  userRepository.findById(userId).map(existingUser ->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourseNotFoundException("User not found!"));

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () ->{
            throw new ResourseNotFoundException("User not found!");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

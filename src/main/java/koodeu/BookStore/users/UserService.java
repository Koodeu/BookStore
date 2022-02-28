package koodeu.BookStore.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserListDto saveUser(UserRegistrationDto dto) {
        User user = User.fromDto(dto);
        User saved = userRepository.save(user);
        return saved.userListDto();
    }
}

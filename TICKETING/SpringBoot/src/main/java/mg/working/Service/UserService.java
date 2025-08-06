package mg.working.Service;

import mg.working.model.User;
import mg.working.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> userList () {
        return userRepository.findAll();
    }

    public Optional<User> findByid(String id) {
        return userRepository.findById(id);
    }
}

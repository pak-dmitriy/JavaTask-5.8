package habsida.spring.boot_security.demo.services;

import habsida.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long id);

    Optional<User> findUserByEmail(String email);

    public User findUserByUsername(String username);
}

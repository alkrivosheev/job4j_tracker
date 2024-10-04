package ru.job4j.ood.dip.userservice;

public class UserService {
    private DatabaseUserRepository userRepository;

    public UserService() {

        userRepository = new DatabaseUserRepository();
    }

    public User findUser(int id) {

        return userRepository.getUserById(id);

    }
}

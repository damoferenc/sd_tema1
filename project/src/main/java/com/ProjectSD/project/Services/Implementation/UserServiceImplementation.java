package com.ProjectSD.project.Services.Implementation;

import com.ProjectSD.project.Entities.Device;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Repositories.UserRepository;
import com.ProjectSD.project.Services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User findById(long id) {
        return userRepository.findFirstById(id);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByUsernameAndPassword(username, password);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        User userDb = userRepository.findById(user.getId()).get();
        userDb.setUsername(user.getUsername());
        userDb.setPassword(user.getPassword());
        userDb.setRole(user.getRole());

        return userDb;
    }

    @Transactional
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User deleteUser(User user) {
        userRepository.delete(user);

        return user;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    @Override
    public User addDevice(User user, Device device) {
        User userDb = userRepository.findById(user.getId()).get();
        List<Device> deviceList = userDb.getDeviceList();
        deviceList.add(device);
        userDb.setDeviceList(deviceList);

        return userDb;
    }

    @Override
    public List<Device> getAllDevices(long id) {
        User userDb = userRepository.findFirstById(id);
        List<Device> deviceList = userDb.getDeviceList();
        return deviceList;
    }

}

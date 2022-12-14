package com.ProjectSD.project.Services;

import com.ProjectSD.project.Entities.Device;
import com.ProjectSD.project.Entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User findById(long id);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    User updateUser(User user);
    User createUser(User user);
    User deleteUser(User user);
    List<User> findAll();
    User addDevice(User user, Device device);
    List<Device> getAllDevices(long id);
}
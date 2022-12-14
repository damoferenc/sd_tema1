package com.ProjectSD.project.Controllers;

import com.ProjectSD.project.Constants.Role;
import com.ProjectSD.project.DTO.DeviceDTO;
import com.ProjectSD.project.DTO.UserDTO;
import com.ProjectSD.project.Entities.Device;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Exceptions.InexistentRoleException;
import com.ProjectSD.project.Services.DeviceService;
import com.ProjectSD.project.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final DeviceService deviceService;

    public UserController(UserService userService, DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO dto){
        try {
            User user = new User();
            verifyRole(dto, user);
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
        }
        catch (InexistentRoleException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    private void verifyRole(UserDTO dto, User user) throws InexistentRoleException {
        switch (dto.getRole()){
            case "admin":
                user.setRole(Role.ADMIN);
                break;
            case "client":
                user.setRole(Role.CLIENT);
                break;
            default:
                throw new InexistentRoleException("The role " + dto.getRole() + "doesn't exist");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDTO dto){
        User user = userService.findById(dto.getId());
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User not found");
        }
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        try {
            verifyRole(dto, user);
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
        } catch (InexistentRoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity addDevice(@RequestBody DeviceDTO dto, @PathVariable String id){
        User user = userService.findById(Long.parseLong(id));
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User not found");
        }
        Device device = deviceService.findById(dto.getId());
        if(device == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Device not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.addDevice(user, device));
    }


    @GetMapping("/{id}")
    public ResponseEntity findUserDetails(@PathVariable String id) {
        User user = userService.findById(Long.parseLong(id));
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        User user = userService.findById(Long.parseLong(id));
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(user));
    }


}
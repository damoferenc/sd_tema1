package com.ProjectSD.project.Controllers;


import com.ProjectSD.project.Constants.Role;
import com.ProjectSD.project.DTO.AuthDTO;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity register(@RequestBody AuthDTO auth){
        System.out.println("Registration requested");
        User user = new User();
        user.setPassword(auth.getPassword());
        user.setUsername(auth.getUsername());
        user.setRole(Role.CLIENT);
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
    }

}

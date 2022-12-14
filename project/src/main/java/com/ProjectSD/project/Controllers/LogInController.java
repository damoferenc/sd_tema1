package com.ProjectSD.project.Controllers;


import com.ProjectSD.project.DTO.AuthDTO;
import com.ProjectSD.project.Entities.User;
import com.ProjectSD.project.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LogInController {

    private final UserService userService;


    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody AuthDTO auth){
        System.out.println("Login requested");
        User user = userService.findByUsernameAndPassword(auth.getUsername(), auth.getPassword());
        System.out.println(user);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

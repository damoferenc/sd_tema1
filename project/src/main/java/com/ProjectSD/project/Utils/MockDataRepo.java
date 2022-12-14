package com.ProjectSD.project.Utils;

import com.ProjectSD.project.Constants.Role;
import com.ProjectSD.project.Entities.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MockDataRepo {

    public List<User> initAdministrators(){
        List<User> administrators = new LinkedList<>();
        administrators.add(User.builder().username("admin1").password("pass").role(Role.ADMIN).build());
        return administrators;
    }

}

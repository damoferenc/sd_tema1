package com.ProjectSD.project.Repositories;

import com.ProjectSD.project.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstById(long id);
    User findFirstByUsername(String username);
    User findFirstByUsernameAndPassword(String username, String password);
}

package com.ProjectSD.project.Utils;

import com.ProjectSD.project.Repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Initializer {
    private final MockDataRepo mockDataRepo;
    private final UserRepository userRepository;

    public Initializer(MockDataRepo mockDataRepo, UserRepository userRepository) {
        this.mockDataRepo = mockDataRepo;
        this.userRepository = userRepository;
    }

    @Bean
    public void initializeData(){
        userRepository.saveAll(mockDataRepo.initAdministrators());
    }

}
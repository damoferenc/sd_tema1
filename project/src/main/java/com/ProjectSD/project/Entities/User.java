package com.ProjectSD.project.Entities;

import com.ProjectSD.project.Constants.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Device> deviceList;

}

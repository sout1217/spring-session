package com.example.springsession.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean enable;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    List<Role> roles = new ArrayList<>();

    public static Member create(String username, String password) {
        Member member = new Member();
        member.username = username;
        member.password = password;
        member.enable = true;
        member.roles.add(Role.builder().roleName("ROLE_USER").build());

        return member;
    }
}

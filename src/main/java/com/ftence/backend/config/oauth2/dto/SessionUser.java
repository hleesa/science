package com.ftence.backend.config.oauth2.dto;

import com.ftence.backend.entity.Role;
import com.ftence.backend.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String intraId;
    private String picture;
    private Role role;

    public SessionUser(User user) {
        this.id = user.getId();
        this.intraId = user.getIntraId();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }
}

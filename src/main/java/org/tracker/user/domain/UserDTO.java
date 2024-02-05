package org.tracker.user.domain;

import lombok.Data;

@Data
public class UserDTO {
    private Long num;
    private String id;
    private String password;

    public String toString() {
        return "UserDTO(num=" + num + ", id=" + id + ", password=" + password + ")";
    }
}

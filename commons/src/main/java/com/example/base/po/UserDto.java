package com.example.base.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author benben
 * @program cloud2021
 * @Description
 * @date 2021-05-11 8:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private List<Role> roles;

}

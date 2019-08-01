package com.dong.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Employee {
    private Long id;

    private String username;

    private String password;

    private String salt;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    private String tel;

    private String email;

    private Boolean state;

    private Boolean admin;

    private Department department;

    private List<Role> roles = new ArrayList<>();

}
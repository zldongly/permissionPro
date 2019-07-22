package com.dong.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongly on 2019/7/21
 *
 * 角色
 */

@Data
public class Role {
    private Long rid;

    private String rnum;

    private String rname;

    private List<Permission> permissions = new ArrayList<>();

}
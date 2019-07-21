package com.dong.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dongly on 2019/7/20
 *
 * 接收分页参数
 */

@Getter@Setter
public class QueryVo {
    private int page;
    private int rows;
}

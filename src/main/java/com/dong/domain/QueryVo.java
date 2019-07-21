package com.dong.domain;

import lombok.Data;

/**
 * Created by dongly on 2019/7/20
 *
 * 接收分页参数
 */

@Data
public class QueryVo {
    private int page;           // 页码
    private int rows;           // 单页行数
    private String keyword;     // 搜索信息
}

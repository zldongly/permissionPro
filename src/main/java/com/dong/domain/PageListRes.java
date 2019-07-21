package com.dong.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Getter@Setter@ToString
public class PageListRes {
    private Long total;
    private List<?> rows = new ArrayList<>();
}

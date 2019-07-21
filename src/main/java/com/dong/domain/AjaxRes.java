package com.dong.domain;

import lombok.Data;

/**
 * Created by dongly on 2019/7/20
 *
 * 返回成功or失败
 */

@Data
public class AjaxRes {
    private boolean success;
    private String msg;
}

package com.dong.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongly on 2019/7/25
 */

public class RequestUtil {
    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal();

    public static HttpServletRequest getRequest() {
        return local.get();
    }

    public static void setReques(HttpServletRequest request) {
        local.set(request);
    }
}

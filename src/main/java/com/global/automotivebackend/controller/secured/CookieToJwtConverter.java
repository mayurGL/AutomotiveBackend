package com.global.automotivebackend.controller.secured;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/*
 * Cookie to JWT convertor class
 */
public abstract class CookieToJwtConverter {

    static String getTokenFromCookie(HttpServletRequest request) {
        String jwtToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("MyJWT")) {
                    jwtToken = cookie.getValue();
                }
            }
        }
        return jwtToken;
    }
}
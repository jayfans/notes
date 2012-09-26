package com.pwc.service;

public class AuthorizationService {
    public int provide(String name, String pass) {
        return name.equals("zhixiong")  && pass.equals("xk") ? 1 : 0;
    }
}

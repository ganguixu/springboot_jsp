package com.ganguixu.service;

import com.ganguixu.model.User;

import java.util.List;

public interface UserService {

    List<User> findUserAll(String type,String beginDate,String endDate);
}

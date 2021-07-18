package com.ganguixu.service.impl;

import com.ganguixu.model.User;
import com.ganguixu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> findUserAll(String type, String beginDate, String endDate) {
        List<User> users = new ArrayList<>();
        boolean b = parseDate(beginDate, endDate);
        if (b) {
            log.info("开始时间小于或等于结束时间，时间选择合法");
            int i = 1;
            while (i <=10) {
                User user = new User();
                user.setName(i+"");
                user.setPass(i+i+"");
                users.add(user);
                i++;
            }
        }
        return users;
    }

    private boolean parseDate(String begin,String end) {
        boolean isBig = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (begin != null && begin.trim().length() >0 && end != null && end.trim().length()>0) {
            try {
                Date beginDate = format.parse(begin);
                Date endDate = format.parse(end);
                int i = endDate.compareTo(beginDate);
                if (i >= 0) {
                    isBig = true;
                } else {
                    isBig = false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return isBig;
    }
}

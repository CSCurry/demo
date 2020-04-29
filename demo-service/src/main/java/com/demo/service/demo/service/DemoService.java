package com.demo.service.demo.service;

import com.demo.common.entity.DemoUser;

import java.util.List;

public interface DemoService {

    List<DemoUser> getAll();

    int addUser(DemoUser user);
}

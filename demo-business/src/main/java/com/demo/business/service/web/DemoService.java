package com.demo.business.service.web;

import com.demo.business.domain.DemoUser;

import java.util.List;

public interface DemoService {

    List<DemoUser> getAll();

    int addUser(DemoUser user);
}

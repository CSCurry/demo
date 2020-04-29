package com.demo.service.demo.service.impl;

import com.demo.common.entity.DemoUser;
import com.demo.service.demo.mapper.DemoUserMapper;
import com.demo.service.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoUserMapper demoUserMapper;

    @Override
    public List<DemoUser> getAll() {
        return demoUserMapper.getAll();
    }

    @Override
    public int addUser(DemoUser user) {
        return demoUserMapper.insert(user);
    }
}

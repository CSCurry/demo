package com.demo.business.service.web.impl;

import com.demo.business.domain.DemoUser;
import com.demo.business.mapper.DemoUserMapper;
import com.demo.business.service.web.DemoService;
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

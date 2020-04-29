package com.demo.web.controller.test;

import com.demo.common.base.BaseController;
import com.demo.common.base.BaseResult;
import com.demo.common.base.BaseResultVO;
import com.demo.common.entity.TestEntity;
import com.demo.framework.systemlog.SystemLog;
import com.demo.framework.taxno.TaxNoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "测试控制器 - Result")
@RequestMapping("test/result")
public class TestResultController extends BaseController {

    @Resource
    private TaxNoService taxNoService;

    @PostMapping("list")
    public BaseResultVO<TestEntity> test(@RequestBody @Valid TestEntity t) {
        TestEntity testEntity = new TestEntity();
        testEntity.setProductName("小米粥");
        testEntity.setProductPrice(10.1);
        testEntity.setProductStock(100);

        ArrayList<TestEntity> list = new ArrayList<>();
        list.add(testEntity);
        list.add(t);

        return BaseResultVO.success(list);
    }

    @PostMapping("delete")
    public BaseResult delete() {
        return BaseResult.success("删除成功");
    }

    @PostMapping("update")
    public BaseResult update() {
        return BaseResult.success();
    }

    @SystemLog
    @PostMapping("query")
    public BaseResult query() {
        TestEntity testEntity = new TestEntity();
        testEntity.setProductName("小米粥");
        testEntity.setProductPrice(10.1);
        testEntity.setProductStock(0);
        int j = 1 / testEntity.getProductStock();

        return BaseResult.success(testEntity);
    }

    @PostMapping("queryCompanyInfo")
    public BaseResult queryCompanyInfo(String content) {
        List<Map<String, Object>> maps = taxNoService.companyInfo(content);
        return BaseResult.success(maps);
    }
}
package com.demo.business.service.mgr.extra;

import com.demo.business.domain.SysDictData;
import com.demo.business.service.mgr.ISysDictDataService;
import com.demo.business.service.mgr.ISysDictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * html调用 thymeleaf 实现字典读取
 *
 * @author 30
 */
@Service("dict")
public class DictService {

    @Resource
    private ISysDictTypeService dictTypeService;
    @Resource
    private ISysDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType) {
        return dictTypeService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
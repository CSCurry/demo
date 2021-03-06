package com.demo.business.service.mgr.impl;

import com.demo.business.domain.SysOperLog;
import com.demo.business.mapper.SysOperLogMapper;
import com.demo.business.service.mgr.ISysOperLogService;
import com.demo.framework.util.ConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志 ServiceImpl
 *
 * @author 30
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Resource
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     */
    @Override
    public int deleteOperLogByIds(String ids) {
        return operLogMapper.deleteOperLogByIds(ConvertUtil.toStrArray(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}

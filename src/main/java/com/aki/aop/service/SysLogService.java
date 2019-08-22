package com.aki.aop.service;

import com.aki.aop.po.SysLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogBO sysLogBO){
        // 这里就不做具体实现了
        log.info(sysLogBO.getParams());
        return true;
    }


}

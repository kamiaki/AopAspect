package com.aki.aop.po;

import lombok.Data;

@Data
public class SysLogBO {
    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;

}

//package com.aki.aop.hollycrm.aspect;
//
//import com.hollycrm.emscheck.common.enums.DateFormatEnum;
//import com.hollycrm.emscheck.common.util.DateUtils;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Date;
//
//@Data
//@Slf4j
//public class OutPutLog {
//    /**
//     * 接口描述
//     */
//    private String description;
//
//    /**
//     * 用户ID
//     */
//    private String userId;
//
//    /**
//     * 请求日期
//     */
//    private Long startTime;
//
//    /**
//     * 消耗时间
//     */
//    private long spendTime;
//
//    /**
//     * URI
//     */
//    private String uri;
//
//    /**
//     * URL
//     */
//    private String url;
//
//    /**
//     * 请求类型
//     */
//    private String method;
//
//    /**
//     * 入参
//     */
//    private Object parameter;
//
//    /**
//     * 出参
//     */
//    private Object result;
//
//    /**
//     * 类名
//     */
//    private String className;
//
//    /**
//     * 方法名
//     */
//    private String methodName;
//
//    /**
//     * 打印日志
//     */
//    public void outPutLog(){
//        log.info(new StringBuffer().append("==============================================================================================================================").toString());
//        log.info(new StringBuffer().append("接口描述:    ").append(description).toString());
//        log.info(new StringBuffer().append(" 用户ID:    ").append(userId).toString());
//        log.info(new StringBuffer().append("请求日期:    ").append(DateUtils.date2String(DateFormatEnum.YYYY_MM_DD_HH_MM_SS, new Date(startTime))).toString());
//        log.info(new StringBuffer().append("消耗时间:    ").append(spendTime).append("毫秒    ").toString());
//        log.info(new StringBuffer().append("    URI:    ").append(uri).toString());
//        log.info(new StringBuffer().append("    URL:    ").append(url).toString());
//        log.info(new StringBuffer().append("请求类型:    ").append(method).toString());
//        log.info(new StringBuffer().append("   类名:    ").append(className).toString());
//        log.info(new StringBuffer().append(" 方法名:    ").append(methodName).toString());
//        log.info(new StringBuffer().append("   入参:    ").append(parameter).toString());
//        log.info(new StringBuffer().append("   出参:    ").append(result).toString());
//        log.info(new StringBuffer().append("==============================================================================================================================").toString());
//    }
//}

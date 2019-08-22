package com.aki.aop.aop;

import com.aki.aop.po.SysLogBO;
import com.aki.aop.service.SysLogService;
import com.aki.aop.zhujie.SysLog;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class TestAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(com.aki.aop.zhujie.SysLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void Before( ) throws Throwable {
        System.out.println("之前");
    }

    @After("logPointCut()")
    public void After( ) throws Throwable {
        System.out.println("之后");
    }

    /**
     * 环绕通知 @Around 当然也可以使用 @Before (前置通知)  @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前");
        long beginTime = System.currentTimeMillis();
        //获取原方法的返回值
        Object result = point.proceed();
        //执行方法的时间
        long time = System.currentTimeMillis() - beginTime;
        System.out.println("环绕后");
        try {
            saveLog(point, time);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //注解对象
        SysLogBO sysLogBO = new SysLogBO();

        //设置注解上的描述
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            sysLogBO.setRemark(sysLog.value());
        }
        //设置执行时间
        sysLogBO.setExeuTime(time);
        //设置创建日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sysLogBO.setCreateDate(dateFormat.format(new Date()));
        //设置请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogBO.setClassName(className);
        sysLogBO.setMethodName(methodName);
        //设置请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                list.add(new Gson().toJson(o));
            }
            sysLogBO.setParams(list.toString());
        } catch (Exception e) {
        }
        sysLogService.save(sysLogBO);
    }
}
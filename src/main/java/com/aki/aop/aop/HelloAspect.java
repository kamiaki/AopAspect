package com.aki.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {
//    实现AOP的切面主要有以下几个要素：
//    使用@Aspect注解将一个java类定义为切面类
//    使用@Pointcut定义一个切入点，可以是一个规则表达式，比如某个package下的所有函数，也可以是一个注解等。

//    根据需要在切入点不同位置的切入内容
//    使用@Before在切入点开始处切入内容
//    使用@After在切入点结尾处切入内容
//    使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
//    使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
//    使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑

    @Pointcut("execution(public * com.aki.aop.controller.HelloController.add*(..))&& @annotation(com.aki.aop.zhujie.MyAnnotation)")
    public void addAdvice() {
    }

    @Around("addAdvice()")
    public Object KamiAkiInterceptor(ProceedingJoinPoint pjp) {
        Object result = null;
        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            String deviceId = (String) args[0];
            if (!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
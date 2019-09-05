//package com.aki.aop.hollycrm.aspect;
//
//import com.hollycrm.emscheck.common.util.StringUtil;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.CodeSignature;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Component
//@Aspect
//public class LogAspect {
//
//    @Pointcut("execution(public * com.hollycrm.emscheck.webserver.controller..*.*(..))")
//    public void pointCut() {
//    }
//
//    @Around("pointCut()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        //起始时间
//        long startTime = System.currentTimeMillis();
//        //执行接口,拿返回值
//        Object result = joinPoint.proceed();
//        //耗时
//        long SpendTime = System.currentTimeMillis() - startTime;
//
//        try {
//            OutPutLog outPutLog = new OutPutLog();
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//            Signature signature = joinPoint.getSignature();
//            MethodSignature methodSignature = (MethodSignature) signature;
//            Method method = methodSignature.getMethod();
//
//            //类名
//            String className = joinPoint.getTarget().getClass().getName();
//            //方法名
//            String methodName = joinPoint.getSignature().getName();
//            //实参
//            Object[] args = joinPoint.getArgs();
//            //形参
//            String[] paramNames = ((CodeSignature) signature).getParameterNames();
//            //方法描述
//            String description = "";
//            if (method.isAnnotationPresent(ApiOperation.class)) {
//                description = method.getAnnotation(ApiOperation.class).value();
//            }
//
//            outPutLog.setDescription(description);
//            outPutLog.setUserId(request.getParameter("userId"));
//            outPutLog.setMethod(request.getMethod());
//            outPutLog.setParameter(getParameter(method, paramNames, args));
//            outPutLog.setResult(result);
//            outPutLog.setSpendTime(SpendTime);
//            outPutLog.setStartTime(startTime);
//            outPutLog.setUri(request.getRequestURI());
//            outPutLog.setUrl(request.getRequestURL().toString());
//            outPutLog.setClassName(className);
//            outPutLog.setMethodName(methodName);
//
//            outPutLog.outPutLog();
//        }catch (Exception e){
//            log.error(new StringBuffer().append("接口日志打印失败,原因:").append(e.getMessage()).toString());
//        }
//        return result;
//    }
//
//    /**
//     * 根据方法和传入的参数获取请求参数
//     *
//     * @param method
//     * @param args
//     * @return
//     */
//    private Object getParameter(Method method, String[] paramNames, Object[] args) {
//        List<Object> argList = new ArrayList<>();
//        Parameter[] parameters = method.getParameters();
//        for (int i = 0; i < parameters.length; i++) {
//            //拿RequestBody注解的值 //拿RequestParam注解的值
//            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
//            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
//            Map<String, Object> map = new HashMap<>();
//            String keyStr;
//            //按照 body和param分类取入参
//            if (requestBody != null) {
//                keyStr = this.getFormalParameter(paramNames, parameters, i);
//                map.put(keyStr, args[i]);
//            } else if (requestParam != null) {
//                if (!StringUtils.isEmpty(requestParam.name())) {
//                    keyStr = requestParam.name();
//                } else {
//                    keyStr = this.getFormalParameter(paramNames, parameters, i);
//                }
//                map.put(keyStr, args[i]);
//            } else {
//                keyStr = this.getFormalParameter(paramNames, parameters, i);
//                map.put(keyStr, args[i]);
//            }
//            //写入list
//            argList.add(map);
//        }
//        // 0.null 1.one n.list
//        if (argList.size() == 0) {
//            return null;
//        } else if (argList.size() == 1) {
//            return argList.get(0);
//        } else {
//            return argList;
//        }
//    }
//
//    /**
//     * 取形参名字
//     */
//    private String getFormalParameter(String[] paramNames, Parameter[] parameters, int i) {
//        String key;
//        try {
//            key = paramNames[i];
//        } catch (Exception e) {
//            key = parameters[i].getName();
//        }
//        return StringUtil.killNull(key);
//    }
//}

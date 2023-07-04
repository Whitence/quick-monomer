package cn.edu.szu.cs.quickmonomer.log;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 统一日志切面类
 * @author whitence
 * @date 2023/3/14 16:38
 * @version 1.0
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {


    @Pointcut("execution(public * cn.edu.szu.cs.quickmonomer.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 日志对象
        WebLog webLog = new WebLog();
        // 执行请求
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        // 获取请求方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 获取URL
        String urlStr = request.getRequestURL().toString();

        webLog.setBasePath(CharSequenceUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        // ip
        webLog.setIp(request.getRemoteUser());
        // 请求方法
        webLog.setMethod(request.getMethod());
        // 参数
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        // 结果
        //webLog.setResult(result);
        // 接口耗时
        webLog.setSpendTime((int) (endTime - startTime));

        webLog.setStartTime(startTime);

        webLog.setUri(request.getRequestURI());

        webLog.setUrl(request.getRequestURL().toString());

        log.debug("{}",webLog);

        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }

            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(1);
                String key = parameters[i].getName();
                if (CharSequenceUtil.isNotBlank(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (CollUtil.isEmpty(argList)) {
            return null;
        }

        if (argList.size() == 1) {
            return argList.get(0);
        }

        return argList;
    }
}

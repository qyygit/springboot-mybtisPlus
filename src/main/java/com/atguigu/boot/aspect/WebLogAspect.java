package com.atguigu.boot.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.atguigu.boot.constants.SystemConstants;
import com.atguigu.boot.feignclient.log.WebLog;
import com.atguigu.boot.jwt.JwtUserInfo;
import com.atguigu.boot.jwt.JwtUtils;
import com.atguigu.boot.utils.IpUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Order(2)
@Slf4j
public class WebLogAspect {

//
//    @Autowired
//    private IotLogClient iotLogClient;


    /**
     * 日志记录：
     *  环绕通知：方法执行之前、之后
     */
    /**
     * 1 定义切入点
     */
//    @Pointcut("@annotation(com.shooin.config.aspect.WebLogAnon)")
    @Pointcut("execution(* com.atguigu..controller.*.*(..)) "
            +   "&& !execution(* com.qyy..controller.*.*(..)) ")
    public void webLog(){}

    /**
     * 2 记录日志的环绕通知
     */
    @Around("webLog()")
    public Object recodeWebLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null ;
        // 获取方法
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        // 获取类的名称
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getName();
        Method method = signature.getMethod();
        String methodName = targetClassName + "." + method.getName();
        // 因为我们会使用Swagger 这工具，我们必须在方法上面添加@ApiOperation(value="")该注解
        // 获取ApiOperation
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        String desc=(annotation==null ? "no desc":annotation.value());
        String paramter = getMethodParameter(method, proceedingJoinPoint.getArgs()).toString();
        WebLog webLog = new WebLog();

        long start = System.currentTimeMillis() ;
//        //创建并启动StopWatch
//        StopWatch stopwatch = StopWatch.createStarted();
//        //业务逻辑......
//
//        stopwatch.stop();
//        //分钟,不足59秒,返回0
//        System.out.println("分钟单位" +  " :" + stopwatch.getTime(TimeUnit.MINUTES));
//        //毫秒单位
//        System.out.println("毫秒单位" + " :" + stopwatch.getTime(TimeUnit.MILLISECONDS));
//        //微妙单位
//        System.out.println("微妙单位" + " :" +stopwatch.getTime(TimeUnit.MICROSECONDS));
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(SystemConstants.AUTHORIZE_TOKEN);
        String ipAddr = IpUtil.getIpAddr(request);
//        System.out.println(ipAddr);
        String url = request.getRequestURL().toString();
        webLog.setUri(request.getRequestURI()); // 设置请求的uri
        webLog.setUrl(url);
        webLog.setBasePath(StrUtil.removeSuffix(url, URLUtil.url(url).getPath())); // http://ip:port/
        webLog.setIp(ipAddr);
        // 获取当前请求的request对象
        if(StringUtils.isEmpty(token) || "null".equals(token)){
//            request.getParameter(S)
            webLog.setCreateUser("system");
        }else{
            JwtUserInfo userInfo = JwtUtils.getJwtUserInfoByJwtToken(token);
            webLog.setCreateUser(userInfo==null ? "system":userInfo.getName()); // 获取用户的id
        }

        webLog.setDescription(desc);
        webLog.setMethod(methodName); // com.bjsxt.controller.UserController.login()
        webLog.setParameter(paramter); //{"key_参数的名称":"value_参数的值"}

       // 执行方法的真实调用
        try {
            log.info("切面log*******************开始执行方法：{}*******************",methodName);
            log.info("切面log*******************方法描述：{}*******************",desc);
            log.info("切面log*******************参数{}：*******************",paramter);
            result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            webLog.setFlag(true);
            log.info("切面log*******************执行方法{}完毕，结果：{}*******************",methodName,result);

            return result;
        } catch (Throwable throwable) {
            log.info("切面log*******************执行方法报错*******************");
            webLog.setFlag(false);
            result=throwable.getMessage();
            throw throwable;
        }finally {
//            saveWebLog(webLog,start,result);
        }
    }

//    public void saveWebLog(WebLog webLog, long start, Object result  ){
//        long end = System.currentTimeMillis() ;
//        webLog.setSpendTime((int)(end-start)/1000); // 请求该接口花费的时间
//        webLog.setCreateTime(LocalDateTime.now());
//        try {
//            iotLogClient.saveWebLog(webLog);
//        }catch (Exception e){
//            log.info(webLog.toString());
//            log.info("切面log*******************保存日志失败*******************");
//            e.printStackTrace();
//        }
//    }
    /**
     * 获取方法的执行参数
     * @param method
     * @param args
     * @return
     * {"key_参数的名称":"value_参数的值"}
     */
    private  Map<String, Object> getMethodParameter(Method method, Object[] args) {
        Map<String, Object> methodParametersWithValues = new HashMap<>();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer =
                            new LocalVariableTableParameterNameDiscoverer();
        // 方法的形参名称
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);

        for (int i = 0; i <parameterNames.length ; i++) {
            if(parameterNames[i].equals("password") || parameterNames[i].equals("file")||parameterNames[i].equals("request")){
                methodParametersWithValues.put(parameterNames[i],"受限的支持类型") ;
            }else{
                methodParametersWithValues.put(parameterNames[i],args[i]) ;
            }
        }
        return methodParametersWithValues ;
    }
}

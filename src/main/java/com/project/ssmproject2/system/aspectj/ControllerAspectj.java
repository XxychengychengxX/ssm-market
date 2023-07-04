/**
 * @author Valar Morghulis
 * @Date 2023/7/4
 */
package com.project.ssmproject2.system.aspectj;

import com.project.ssmproject2.system.response.NormalUpdateResponse;
import io.jsonwebtoken.JwtException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class ControllerAspectj {

    @Pointcut("execution(* com.project.ssmproject2.controller.*.*(..))")
    private void ExceptionInControllerPointCut() {
    }

    @Pointcut("@annotation(com.project.ssmproject2.system.config.WithAuthorizationHeader)))")
    public void JWTCompiredCheckPointCut() {
    }

    /**
     * 在controller层后抛出的通知
     *
     * @return 有异常后返回异常Json
     */
    @Around(value = "ExceptionInControllerPointCut()")
    public Object ExceptionInControllerInformation(ProceedingJoinPoint pjp) {
        Object proceed;
        try {
            proceed = pjp.proceed();
        } catch (Throwable ex) {
            if (ex instanceof JwtException)
                return NormalUpdateResponse.generate(true, 3, "身份验证失败,请重新登录后重试");
            else
                return NormalUpdateResponse.generate(true, 5,
                        "出错,请检查后重试!\n具体信息如下 :\n" + ex.getCause().getMessage());
        }
        return proceed;
    }

   /* @Before("JWTCompiredCheckPointCut()")
    public NormalUpdateResponse JWTCompiredCheck(JoinPoint joinPoint) {
        // 获取HttpServletRequest对象
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 获取Authorization请求头的值
        String authorization = request.getHeader("Authorization");

        try {
            if (authorization != null)
                MyToken.parseJWTGetUserRole(authorization);
        } catch (Exception e) {
            return NormalUpdateResponse.generate(true, 3, "身份已过期");
        }

        return null;
    }*/


}

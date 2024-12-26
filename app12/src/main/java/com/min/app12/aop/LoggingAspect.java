package com.min.app12.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
  
  @Pointcut("execution (* com.min.app12.controller.*Controller.*(..))")
  public void preparePointcut() {
    
  }
  
  @Before("preparePointcut()")
  public void logging(JoinPoint joinpoint) {
    // log.debug("Before Advice 동작");
    
    // HttpServletRequest를 이용해서 요청 메소드/요청 주소/요청 파라미터 확인해 보기
    
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    
    String requestMethod = request.getMethod();  // 요청 메소드
    String requestURI = request.getRequestURI(); // 요청 주소
    Map<String, String[]> params = request.getParameterMap();  // 모든 요청 파라미터를 Map으로 반환
    String str = "{";
    if(!params.isEmpty()) {
      for(Entry<String, String[]> entry : params.entrySet())
        str+= entry.getKey() + ":" + Arrays.toString(entry.getValue()) + ", ";
      
    }
    str = str.substring(0, Math.max(0, str.length() - 2)) + "}";
    
    log.debug("Method: {}, URI: {}, Parameters: {}", requestMethod, requestURI, str);
    
  }
  
  
  
}

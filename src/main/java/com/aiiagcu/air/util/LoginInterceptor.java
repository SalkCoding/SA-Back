package com.aiiagcu.air.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String clientAddress = request.getRemoteAddr();
        log.info("인증 체크 인터셉터 실행 {}", request.getRequestURI());
        //Success logging
        boolean succeed = validateSession(request);
        if (succeed) log.info("Session validation succeed {}, Client: {}, UUID: {}",
                request.getRequestURI(),
                clientAddress,
                request.getSession().getAttribute("loginUser").toString());
        else {
            log.info("Session validation failed, Client: {}", clientAddress);
//            response.sendRedirect("/");
//            response.sendRedirect("https://aiia-gcu.com/");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        
        //Handling
        return succeed;
    }
    
    private boolean validateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return false;
        }
        return true;
    }
    
}

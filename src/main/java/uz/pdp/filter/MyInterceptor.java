package uz.pdp.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class MyInterceptor implements HandlerInterceptor {


    // Controller chaqirilishidan avval
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle");

        Thread.sleep(5000);

        return true;
    }

    // Controller dan keyin render bolishidan avval
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Thread.sleep(5000);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    // render qilingandan so'ng
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Thread.sleep(5000);
        System.out.println("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

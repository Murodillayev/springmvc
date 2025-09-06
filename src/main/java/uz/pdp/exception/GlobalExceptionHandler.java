package uz.pdp.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handleBadRequestException(HttpServletRequest req, BadRequestException ex) {
        ModelAndView mav = new ModelAndView();


        mav.addObject("error_message", ex.getMessage());
        mav.setViewName("redirect:register");

        return mav;
    }


}

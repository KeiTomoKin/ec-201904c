package jp.co.example.ecommerce_c.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 404エラーが発生するときに、エラーページに遷移させる.
 * @author koichi.nagata
 *
 */
@Controller
public class NotFoundController implements ErrorController {

    private static final String PATH = "/error";
    
    @Override
    @RequestMapping(PATH)
    public String getErrorPath() {
    	System.out.println("404 not found");
        return "common/notFound";
    }

}
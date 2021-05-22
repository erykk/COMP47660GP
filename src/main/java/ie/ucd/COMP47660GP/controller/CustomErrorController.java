package ie.ucd.COMP47660GP.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(Model model){
        return "Oops, you did something wrong. Click back!";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

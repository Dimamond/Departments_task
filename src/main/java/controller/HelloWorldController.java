package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {



    @RequestMapping(value = "/", produces = "text/html")
    public String helloWorld(){
        System.out.println("Hello");
        return "hello";
    }

}
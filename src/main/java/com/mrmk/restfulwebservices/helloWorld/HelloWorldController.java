package com.mrmk.restfulwebservices.helloWorld;

import com.mrmk.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World ";
    }
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World ");
    }
    @GetMapping(path = "/hello-world-bean/{variable}")
    public HelloWorldBean helloQorldPath(@PathVariable String variable){
        return new HelloWorldBean("Hello World " + variable);
    }
    @GetMapping(path = "/hello-i18n")
    public String international() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);

    }

}

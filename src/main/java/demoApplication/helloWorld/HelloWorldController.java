package demoApplication.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET,path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
// hello world bean
    @RequestMapping(method = RequestMethod.GET,path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean ("Hello World");
    }

    // hello world path variable
    @RequestMapping(method = RequestMethod.GET,path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean (String.format("Hello World %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized (@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}

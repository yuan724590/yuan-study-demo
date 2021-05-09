package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.*;
import yuan.study.demo.service.ErrorService;

import javax.annotation.Resource;

@RequestMapping("/demo")
@RestController
public class ErrorController {

    @Resource
    private ErrorService errorService;

    /**
     * 栈溢出问题复现
     */
    @GetMapping(value = "/stackOverflowError")
    public String stackOverflowError(){
        return errorService.stackOverflowError();
    }

    /**
     * 栈溢出问题复现1
     */
    @GetMapping(value = "/stackOverflowError1")
    public String stackOverflowError1(){
        return errorService.stackOverflowError1();
    }

    /**
     * 堆溢出问题复现
     */
    @GetMapping(value = "/heapSpaceOOM")
    public String heapSpaceOOM(){
        return errorService.heapSpaceOOM();
    }
}

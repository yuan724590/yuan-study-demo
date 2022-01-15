package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.*;
import yuan.study.demo.service.ErrorService;

import javax.annotation.Resource;

@RequestMapping
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

    /**
     * 通过线程造成泄露, 栈溢出问题复现2(会造成win假死!!!!)
     */
    @GetMapping(value = "/stackLeakByThread")
    public String stackLeakByThread(){
        return errorService.stackLeakByThread();
    }

    /**
     * 运行时常量池OOM
     */
    @GetMapping(value = "/runtimeConstantPoolOOM")
    public String runtimeConstantPoolOOM(){
        return errorService.runtimeConstantPoolOOM();
    }

    /**
     * 直接内存OOM
     */
    @GetMapping(value = "/directMemoryOOM")
    public String directMemoryOOM(){
        return errorService.directMemoryOOM();
    }
}

package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.Java8DemoService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class Java8DemoController {

    @Resource
    private Java8DemoService demoService;

    /**
     * 测试多线程
     */
    @GetMapping(value = "/completableFuture")
    public String syncList() {
        return demoService.completableFuture();
    }

    /**
     * 使用limit
     */
    @GetMapping(value = "/limit")
    public String limit() {
        return demoService.limit();
    }
}

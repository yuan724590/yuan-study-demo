package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.aop.SpringService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class SpringController {

    @Resource
    private SpringService springService;

    /**
     * 动态代理
     */
    @GetMapping(value = "/spring/dynamic/proxy", produces = MediaType.APPLICATION_JSON_VALUE)
    public String dynamicProxy() {
        return springService.dynamicProxy();
    }

    /**
     * aop
     */
    @GetMapping(value = "/spring/aop", produces = MediaType.APPLICATION_JSON_VALUE)
    public String aop() {
        return springService.aop();
    }

    /**
     * 事件
     */
    @GetMapping(value = "/spring/event", produces = MediaType.APPLICATION_JSON_VALUE)
    public String event() {
        return springService.event();
    }
}

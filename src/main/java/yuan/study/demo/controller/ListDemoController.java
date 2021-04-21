package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.ListDemoService;

import javax.annotation.Resource;

/**
 * @author yuan
 * @Date 2021/4/18 20:09
 */
@RequestMapping("/demo")
@RestController
public class ListDemoController {

    @Resource
    private ListDemoService demoService;

    /**
     * subList相关测试
     */
    @PostMapping(value = "/subList", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String subList() {
        return demoService.subList();
    }

    /**
     * arrayList相关测试
     */
    @GetMapping(value = "/arrayList")
    public String arrayList() {
        return demoService.arrayList();
    }

    /**
     * 线程安全的list
     */
    @GetMapping(value = "/syncList")
    public String syncList() {
        return demoService.syncList();
    }

    /**
     * 测试list的最大长度
     */
    @GetMapping(value = "/testMaxListSize")
    public String testMaxListSize() {
        return demoService.testMaxListSize();
    }
}

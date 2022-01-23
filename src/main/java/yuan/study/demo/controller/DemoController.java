package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yuan.study.demo.dto.DemoDTO;
import yuan.study.demo.service.DemoService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    /**
     * threadLocal使用的demo
     */
    @PostMapping(value = "/threadLocal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String threadLocal(@RequestBody @Validated DemoDTO.ThreadLocalDTO reqDTO) {
        return demoService.threadLocal(reqDTO.getValue());
    }

    /**
     * SimpleDateFormat同步获取时间
     */
    @PostMapping(value = "/syncGetTime", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String syncGetTime() {
        return demoService.syncGetTime();
    }

    /**
     * localDateTime的使用
     */
    @PostMapping(value = "/localDateTime", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String localDateTime() {
        return demoService.localDateTime();
    }

    /**
     * 创建对象
     */
    @PostMapping(value = "/createObject", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createObject() {
        return demoService.createObject();
    }

    /**
     * 属性可见
     */
    @GetMapping(value = "/accessible")
    public String accessible(){
        return demoService.accessible();
    }

    /**
     * 两种构造器的区别, Declared关键词的意思
     */
    @GetMapping(value = "/getDeclaredConstructor")
    public String getDeclaredConstructor(){
        return demoService.getDeclaredConstructor();
    }

    /**
     * 获取硬件信息
     */
    @GetMapping(value = "/getHardwareInformation")
    public String getHardwareInformation(){
        return demoService.getHardwareInformation();
    }

    /**
     * 基本语法测试
     */
    @GetMapping(value = "/grammar")
    public String grammar(){
        return demoService.grammar();
    }

    /**
     * string的intern方法测试
     */
    @GetMapping(value = "/stringIntern")
    public String stringIntern(){
        return demoService.stringIntern();
    }

    /**
     * double测试
     */
    @GetMapping(value = "/doubleDemo")
    public String doubleDemo(){
        return demoService.doubleDemo();
    }

    /**
     * volatile测试
     */
    @GetMapping(value = "/volatileDemo")
    public String volatileDemo(){
        return demoService.volatileDemo();
    }

    /**
     * 属性拷贝工具的性能测试
     */
    @GetMapping(value = "/attribute/copy/test")
    public String attributeCopy(){
        return demoService.attributeCopy();
    }
}

package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yuan.study.demo.dto.DemoDTO;
import yuan.study.demo.service.DemoService;

import javax.annotation.Resource;

@RequestMapping("/demo")
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
     * subList相关测试
     */
    @PostMapping(value = "/subList", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String arrayList() {
        return demoService.subList();
    }

    /**
     * entrySet, keySet, foreach性能相关测试
     */
    @PostMapping(value = "/entrySet", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String entrySet() {
        return demoService.entrySet();
    }

    /**
     * 几种map对比
     */
    @PostMapping(value = "/mapCompare", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mapCompare() {
        return demoService.mapCompare();
    }

    /**
     * 去重
     */
    @PostMapping(value = "/duplicateRemoval", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String duplicateRemoval() {
        return demoService.duplicateRemoval();
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
     * 查看内存消耗
     */
    @GetMapping(value = "/getMemory")
    public String getMemory(){
        return demoService.getMemory();
    }
}

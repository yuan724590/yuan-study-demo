package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yuan.study.demo.dto.DemoDTO;
import yuan.study.demo.service.DemoService;
import yuan.study.demo.service.MapDemoService;

import javax.annotation.Resource;

@RequestMapping("/demo")
@RestController
public class MapDemoController {

    @Resource
    private MapDemoService mapDemoService;


    /**
     * entrySet, keySet, foreach性能相关测试
     */
    @PostMapping(value = "/entrySet", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String entrySet() {
        return mapDemoService.entrySet();
    }

    /**
     * 几种map对比
     */
    @PostMapping(value = "/mapCompare", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mapCompare() {
        return mapDemoService.mapCompare();
    }

    /**
     * 去重
     */
    @PostMapping(value = "/duplicateRemoval", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String duplicateRemoval() {
        return mapDemoService.duplicateRemoval();
    }
}

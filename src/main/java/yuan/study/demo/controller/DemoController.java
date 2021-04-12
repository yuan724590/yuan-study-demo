package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

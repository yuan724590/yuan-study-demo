package yuan.study.demo.controller.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.subjectService.OfferSubjectService;

import javax.annotation.Resource;

/**
 * 剑指 Offer系列
 */
@RestController
public class OfferSubjectController {

    @Resource
    private OfferSubjectService subjectService;

    /**
     * 03. 数组中重复的数字
     */
    @GetMapping(value = "/find/repeat/number")
    public String findRepeatNumber() {
        return subjectService.findRepeatNumber();
    }

    /**
     * 04. 二维数组中的查找
     */
    @GetMapping(value = "/find/number/in2DArray")
    public String findNumberIn2DArray() {
        return subjectService.findNumberIn2DArray();
    }
}

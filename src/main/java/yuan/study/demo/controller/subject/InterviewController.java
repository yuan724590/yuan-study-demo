package yuan.study.demo.controller.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.subjectService.InterviewService;
import javax.annotation.Resource;

/**
 * 面试题系列
 */
@RestController
public class InterviewController {

    @Resource
    private InterviewService interviewService;

    /**
     * 面试题 01.01. 判定字符是否唯一
     */
    @GetMapping(value = "/isUnique")
    public String isUnique() {
        return interviewService.isUnique();
    }

    /**
     * 面试题 01.02. 判定字符是否唯一
     */
    @GetMapping(value = "/checkPermutation")
    public String checkPermutation() {
        return interviewService.checkPermutation();
    }
}

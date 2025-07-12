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

    /**
     * 面试题 01.03. URL化
     */
    @GetMapping(value = "/replaceSpaces")
    public String replaceSpaces() {
        return interviewService.replaceSpaces();
    }

    /**
     * 面试题 01.04. 回文排列
     */
    @GetMapping(value = "/canPermutePalindrome")
    public String canPermutePalindrome() {
        return interviewService.canPermutePalindrome();
    }

    /**
     * 面试题 01.05. 一次编辑
     */
    @GetMapping(value = "/oneEditAway")
    public String oneEditAway() {
        return interviewService.oneEditAway();
    }
}

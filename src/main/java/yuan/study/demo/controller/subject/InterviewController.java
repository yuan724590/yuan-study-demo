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

    /**
     * 面试题 01.06. 字符串压缩
     */
    @GetMapping(value = "/compressString")
    public String compressString() {
        return interviewService.compressString();
    }

    /**
     * 面试题 01.07. 旋转矩阵
     * 同yuan.study.demo.service.subjectService.impl.SubjectServiceImpl#rotate() 故跳过
     */

    /**
     * 面试题 01.08. 零矩阵
     * 同yuan.study.demo.service.subjectService.impl.SubjectServiceImpl#setZeroes() 故跳过
     */

    /**
     * 面试题 01.09. 字符串轮转
     */
    @GetMapping(value = "/isFlipedString")
    public String isFlipedString() {
        return interviewService.isFlipedString();
    }

    /**
     * 面试题 02.01. 移除重复节点
     */
    @GetMapping(value = "/removeDuplicateNodes")
    public String removeDuplicateNodes() {
        return interviewService.removeDuplicateNodes();
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     */
    @GetMapping(value = "/kthToLast")
    public String kthToLast() {
        return interviewService.kthToLast();
    }

    /**
     * 面试题 02.03. 删除中间节点
     */
    @GetMapping(value = "/deleteNode0203")
    public String deleteNode() {
        return interviewService.deleteNode();
    }

    /**
     * 面试题 02.04. 分割链表
     * 同 yuan.study.demo.controller.subject.SubjectController#partition() 故跳过
     */

    /**
     * 面试题 02.05. 链表求和
     */
    @GetMapping(value = "/addTwoNumbers5")
    public String addTwoNumbers() {
        return interviewService.addTwoNumbers();
    }

    /**
     * 面试题 02.06. 回文链表
     */
    @GetMapping(value = "/isPalindrome6")
    public String isPalindrome() {
        return interviewService.isPalindrome();
    }
}

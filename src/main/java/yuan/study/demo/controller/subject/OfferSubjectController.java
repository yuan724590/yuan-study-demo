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

    /**
     * 05. 替换空格
     */
    @GetMapping(value = "/replace/space")
    public String replaceSpace() {
        return subjectService.replaceSpace();
    }

    /**
     * 06. 从尾到头打印链表
     */
    @GetMapping(value = "/reverse/print")
    public String reversePrint() {
        return subjectService.reversePrint();
    }

    /**
     * 07. 重建二叉树
     */
    @GetMapping(value = "/build/tree")
    public String buildTree() {
        return subjectService.buildTree();
    }

    /**
     * 09. 用两个栈实现队列
     */
    @GetMapping(value = "/two/stack/Queue")
    public String twoStackQueue() {
        return subjectService.twoStackQueue();
    }
}

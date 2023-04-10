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

    /**
     * 10- I. 斐波那契数列
     */
    @GetMapping(value = "/fib")
    public String fib() {
        return subjectService.fib();
    }

    /**
     * 10- II. 青蛙跳台阶问题
     */
    @GetMapping(value = "/numWays")
    public String numWays() {
        return subjectService.numWays();
    }

    /**
     * 11. 旋转数组的最小数字
     */
    @GetMapping(value = "/minArray")
    public String minArray() {
        return subjectService.minArray();
    }

    /**
     * 12. 矩阵中的路径
     */
    @GetMapping(value = "/exist")
    public String exist() {
        return subjectService.exist();
    }

    /**
     * 14- I. 剪绳子
     */
    @GetMapping(value = "/cutting/rope")
    public String cuttingRope() {
        return subjectService.cuttingRope();
    }

    /**
     * 14- II. 剪绳子 II
     */
    @GetMapping(value = "/cutting/rope2")
    public String cuttingRope2() {
        return subjectService.cuttingRope2();
    }

    /**
     * 15. 二进制中1的个数
     */
    @GetMapping(value = "/hamming/weight")
    public String hammingWeight() {
        return subjectService.hammingWeight();
    }

    /**
     * 16. 数值的整数次方
     */
    @GetMapping(value = "/myPow")
    public String myPow() {
        return subjectService.myPow();
    }

    /**
     * 17. 打印从1到最大的n位数
     */
    @GetMapping(value = "/print/numbers")
    public String printNumbers() {
        return subjectService.printNumbers();
    }

    /**
     * 18. 删除链表的节点
     */
    @GetMapping(value = "/delete/node")
    public String deleteNode() {
        return subjectService.deleteNode();
    }

    /**
     * 19. 正则表达式匹配
     */
    @GetMapping(value = "/is/match")
    public String isMatch() {
        return subjectService.isMatch();
    }

    /**
     * 20. 表示数值的字符串
     */
    @GetMapping(value = "/is/number")
    public String isNumber() {
        return subjectService.isNumber();
    }

    /**
     * 21. 调整数组顺序使奇数位于偶数前面
     */
    @GetMapping(value = "/exchange")
    public String exchange() {
        return subjectService.exchange();
    }

    /**
     * 22. 链表中倒数第k个节点
     */
    @GetMapping(value = "/getKthFromEnd")
    public String getKthFromEnd() {
        return subjectService.getKthFromEnd();
    }

    /**
     * 24. 反转链表
     */
    @GetMapping(value = "/reverse/list")
    public String reverseList() {
        return subjectService.reverseList();
    }

    /**
     * 25. 合并两个排序的链表
     */
    @GetMapping(value = "/merge/two/lists/24")
    public String mergeTwoLists() {
        return subjectService.mergeTwoLists();
    }

    /**
     * 26. 树的子结构
     */
    @GetMapping(value = "/is/sub/structure")
    public String isSubStructure() {
        return subjectService.isSubStructure();
    }

    /**
     * 27. 二叉树的镜像
     */
    @GetMapping(value = "/mirror/tree")
    public String mirrorTree() {
        return subjectService.mirrorTree();
    }

    /**
     * 28. 对称的二叉树
     */
    @GetMapping(value = "/is/symmetric")
    public String isSymmetric() {
        return subjectService.isSymmetric();
    }

    /**
     * 29. 顺时针打印矩阵
     */
    @GetMapping(value = "/spiral/order")
    public String spiralOrder() {
        return subjectService.spiralOrder();
    }

    /**
     * 30. 包含min函数的栈
     */
    @GetMapping(value = "/min/stack")
    public String minStack() {
        return subjectService.minStack();
    }

    /**
     * 31. 栈的压入、弹出序列
     */
    @GetMapping(value = "/validate/stack/sequences")
    public String validateStackSequences() {
        return subjectService.validateStackSequences();
    }

    /**
     * 32 - I. 从上到下打印二叉树
     */
    @GetMapping(value = "/level/order")
    public String levelOrder() {
        return subjectService.levelOrder();
    }

    /**
     * 32 - II. 从上到下打印二叉树 II
     */
    @GetMapping(value = "/level/order/two")
    public String levelOrderTwo() {
        return subjectService.levelOrderTwo();
    }

    /**
     * 32 - III. 从上到下打印二叉树 III
     */
    @GetMapping(value = "/level/order/three")
    public String levelOrderThree() {
        return subjectService.levelOrderThree();
    }

    /**
     * 33. 二叉搜索树的后序遍历序列
     */
    @GetMapping(value = "/verify/post/order")
    public String verifyPostOrder() {
        return subjectService.verifyPostOrder();
    }

    /**
     * 34. 二叉树中和为某一值的路径
     */
    @GetMapping(value = "/path/sum")
    public String pathSum() {
        return subjectService.pathSum();
    }

    /**
     * 35. 复杂链表的复制
     */
    @GetMapping(value = "/copy/random/list")
    public String copyRandomList() {
        return subjectService.copyRandomList();
    }

    /**
     * 36. 二叉搜索树与双向链表
     */
    @GetMapping(value = "/tree/to/doubly/list")
    public String treeToDoublyList() {
        return subjectService.treeToDoublyList();
    }

    /**
     * 37. 序列化二叉树
     */
    @GetMapping(value = "/serialize/tree")
    public String serializeTree() {
        return subjectService.serializeTree();
    }

    /**
     * 38. 字符串的排列
     */
    @GetMapping(value = "/permutation")
    public String permutation() {
        return subjectService.permutation();
    }

    /**
     * 39. 数组中出现次数超过一半的数字
     */
    @GetMapping(value = "/majority/element")
    public String majorityElement() {
        return subjectService.majorityElement();
    }

    /**
     * 40. 最小的k个数
     */
    @GetMapping(value = "/get/least/numbers")
    public String getLeastNumbers() {
        return subjectService.getLeastNumbers();
    }

    /**
     * 41. 数据流中的中位数
     */
    @GetMapping(value = "/median/finder")
    public String medianFinder() {
        return subjectService.medianFinder();
    }

    /**
     * 42. 连续子数组的最大和
     */
    @GetMapping(value = "/max/sub/array")
    public String maxSubArray() {
        return subjectService.maxSubArray();
    }

    /**
     * 43. 1～n 整数中 1 出现的次数
     */
    @GetMapping(value = "/count/digit/one")
    public String countDigitOne() {
        return subjectService.countDigitOne();
    }
}

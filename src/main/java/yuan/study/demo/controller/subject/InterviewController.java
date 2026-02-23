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

    /**
     * 面试题 02.07. 链表相交
     * 同yuan.study.demo.controller.subject.OfferSubjectController#getIntersectionNode()重复 故跳过
     */

    /**
     * 面试题 02.08. 环路检测
     * 同yuan.study.demo.controller.subject.SubjectController#detectCycle()重复 故跳过
     */

    /**
     * 面试题 03.01. 三合一
     */
    @GetMapping(value = "/tripleInOne")
    public String tripleInOne() {
        return interviewService.tripleInOne();
    }

    /**
     * 面试题 03.02. 栈的最小值
     * 和yuan.study.demo.controller.subject.OfferSubjectController#minStack()重复 故跳过
     */

    /**
     * 面试题 03.03. 堆盘子
     */
    @GetMapping(value = "/stackOfPlates")
    public String stackOfPlates() {
        return interviewService.stackOfPlates();
    }

    /**
     * 面试题 03.04. 化栈为队
     * 同yuan.study.demo.controller.subject.SubjectController#myQueue(), 故跳过
     */

    /**
     * 面试题 03.05. 栈排序
     */
    @GetMapping(value = "/sortedStack")
    public String sortedStack() {
        return interviewService.sortedStack();
    }

    /**
     * 面试题 03.06. 动物收容所
     */
    @GetMapping(value = "/animalShelf")
    public String animalShelf() {
        return interviewService.animalShelf();
    }

    /**
     * 面试题 04.01. 节点间通路
     */
    @GetMapping(value = "/findWhetherExistsPath")
    public String findWhetherExistsPath() {
        return interviewService.findWhetherExistsPath();
    }

    /**
     * 面试题 04.02. 最小高度树
     * 同yuan.study.demo.controller.subject.SubjectController#sortedArrayToBST(), 故跳过
     */

    /**
     * 面试题 04.03. 特定深度节点链表
     */
    @GetMapping(value = "/listOfDepth")
    public String listOfDepth() {
        return interviewService.listOfDepth();
    }

    /**
     * 面试题 04.04. 检查平衡性
     * 同yuan.study.demo.service.subjectService.impl.OfferSubjectServiceImpl#isBalanced(), 故跳过
     */

    /**
     * 面试题 04.05. 合法二叉搜索树
     * 同yuan.study.demo.controller.subject.SubjectController#isValidBST(), 故跳过
     */

    /**
     * 面试题 04.06. 后继者
     */
    @GetMapping(value = "/inorderSuccessor")
    public String inorderSuccessor() {
        return interviewService.inorderSuccessor();
    }

    /**
     * 面试题 04.08. 首个共同祖先
     */
    @GetMapping(value = "/lowestCommonAncestor")
    public String lowestCommonAncestor() {
        return interviewService.lowestCommonAncestor();
    }

    /**
     * 面试题 04.09. 二叉搜索树序列
     */
    @GetMapping(value = "/BSTSequences")
    public String BSTSequences() {
        return interviewService.BSTSequences();
    }

    /**
     * 面试题 04.10. 检查子树
     */
    @GetMapping(value = "/checkSubTree")
    public String checkSubTree() {
        return interviewService.checkSubTree();
    }

    /**
     * 面试题 04.12. 求和路径
     * 同yuan.study.demo.service.subjectService.impl.SubjectServiceImpl#pathSum, 故跳过
     */

    /**
     * 面试题 05.01. 插入
     */
    @GetMapping(value = "/insertBits")
    public String insertBits() {
        return interviewService.insertBits();
    }

    /**
     * 面试题 05.02. 二进制数转字符串
     */
    @GetMapping(value = "/printBin")
    public String printBin() {
        return interviewService.printBin();
    }

    /**
     * 面试题 05.03. 翻转数位
     */
    @GetMapping(value = "/reverseBits0503")
    public String reverseBits() {
        return interviewService.reverseBits();
    }

    /**
     * 面试题 05.04. 下一个数
     */
    @GetMapping(value = "/findClosedNumbers")
    public String findClosedNumbers() {
        return interviewService.findClosedNumbers();
    }

    /**
     * 面试题 05.06. 整数转换
     */
    @GetMapping(value = "/convertInteger")
    public String convertInteger() {
        return interviewService.convertInteger();
    }

    /**
     * 面试题 05.07. 配对交换
     */
    @GetMapping(value = "/exchangeBits")
    public String exchangeBits() {
        return interviewService.exchangeBits();
    }

    /**
     * 面试题 05.08. 绘制直线
     */
    @GetMapping(value = "/drawLine")
    public String drawLine() {
        return interviewService.drawLine();
    }

    /**
     * 面试题 08.01. 三步问题
     */
    @GetMapping(value = "/waysToStep")
    public String waysToStep() {
        return interviewService.waysToStep();
    }

    /**
     * 面试题 08.02. 迷路的机器人
     */
    @GetMapping(value = "/pathWithObstacles")
    public String pathWithObstacles() {
        return interviewService.pathWithObstacles();
    }

    /**
     * 面试题 08.03. 魔术索引
     */
    @GetMapping(value = "/findMagicIndex")
    public String findMagicIndex() {
        return interviewService.findMagicIndex();
    }

    /**
     * 面试题 08.04. 幂集
     * 同yuan.study.demo.controller.subject.SubjectController#subsets(), 故跳过
     */

    /**
     * 面试题 08.05. 递归乘法
     */
    @GetMapping(value = "/multiply0805")
    public String multiply() {
        return interviewService.multiply();
    }

    /**
     * 面试题 08.06. 汉诺塔问题
     */
    @GetMapping(value = "/hanota")
    public String hanota() {
        return interviewService.hanota();
    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     * 同yuan.study.demo.service.subjectService.impl.OfferSubjectServiceImpl#permutation(),故跳过
     */

    /**
     * 面试题 08.08. 有重复字符串的排列组合
     */
    @GetMapping(value = "/permutation0808")
    public String permutation() {
        return interviewService.permutation();
    }

    /**
     * 面试题 08.09. 括号
     * 同yuan.study.demo.controller.subject.SubjectController#generateParenthesis(), 故跳过
     */

    /**
     * 面试题 08.10. 颜色填充
     */
    @GetMapping(value = "/floodFill")
    public String floodFill() {
        return interviewService.floodFill();
    }

    /**
     * 面试题 08.11. 硬币
     */
    @GetMapping(value = "/waysToChange")
    public String waysToChange() {
        return interviewService.waysToChange();
    }

    /**
     * 面试题 08.12. 八皇后
     * 同yuan.study.demo.controller.subject.SubjectController#solveNQueens(), 故跳过
     */

    /**
     * 面试题 08.13. 堆箱子
     */
    @GetMapping(value = "/pileBox")
    public String pileBox() {
        return interviewService.pileBox();
    }

    /**
     * 面试题 08.14. 布尔运算
     */
    @GetMapping(value = "/countEval")
    public String countEval() {
        return interviewService.countEval();
    }

    /**
     * 面试题 10.01. 合并排序的数组
     * 同yuan.study.demo.controller.subject.SubjectController#merge88(), 故跳过
     */

    /**
     * 面试题 10.02. 变位词组
     * 同yuan.study.demo.controller.subject.SubjectController#groupAnagrams(), 故跳过
     */

    /**
     * 面试题 10.03. 搜索旋转数组
     */
    @GetMapping(value = "/search1003")
    public String search() {
        return interviewService.search();
    }

    /**
     * 面试题 10.05. 稀疏数组搜索
     */
    @GetMapping(value = "/findString")
    public String findString() {
        return interviewService.findString();
    }

    /**
     * 面试题 10.09. 排序矩阵查找
     * 同yuan.study.demo.controller.subject.OfferSubjectController#findNumberIn2DArray(), 故跳过
     */

    /**
     * 面试题 10.10. 数字流的秩
     */
    @GetMapping(value = "/streamRank")
    public String streamRank() {
        return interviewService.streamRank();
    }

    /**
     * 面试题 10.11. 峰与谷
     * 同yuan.study.demo.controller.subject.SubjectController#wiggleSort(), 故跳过
     */

    /**
     * 面试题 16.01. 交换数字
     */
    @GetMapping(value = "/swapNumbers")
    public String swapNumbers() {
        return interviewService.swapNumbers();
    }

    /**
     * 面试题 16.02. 单词频率
     */
    @GetMapping(value = "/wordsFrequency")
    public String wordsFrequency() {
        return interviewService.wordsFrequency();
    }

    /**
     * 面试题 16.03. 交点
     */
    @GetMapping(value = "/intersection1603")
    public String intersection() {
        return interviewService.intersection();
    }

    /**
     * 面试题 16.04. 井字游戏
     */
    @GetMapping(value = "/tictactoe")
    public String tictactoe() {
        return interviewService.tictactoe();
    }

    /**
     * 面试题 16.05. 阶乘尾数
     */
    @GetMapping(value = "/trailingZeroes1605")
    public String trailingZeroes() {
        return interviewService.trailingZeroes();
    }

    /**
     * 面试题 16.06. 最小差
     */
    @GetMapping(value = "/smallestDifference")
    public String smallestDifference() {
        return interviewService.smallestDifference();
    }

    /**
     * 面试题 16.07. 最大数值
     */
    @GetMapping(value = "/maximum")
    public String maximum() {
        return interviewService.maximum();
    }

    /**
     * 面试题 16.08. 整数的英语表示
     * 同yuan.study.demo.controller.subject.SubjectController#numberToWords(), 故跳过
     */

    /**
     * 面试题 16.09. 运算
     */
    @GetMapping(value = "/operations")
    public String operations() {
        return interviewService.operations();
    }

    /**
     * 面试题 16.10. 生存人数
     */
    @GetMapping(value = "/maxAliveYear")
    public String maxAliveYear() {
        return interviewService.maxAliveYear();
    }

    /**
     * 面试题 16.11. 跳水板
     */
    @GetMapping(value = "/divingBoard")
    public String divingBoard() {
        return interviewService.divingBoard();
    }

    /**
     * 面试题 16.13. 平分正方形
     */
    @GetMapping(value = "/cutSquares")
    public String cutSquares() {
        return interviewService.cutSquares();
    }

    /**
     * 面试题 16.15. 珠玑妙算
     */
    @GetMapping(value = "/masterMind")
    public String masterMind() {
        return interviewService.masterMind();
    }

    /**
     * 面试题 16.16. 部分排序
     */
    @GetMapping(value = "/subSort")
    public String subSort() {
        return interviewService.subSort();
    }

    /**
     * 面试题 16.17. 连续数列
     */
    @GetMapping(value = "/maxSubArray")
    public String maxSubArray() {
        return interviewService.maxSubArray();
    }
}
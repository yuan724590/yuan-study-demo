package yuan.study.demo.controller.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.subjectService.SubjectService;

import javax.annotation.Resource;

@RestController
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 题目测试
     */
    @GetMapping(value = "/subjectTest")
    public void subjectTest() {
        subjectService.subjectTest();
    }

    /**
     * 1. 两数之和
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#twoSum() 故跳过
     */

    /**
     * 相加两个数
     */
    @GetMapping(value = "/addTwoNumbers")
    public void addTwoNumbers() {
        subjectService.addTwoNumbers();
    }

    /**
     * 无重复字符的最长子串
     */
    @GetMapping(value = "/longestSubstringWithoutRepeatedCharacters")
    public void longestSubstringWithoutRepeatedCharacters() {
        subjectService.longestSubstringWithoutRepeatedCharacters();
    }

    /**
     * 寻找两个正序数组的中位数
     */
    @GetMapping(value = "/query/positiveArray/median")
    public void queryPositiveArrayMedian() {
        subjectService.queryPositiveArrayMedian();
    }

    /**
     * 寻找两个正序数组的中位数
     */
    @GetMapping(value = "/get/longest/palindromeString")
    public void getTheLongestPalindromeString() {
        subjectService.getTheLongestPalindromeString();
    }

    /**
     * Z字形变换
     */
    @GetMapping(value = "/zigzag/transformation")
    public void zigzagTransformation() {
        subjectService.zigzagTransformation();
    }

    /**
     * 整数反转
     */
    @GetMapping(value = "/integer/inversion")
    public void integerInversion() {
        subjectService.integerInversion();
    }

    /**
     * 字符串转换整数
     */
    @GetMapping(value = "/convert/string/number")
    public void convertStringNumber() {
        subjectService.convertStringNumber();
    }

    /**
     * 回文数
     */
    @GetMapping(value = "/palindrome/number")
    public void palindromeNumber() {
        subjectService.palindromeNumber();
    }

    /**
     * 10 - 正则表达式匹配
     */
    @GetMapping(value = "/regular/expression/matching")
    public void regularExpressionMatching(){
        subjectService.regularExpressionMatching();
    }

    /**
     * 盛最多水的容器
     */
    @GetMapping(value = "/hold/most/water")
    public void holdMostWater(){
        subjectService.holdMostWater();
    }

    /**
     * 整数转罗马数字
     */
    @GetMapping(value = "/int/to/roman")
    public void IntToRoman(){
        subjectService.intToRoman();
    }

    /**
     * 罗马数字转整数
     */
    @GetMapping(value = "/roman/to/int")
    public void romanToInt(){
        subjectService.romanToInt();
    }

    /**
     * 最长公共前缀
     */
    @GetMapping(value = "/longest/common/prefix")
    public void longestCommonPrefix(){
        subjectService.longestCommonPrefix();
    }

    /**
     * 15.三数之和
     */
    @GetMapping(value = "/three/sum")
    public void threeSum(){
        subjectService.threeSum();
    }

    /**
     * 最接近的三数之和
     */
    @GetMapping(value = "/three/sum/closet")
    public void threeSumClosest(){
        subjectService.threeSumClosest();
    }

    /**
     * 电话号码的字母组合
     */
    @GetMapping(value = "/letter/combinations")
    public void letterCombinations(){
        subjectService.letterCombinations();
    }

    /**
     * 四数之和
     */
    @GetMapping(value = "/four/sum")
    public void fourSum(){
        subjectService.fourSum();
    }

    /**
     * 删除链表的倒数第 N 个结点
     */
    @GetMapping(value = "/remove/nth/from/end")
    public void removeNthFromEnd(){
        subjectService.removeNthFromEnd();
    }

    /**
     * 20 - 有效的括号
     */
    @GetMapping(value = "/is/valid/brackets")
    public void isValidBrackets(){
        subjectService.isValidBrackets();
    }

    /**
     * 合并两个有序链表
     */
    @GetMapping(value = "/merge/two/lists")
    public void mergeTwoLists(){
        subjectService.mergeTwoLists();
    }

    /**
     * 括号生成
     */
    @GetMapping(value = "/generate/parenthesis")
    public void generateParenthesis(){
        subjectService.generateParenthesis();
    }

    /**
     * 合并k个有序链表
     */
    @GetMapping(value = "/merge/k/lists")
    public void mergeKLists(){
        subjectService.mergeKLists();
    }

    /**
     * 两两交换链表中的节点
     */
    @GetMapping(value = "/swap/pairs")
    public void swapPairs(){
        subjectService.swapPairs();
    }

    /**
     * 25. K 个一组翻转链表
     */
    @GetMapping(value = "/reverse/k/group")
    public void reverseKGroup(){
        subjectService.reverseKGroup();
    }

    /**
     * 删除有序数组中的重复项
     */
    @GetMapping(value = "/remove/duplicates")
    public void removeDuplicates(){
        subjectService.removeDuplicates();
    }

    /**
     * 移除元素
     */
    @GetMapping(value = "/remove/element")
    public void removeElement(){
        subjectService.removeElement();
    }

    /**
     * 找出字符串中第一个匹配项的下标
     */
    @GetMapping(value = "/find/str/index")
    public void findStrIndex(){
        subjectService.findStrIndex();
    }

    /**
     * 两数相除
     */
    @GetMapping(value = "/divide")
    public void divide(){
        subjectService.divide();
    }

    /**
     * 30 - 串联所有单词的子串
     */
    @GetMapping(value = "/findSubstring")
    public void findSubstring(){
        subjectService.findSubstring();
    }

    /**
     * 下一个排列
     */
    @GetMapping(value = "/nextPermutation")
    public void nextPermutation(){
        subjectService.nextPermutation();
    }

    /**
     * 最长有效括号
     */
    @GetMapping(value = "/longestValidParentheses")
    public void longestValidParentheses(){
        subjectService.longestValidParentheses();
    }

    /**
     * 搜索旋转排序数组
     */
    @GetMapping(value = "/search")
    public void search(){
        subjectService.search();
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     */
    @GetMapping(value = "/searchRange")
    public void searchRange(){
        subjectService.searchRange();
    }

    /**
     * 36. 有效的数独
     */
    @GetMapping(value = "/isValidSudoku")
    public void isValidSudoku(){
        subjectService.isValidSudoku();
    }

    /**
     * 37. 解数独
     */
    @GetMapping(value = "/solveSudoku")
    public void solveSudoku(){
        subjectService.solveSudoku();
    }

    /**
     * 42. 接雨水
     */
    @GetMapping(value = "/trap")
    public String maxSubArray() {
        return subjectService.trap();
    }

    /**
     * 50. Pow(x, n)
     * 同yuan.study.demo.controller.subject.OfferSubjectController#myPow() 故跳过
     */

    /**
     * 51. N 皇后
     */
    @GetMapping(value = "/solve/n/queens")
    public String solveNQueens() {
        return subjectService.solveNQueens();
    }

    /**
     * 52. N 皇后 II
     */
    @GetMapping(value = "/total/n/queens")
    public String totalNQueens() {
        return subjectService.totalNQueens();
    }

    /**
     * 69. x 的平方根
     */
    @GetMapping(value = "/mySqrt")
    public String mySqrt() {
        return subjectService.mySqrt();
    }

    /**
     * 70. 爬楼梯
     */
    @GetMapping(value = "/climbStairs")
    public String climbStairs() {
        return subjectService.climbStairs();
    }

    /**
     * 79. 单词搜索
     */
    @GetMapping(value = "/exist79")
    public String exist() {
        return subjectService.exist();
    }

    /**
     * 98. 验证二叉搜索树
     */
    @GetMapping(value = "/is/valid/bst")
    public String isValidBST() {
        return subjectService.isValidBST();
    }

    /**
     * 102. 二叉树的层序遍历
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#levelOrder() 故跳过
     */

    /**
     * 104. 二叉树的最大深度
     * 同yuan.study.demo.controller.subject.OfferSubjectController#maxDepth() 故跳过
     */

    /**
     * 111. 二叉树的最小深度
     */
    @GetMapping(value = "/min/depth")
    public String minDepth() {
        return subjectService.minDepth();
    }

    /**
     * 120. 三角形最小路径和
     */
    @GetMapping(value = "/minimumTotal")
    public String minimumTotal() {
        return subjectService.minimumTotal();
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    @GetMapping(value = "/max/profit")
    public String maxProfit() {
        return subjectService.maxProfit();
    }

    /**
     * 122. 买卖股票的最佳时机 II
     */
    @GetMapping(value = "/max/profit2")
    public String maxProfit2() {
        return subjectService.maxProfit2();
    }

    /**
     * 123. 买卖股票的最佳时机 III
     */
    @GetMapping(value = "/max/profit3")
    public String maxProfit3() {
        return subjectService.maxProfit3();
    }

    /**
     * 141. 环形链表
     */
    @GetMapping(value = "/has/cycle")
    public void hasCycle(){
        subjectService.hasCycle();
    }

    /**
     * 142. 环形链表 II
     */
    @GetMapping(value = "/detect/cycle")
    public void detectCycle(){
        subjectService.detectCycle();
    }

    /**
     * 152. 乘积最大子数组
     */
    @GetMapping(value = "/maxProduct")
    public void maxProduct(){
        subjectService.maxProduct();
    }

    /**
     * 169. 多数元素
     * 同yuan.study.demo.controller.subject.OfferSubjectController#majorityElement() 故跳过
     */

    /**
     * 191. 位1的个数
     * 同yuan.study.demo.controller.subject.OfferSubjectController#hammingWeight() 故跳过
     */

    /**
     * 208. 实现 Trie (前缀树)
     */
    @GetMapping(value = "/trie")
    public void trie(){
        subjectService.trie();
    }

    /**
     * 212. 单词搜索 II
     */
    @GetMapping(value = "/findWords")
    public void findWords(){
        subjectService.findWords();
    }

    /**
     * 225. 用队列实现栈
     */
    @GetMapping(value = "/my/stack")
    public void myStack(){
        subjectService.myStack();
    }

    /**
     * 231. 2 的幂
     */
    @GetMapping(value = "/isPowerOfTwo")
    public void isPowerOfTwo(){
        subjectService.isPowerOfTwo();
    }

    /**
     * 232. 用栈实现队列
     */
    @GetMapping(value = "/my/queue")
    public void myQueue(){
        subjectService.myQueue();
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    @GetMapping(value = "/lowest/common/ancestor")
    public void lowestCommonAncestor(){
        subjectService.lowestCommonAncestor();
    }

    /**
     * 236. 二叉树的最近公共祖先
     */
    @GetMapping(value = "/lowest/common/ancestor2")
    public void lowestCommonAncestor2(){
        subjectService.lowestCommonAncestor2();
    }

    /**
     * 239. 滑动窗口最大值
     */
    @GetMapping(value = "/max/sliding/window")
    public void maxSlidingWindow(){
        subjectService.maxSlidingWindow();
    }

    /**
     * 242. 有效的字母异位词
     */
    @GetMapping(value = "/is/anagram")
    public void isAnagram(){
        subjectService.isAnagram();
    }

    /**
     * 312. 戳气球
     */
    @GetMapping(value = "/max/coins")
    public void maxCoins(){
        subjectService.maxCoins();
    }

    /**
     * 338. 比特位计数
     */
    @GetMapping(value = "/countBits")
    public void countBits(){
        subjectService.countBits();
    }

    /**
     * 703. 数据流中的第 K 大元素
     */
    @GetMapping(value = "/k/th/largest")
    public String kthLargest() {
        return subjectService.kthLargest();
    }
}

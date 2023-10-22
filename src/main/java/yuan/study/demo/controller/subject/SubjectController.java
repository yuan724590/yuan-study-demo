package yuan.study.demo.controller.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.subjectService.SubjectService;

import javax.annotation.Resource;

/**
 * Leetcode 默认类型的题
 * https://leetcode.cn/problemset/all/?page=1
 */
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
     * 2. 相加两个数
     */
    @GetMapping(value = "/addTwoNumbers")
    public void addTwoNumbers() {
        subjectService.addTwoNumbers();
    }

    /**
     * 3. 无重复字符的最长子串
     */
    @GetMapping(value = "/longestSubstringWithoutRepeatedCharacters")
    public void longestSubstringWithoutRepeatedCharacters() {
        subjectService.longestSubstringWithoutRepeatedCharacters();
    }

    /**
     * 4. 寻找两个正序数组的中位数
     */
    @GetMapping(value = "/query/positiveArray/median")
    public void queryPositiveArrayMedian() {
        subjectService.queryPositiveArrayMedian();
    }

    /**
     * 5. 最长回文子串
     */
    @GetMapping(value = "/get/longest/palindromeString")
    public void getTheLongestPalindromeString() {
        subjectService.getTheLongestPalindromeString();
    }

    /**
     * 6. Z字形变换
     */
    @GetMapping(value = "/zigzag/transformation")
    public void zigzagTransformation() {
        subjectService.zigzagTransformation();
    }

    /**
     * 7. 整数反转
     */
    @GetMapping(value = "/integer/inversion")
    public void integerInversion() {
        subjectService.integerInversion();
    }

    /**
     * 8. 字符串转换整数 (atoi)
     */
    @GetMapping(value = "/convert/string/number")
    public void convertStringNumber() {
        subjectService.convertStringNumber();
    }

    /**
     * 9. 回文数
     */
    @GetMapping(value = "/palindrome/number")
    public void palindromeNumber() {
        subjectService.palindromeNumber();
    }

    /**
     * 10 - 正则表达式匹配
     * 同yuan.study.demo.controller.subject.OfferSubjectController#isMatch() 故跳过
     */
    @GetMapping(value = "/regular/expression/matching")
    public void regularExpressionMatching(){
        subjectService.regularExpressionMatching();
    }

    /**
     * 11. 盛最多水的容器
     */
    @GetMapping(value = "/hold/most/water")
    public void holdMostWater(){
        subjectService.holdMostWater();
    }

    /**
     * 12. 整数转罗马数字
     */
    @GetMapping(value = "/int/to/roman")
    public void IntToRoman(){
        subjectService.intToRoman();
    }

    /**
     * 13. 罗马数字转整数
     */
    @GetMapping(value = "/roman/to/int")
    public void romanToInt(){
        subjectService.romanToInt();
    }

    /**
     * 14. 最长公共前缀
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
     * 16. 最接近的三数之和
     */
    @GetMapping(value = "/three/sum/closet")
    public void threeSumClosest(){
        subjectService.threeSumClosest();
    }

    /**
     * 17. 电话号码的字母组合
     */
    @GetMapping(value = "/letter/combinations")
    public void letterCombinations(){
        subjectService.letterCombinations();
    }

    /**
     * 18. 四数之和
     */
    @GetMapping(value = "/four/sum")
    public void fourSum(){
        subjectService.fourSum();
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     */
    @GetMapping(value = "/remove/nth/from/end")
    public void removeNthFromEnd(){
        subjectService.removeNthFromEnd();
    }

    /**
     * 20. 有效的括号
     */
    @GetMapping(value = "/is/valid/brackets")
    public void isValidBrackets(){
        subjectService.isValidBrackets();
    }

    /**
     * 21. 合并两个有序链表
     */
    @GetMapping(value = "/merge/two/lists")
    public void mergeTwoLists(){
        subjectService.mergeTwoLists();
    }

    /**
     * 22. 括号生成
     */
    @GetMapping(value = "/generate/parenthesis")
    public void generateParenthesis(){
        subjectService.generateParenthesis();
    }

    /**
     * 23. 合并k个有序链表
     */
    @GetMapping(value = "/merge/k/lists")
    public void mergeKLists(){
        subjectService.mergeKLists();
    }

    /**
     * 24. 两两交换链表中的节点
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
     * 26. 删除有序数组中的重复项
     */
    @GetMapping(value = "/remove/duplicates")
    public void removeDuplicates(){
        subjectService.removeDuplicates();
    }

    /**
     * 27. 移除元素
     */
    @GetMapping(value = "/remove/element")
    public void removeElement(){
        subjectService.removeElement();
    }

    /**
     * 28. 找出字符串中第一个匹配项的下标
     */
    @GetMapping(value = "/find/str/index")
    public void findStrIndex(){
        subjectService.findStrIndex();
    }

    /**
     * 29. 两数相除
     */
    @GetMapping(value = "/divide")
    public void divide(){
        subjectService.divide();
    }

    /**
     * 30. 串联所有单词的子串
     */
    @GetMapping(value = "/findSubstring")
    public void findSubstring(){
        subjectService.findSubstring();
    }

    /**
     * 31. 下一个排列
     */
    @GetMapping(value = "/nextPermutation")
    public void nextPermutation(){
        subjectService.nextPermutation();
    }

    /**
     * 32. 最长有效括号
     */
    @GetMapping(value = "/longestValidParentheses")
    public void longestValidParentheses(){
        subjectService.longestValidParentheses();
    }

    /**
     * 33. 搜索旋转排序数组
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
     * 35. 搜索插入位置
     */
    @GetMapping(value = "/searchInsert")
    public void searchInsert(){
        subjectService.searchInsert();
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
     * 38. 外观数列
     */
    @GetMapping(value = "/countAndSay")
    public void countAndSay(){
        subjectService.countAndSay();
    }

    /**
     * 39. 组合总和
     */
    @GetMapping(value = "/combinationSum")
    public void combinationSum(){
        subjectService.combinationSum();
    }

    /**
     * 40. 组合总和 II
     */
    @GetMapping(value = "/combinationSum2")
    public void combinationSum2(){
        subjectService.combinationSum2();
    }

    /**
     * 41. 缺失的第一个正数
     */
    @GetMapping(value = "/firstMissingPositive")
    public void firstMissingPositive(){
        subjectService.firstMissingPositive();
    }

    /**
     * 42. 接雨水
     */
    @GetMapping(value = "/trap")
    public String trap() {
        return subjectService.trap();
    }

    /**
     * 43. 字符串相乘
     */
    @GetMapping(value = "/multiply")
    public String multiply() {
        return subjectService.multiply();
    }

    /**
     * 44. 通配符匹配
     */
    @GetMapping(value = "/isMatch")
    public String isMatch() {
        return subjectService.isMatch();
    }

    /**
     * 45. 跳跃游戏 II
     */
    @GetMapping(value = "/jump")
    public String jump() {
        return subjectService.jump();
    }

    /**
     * 46. 全排列
     */
    @GetMapping(value = "/permute")
    public String permute() {
        return subjectService.permute();
    }

    /**
     * 47. 全排列 II
     */
    @GetMapping(value = "/permuteUnique")
    public String permuteUnique() {
        return subjectService.permuteUnique();
    }

    /**
     * 48. 旋转图像
     */
    @GetMapping(value = "/rotate")
    public String rotate() {
        return subjectService.rotate();
    }

    /**
     * 49. 字母异位词分组
     */
    @GetMapping(value = "/groupAnagrams")
    public String groupAnagrams() {
        return subjectService.groupAnagrams();
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
     * 53. 最大子数组和
     * 同yuan.study.demo.controller.subject.OfferSubjectController#maxSubArray()故跳过
     */

    /**
     * 54. 螺旋矩阵
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#spiralOrder()故跳过
     */

    /**
     * 55. 跳跃游戏
     */
    @GetMapping(value = "/canJump")
    public String canJump() {
        return subjectService.canJump();
    }

    /**
     * 56. 合并区间
     */
    @GetMapping(value = "/merge")
    public String merge() {
        return subjectService.merge();
    }

    /**
     * 57. 插入区间
     */
    @GetMapping(value = "/insert")
    public String insert() {
        return subjectService.insert();
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
     * 72. 编辑距离
     */
    @GetMapping(value = "/minDistance")
    public String minDistance() {
        return subjectService.minDistance();
    }

    /**
     * 79. 单词搜索
     */
    @GetMapping(value = "/exist79")
    public String exist() {
        return subjectService.exist();
    }

    /**
     * 85. 最大矩形
     */
    @GetMapping(value = "/maximalRectangle")
    public String maximalRectangle() {
        return subjectService.maximalRectangle();
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
     * 124. 二叉树中的最大路径和
     */
    @GetMapping(value = "/maxPathSum")
    public String maxPathSum() {
        return subjectService.maxPathSum();
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
     * 146. LRU 缓存
     */
    @GetMapping(value = "/lruCache")
    public void lruCache(){
        subjectService.lruCache();
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
     * 188. 买卖股票的最佳时机 IV
     */
    @GetMapping(value = "/max/profit4")
    public void maxProfit4(){
        subjectService.maxProfit4();
    }

    /**
     * 191. 位1的个数
     * 同yuan.study.demo.controller.subject.OfferSubjectController#hammingWeight() 故跳过
     */

    /**
     * 200. 岛屿数量
     */
    @GetMapping(value = "/numIslands")
    public void numIslands(){
        subjectService.numIslands();
    }

    /**
     * 207. 课程表
     */
    @GetMapping(value = "/canFinish")
    public void canFinish(){
        subjectService.canFinish();
    }

    /**
     * 208. 实现 Trie (前缀树)
     */
    @GetMapping(value = "/trie")
    public void trie(){
        subjectService.trie();
    }

    /**
     * 210. 课程表 II
     */
    @GetMapping(value = "/findOrder")
    public void findOrder(){
        subjectService.findOrder();
    }

    /**
     * 212. 单词搜索 II
     */
    @GetMapping(value = "/findWords")
    public void findWords(){
        subjectService.findWords();
    }

    /**
     * 215. 数组中的第K个最大元素
     * 同yuan.study.demo.controller.subject.SubjectController#kthLargest() 故跳过
     */

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
     * 300. 最长递增子序列
     */
    @GetMapping(value = "/lengthOfLIS")
    public void lengthOfLIS(){
        subjectService.lengthOfLIS();
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     */
    @GetMapping(value = "/max/profit5")
    public void maxProfit5(){
        subjectService.maxProfit5();
    }

    /**
     * 312. 戳气球
     */
    @GetMapping(value = "/max/coins")
    public void maxCoins(){
        subjectService.maxCoins();
    }

    /**
     * 322. 零钱兑换
     */
    @GetMapping(value = "/coinChange")
    public void coinChange(){
        subjectService.coinChange();
    }

    /**
     * 338. 比特位计数
     */
    @GetMapping(value = "/countBits")
    public void countBits(){
        subjectService.countBits();
    }

    /**
     * 407. 接雨水 II
     */
    @GetMapping(value = "/trapRainWater")
    public String trapRainWater() {
        return subjectService.trapRainWater();
    }

    /**
     * 703. 数据流中的第 K 大元素
     */
    @GetMapping(value = "/k/th/largest")
    public String kthLargest() {
        return subjectService.kthLargest();
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     */
    @GetMapping(value = "/max/profit6")
    public String maxProfit6() {
        return subjectService.maxProfit6();
    }

    /**
     * 845. 数组中的最长山脉
     */
    @GetMapping(value = "/longestMountain")
    public String longestMountain() {
        return subjectService.longestMountain();
    }

    /**
     * 852. 山脉数组的峰顶索引
     */
    @GetMapping(value = "/peakIndexInMountainArray")
    public String peakIndexInMountainArray() {
        return subjectService.peakIndexInMountainArray();
    }

    /**
     * 906. 超级回文数
     */
    @GetMapping(value = "/superpalindromesInRange")
    public String superpalindromesInRange() {
        return subjectService.superpalindromesInRange();
    }

    /**
     * 941. 有效的山脉数组
     */
    @GetMapping(value = "/validMountainArray")
    public String validMountainArray() {
        return subjectService.validMountainArray();
    }

    /**
     * 1095. 山脉数组中查找目标值
     */
    @GetMapping(value = "/findInMountainArray")
    public String findInMountainArray() {
        return subjectService.findInMountainArray();
    }

    /**
     * 1143. 最长公共子序列
     */
    @GetMapping(value = "/longestCommonSubsequence")
    public String longestCommonSubsequence() {
        return subjectService.longestCommonSubsequence();
    }
}

package yuan.study.demo.controller.subject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.subjectService.SubjectService;

import javax.annotation.Resource;

/**
 * Leetcode 默认类型的题
 * total: 235(2024.4.21)
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
    public void intToRoman(){
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
     * 58. 最后一个单词的长度
     */
    @GetMapping(value = "/lengthOfLastWord")
    public String lengthOfLastWord() {
        return subjectService.lengthOfLastWord();
    }

    /**
     * 59. 螺旋矩阵 II
     */
    @GetMapping(value = "/generateMatrix")
    public String generateMatrix() {
        return subjectService.generateMatrix();
    }

    /**
     * 60. 排列序列
     */
    @GetMapping(value = "/getPermutation")
    public String getPermutation() {
        return subjectService.getPermutation();
    }

    /**
     * 61. 旋转链表
     */
    @GetMapping(value = "/rotateRight")
    public String rotateRight() {
        return subjectService.rotateRight();
    }

    /**
     * 62. 不同路径
     */
    @GetMapping(value = "/uniquePaths")
    public String uniquePaths() {
        return subjectService.uniquePaths();
    }

    /**
     * 63. 不同路径 II
     */
    @GetMapping(value = "/uniquePathsWithObstacles")
    public String uniquePathsWithObstacles() {
        return subjectService.uniquePathsWithObstacles();
    }

    /**
     * 64. 最小路径和
     */
    @GetMapping(value = "/minPathSum")
    public String minPathSum() {
        return subjectService.minPathSum();
    }

    /**
     * 65. 有效数字
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#isNumber()
     */

    /**
     * 66. 加一
     */
    @GetMapping(value = "/plusOne")
    public String plusOne() {
        return subjectService.plusOne();
    }

    /**
     * 67. 二进制求和
     */
    @GetMapping(value = "/addBinary")
    public String addBinary() {
        return subjectService.addBinary();
    }

    /**
     * 68. 文本左右对齐
     */
    @GetMapping(value = "/fullJustify")
    public String fullJustify() {
        return subjectService.fullJustify();
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
     * 71. 简化路径
     */
    @GetMapping(value = "/simplifyPath")
    public String simplifyPath() {
        return subjectService.simplifyPath();
    }

    /**
     * 72. 编辑距离
     */
    @GetMapping(value = "/minDistance")
    public String minDistance() {
        return subjectService.minDistance();
    }

    /**
     * 73. 矩阵置零
     */
    @GetMapping(value = "/setZeroes")
    public String setZeroes() {
        return subjectService.setZeroes();
    }

    /**
     * 74. 搜索二维矩阵
     * 同yuan.study.demo.controller.subject.OfferSubjectController#findNumberIn2DArray()
     */

    /**
     * 75. 颜色分类
     */
    @GetMapping(value = "/sortColors")
    public String sortColors() {
        return subjectService.sortColors();
    }

    /**
     * 76. 最小覆盖子串
     */
    @GetMapping(value = "/minWindow")
    public String minWindow() {
        return subjectService.minWindow();
    }

    /**
     * 77. 组合
     */
    @GetMapping(value = "/combine")
    public String combine() {
        return subjectService.combine();
    }

    /**
     * 78. 子集
     */
    @GetMapping(value = "/subsets")
    public String subsets() {
        return subjectService.subsets();
    }

    /**
     * 79. 单词搜索
     */
    @GetMapping(value = "/exist79")
    public String exist() {
        return subjectService.exist();
    }

    /**
     * 80. 删除有序数组中的重复项 II
     */
    @GetMapping(value = "/removeDuplicates80")
    public String removeDuplicates80() {
        return subjectService.removeDuplicates80();
    }

    /**
     * 81. 搜索旋转排序数组 II
     */
    @GetMapping(value = "/search81")
    public String search81() {
        return subjectService.search81();
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     */
    @GetMapping(value = "/deleteDuplicates")
    public String deleteDuplicates() {
        return subjectService.deleteDuplicates();
    }

    /**
     * 83. 删除排序链表中的重复元素
     */
    @GetMapping(value = "/deleteDuplicates83")
    public String deleteDuplicates83() {
        return subjectService.deleteDuplicates83();
    }

    /**
     * 84. 柱状图中最大的矩形
     */
    @GetMapping(value = "/largestRectangleArea")
    public String largestRectangleArea() {
        return subjectService.largestRectangleArea();
    }

    /**
     * 85. 最大矩形
     */
    @GetMapping(value = "/maximalRectangle")
    public String maximalRectangle() {
        return subjectService.maximalRectangle();
    }

    /**
     * 86. 分隔链表
     */
    @GetMapping(value = "/partition")
    public String partition() {
        return subjectService.partition();
    }

    /**
     * 87. 扰乱字符串
     */
    @GetMapping(value = "/isScramble")
    public String isScramble() {
        return subjectService.isScramble();
    }

    /**
     * 88. 合并两个有序数组
     */
    @GetMapping(value = "/merge88")
    public String merge88() {
        return subjectService.merge88();
    }

    /**
     * 89. 格雷编码
     */
    @GetMapping(value = "/grayCode")
    public String grayCode() {
        return subjectService.grayCode();
    }

    /**
     * 90. 子集 II
     * 解题思路相近
     */

    /**
     * 91. 解码方法
     * 同yuan.study.demo.controller.subject.OfferSubjectController#translateNum(), 故跳过
     */

    /**
     * 92. 反转链表 II
     */
    @GetMapping(value = "/reverseBetween")
    public String reverseBetween() {
        return subjectService.reverseBetween();
    }

    /**
     * 93. 复原 IP 地址
     */
    @GetMapping(value = "/restoreIpAddresses")
    public String restoreIpAddresses() {
        return subjectService.restoreIpAddresses();
    }

    /**
     * 94. 二叉树的中序遍历
     */
    @GetMapping(value = "/inorderTraversal")
    public String inorderTraversal() {
        return subjectService.inorderTraversal();
    }

    /**
     * 95. 不同的二叉搜索树 II
     */
    @GetMapping(value = "/generateTrees")
    public String generateTrees() {
        return subjectService.generateTrees();
    }

    /**
     * 96. 不同的二叉搜索树
     */
    @GetMapping(value = "/numTrees")
    public String numTrees() {
        return subjectService.numTrees();
    }

    /**
     * 97. 交错字符串
     */
    @GetMapping(value = "/isInterleave")
    public String isInterleave() {
        return subjectService.isInterleave();
    }

    /**
     * 98. 验证二叉搜索树
     */
    @GetMapping(value = "/is/valid/bst")
    public String isValidBST() {
        return subjectService.isValidBST();
    }

    /**
     * 99. 恢复二叉搜索树
     */
    @GetMapping(value = "/recoverTree")
    public String recoverTree() {
        return subjectService.recoverTree();
    }

    /**
     * 100. 相同的树
     */
    @GetMapping(value = "/isSameTree")
    public String isSameTree() {
        return subjectService.isSameTree();
    }

    /**
     * 101. 对称二叉树
     * 同yuan.study.demo.controller.subject.OfferSubjectController#isSymmetric(), 故跳过
     */

    /**
     * 102. 二叉树的层序遍历
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#levelOrder() 故跳过
     */

    /**
     * 103. 二叉树的锯齿形层序遍历
     */
    @GetMapping(value = "/zigzagLevelOrder")
    public String zigzagLevelOrder() {
        return subjectService.zigzagLevelOrder();
    }

    /**
     * 104. 二叉树的最大深度
     * 同yuan.study.demo.controller.subject.OfferSubjectController#maxDepth() 故跳过
     */

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 同yuan.study.demo.controller.subject.OfferSubjectController#buildTree(), 故跳过
     */

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     */
    @GetMapping(value = "/buildTree")
    public String buildTree() {
        return subjectService.buildTree();
    }

    /**
     * 107. 二叉树的层序遍历 II
     */
    @GetMapping(value = "/levelOrderBottom")
    public String levelOrderBottom() {
        return subjectService.levelOrderBottom();
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     */
    @GetMapping(value = "/sortedArrayToBST")
    public String sortedArrayToBST() {
        return subjectService.sortedArrayToBST();
    }

    /**
     * 109. 有序链表转换二叉搜索树
     */
    @GetMapping(value = "/sortedListToBST")
    public String sortedListToBST() {
        return subjectService.sortedListToBST();
    }

    /**
     * 110. 平衡二叉树
     * 同yuan.study.demo.service.subjectService.impl.OfferSubjectServiceImpl#isBalanced(), 故跳过
     */

    /**
     * 111. 二叉树的最小深度
     */
    @GetMapping(value = "/min/depth")
    public String minDepth() {
        return subjectService.minDepth();
    }

    /**
     * 112. 路径总和
     */
    @GetMapping(value = "/hasPathSum")
    public String hasPathSum() {
        return subjectService.hasPathSum();
    }

    /**
     * 113. 路径总和 II
     * 同yuan.study.demo.controller.subject.OfferSubjectController#pathSum(), 故跳过
     */

    /**
     * 114. 二叉树展开为链表
     */
    @GetMapping(value = "/flatten")
    public String flatten() {
        return subjectService.flatten();
    }

    /**
     * 115. 不同的子序列
     */
    @GetMapping(value = "/numDistinct")
    public String numDistinct() {
        return subjectService.numDistinct();
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     */
    @GetMapping(value = "/connect")
    public String connect() {
        return subjectService.connect();
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     */
    @GetMapping(value = "/connect117")
    public String connect117() {
        return subjectService.connect117();
    }

    /**
     * 118. 杨辉三角
     */
    @GetMapping(value = "/generate")
    public String generate() {
        return subjectService.generate();
    }

    /**
     * 119. 杨辉三角 II
     */
    @GetMapping(value = "/getRow")
    public String getRow() {
        return subjectService.getRow();
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
     * 125. 验证回文串
     */
    @GetMapping(value = "/isPalindrome")
    public String isPalindrome() {
        return subjectService.isPalindrome();
    }

    /**
     * 126. 单词接龙 II
     */
    @GetMapping(value = "/findLadders")
    public String findLadders() {
        return subjectService.findLadders();
    }

    /**
     * 127. 单词接龙
     */
    @GetMapping(value = "/ladderLength")
    public String ladderLength() {
        return subjectService.ladderLength();
    }

    /**
     * 128. 最长连续序列
     */
    @GetMapping(value = "/longestConsecutive")
    public String longestConsecutive() {
        return subjectService.longestConsecutive();
    }

    /**
     * 129. 求根节点到叶节点数字之和
     */
    @GetMapping(value = "/sumNumbers")
    public String sumNumbers() {
        return subjectService.sumNumbers();
    }

    /**
     * 130. 被围绕的区域
     */
    @GetMapping(value = "/solve")
    public String solve() {
        return subjectService.solve();
    }

    /**
     * 131. 分割回文串
     */
    @GetMapping(value = "/partition131")
    public String partition131() {
        return subjectService.partition131();
    }

    /**
     * 132. 分割回文串 II
     */
    @GetMapping(value = "/minCut")
    public String minCut() {
        return subjectService.minCut();
    }

    /**
     * 133. 克隆图
     */
    @GetMapping(value = "/cloneGraph")
    public String cloneGraph() {
        return subjectService.cloneGraph();
    }

    /**
     * 134. 加油站
     */
    @GetMapping(value = "/canCompleteCircuit")
    public String canCompleteCircuit() {
        return subjectService.canCompleteCircuit();
    }

    /**
     * 135. 分发糖果
     */
    @GetMapping(value = "/candy")
    public String candy() {
        return subjectService.candy();
    }

    /**
     * 136. 只出现一次的数字
     * 同yuan.study.demo.controller.subject.OfferSubjectController#singleNumber(), 故跳过
     */

    /**
     * 137. 只出现一次的数字 II
     * 同yuan.study.demo.controller.subject.OfferSubjectController#singleNumber(), 故跳过
     */

    /**
     * 138. 随机链表的复制
     */
    @GetMapping(value = "/copyRandomList")
    public void copyRandomList(){
        subjectService.copyRandomList();
    }

    /**
     * 139. 单词拆分
     */
    @GetMapping(value = "/wordBreak")
    public void wordBreak(){
        subjectService.wordBreak();
    }

    /**
     * 140. 单词拆分 II
     */
    @GetMapping(value = "/wordBreak140")
    public void wordBreak140(){
        subjectService.wordBreak140();
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
     * 143. 重排链表
     */
    @GetMapping(value = "/reorderList")
    public void reorderList(){
        subjectService.reorderList();
    }

    /**
     * 144. 二叉树的前序遍历
     */
    @GetMapping(value = "/preorderTraversal")
    public void preorderTraversal(){
        subjectService.preorderTraversal();
    }

    /**
     * 146. LRU 缓存
     */
    @GetMapping(value = "/lruCache")
    public void lruCache(){
        subjectService.lruCache();
    }

    /**
     * 147. 对链表进行插入排序
     */
    @GetMapping(value = "/insertionSortList")
    public void insertionSortList(){
        subjectService.insertionSortList();
    }

    /**
     * 148. 排序链表
     */
    @GetMapping(value = "/sortList")
    public void sortList(){
        subjectService.sortList();
    }

    /**
     * 149. 直线上最多的点数
     */
    @GetMapping(value = "/maxPoints")
    public void maxPoints(){
        subjectService.maxPoints();
    }

    /**
     * 150. 逆波兰表达式求值
     */
    @GetMapping(value = "/evalRPN")
    public void evalRPN(){
        subjectService.evalRPN();
    }

    /**
     * 151. 反转字符串中的单词
     * 同yuan.study.demo.controller.subject.OfferSubjectController#reverseWords(), 故跳过
     */

    /**
     * 152. 乘积最大子数组
     */
    @GetMapping(value = "/maxProduct")
    public void maxProduct(){
        subjectService.maxProduct();
    }

    /**
     * 160. 相交链表
     * 同yuan.study.demo.controller.subject.OfferSubjectController#getIntersectionNode()故跳过
     */

    /**
     * 162. 寻找峰值
     */
    @GetMapping(value = "/findPeakElement")
    public void findPeakElement(){
        subjectService.findPeakElement();
    }

    /**
     * 165. 比较版本号
     */
    @GetMapping(value = "/compareVersion")
    public void compareVersion(){
        subjectService.compareVersion();
    }

    /**
     * 166. 分数到小数
     */
    @GetMapping(value = "/fractionToDecimal")
    public void fractionToDecimal(){
        subjectService.fractionToDecimal();
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     */
    @GetMapping(value = "/twoSum167")
    public void twoSum167(){
        subjectService.twoSum167();
    }

    /**
     * 168. Excel表列名称
     */
    @GetMapping(value = "/convertToTitle")
    public void convertToTitle(){
        subjectService.convertToTitle();
    }

    /**
     * 169. 多数元素
     * 同yuan.study.demo.controller.subject.OfferSubjectController#majorityElement() 故跳过
     */

    /**
     * 171. Excel 表列序号
     */
    @GetMapping(value = "/titleToNumber")
    public void titleToNumber(){
        subjectService.titleToNumber();
    }

    /**
     * 172. 阶乘后的零
     */
    @GetMapping(value = "/trailingZeroes")
    public void trailingZeroes(){
        subjectService.trailingZeroes();
    }

    /**
     * 173. 二叉搜索树迭代器
     */
    @GetMapping(value = "/bstIterator")
    public void bstIterator(){
        subjectService.bstIterator();
    }

    /**
     * 174. 地下城游戏
     */
    @GetMapping(value = "/calculateMinimumHP")
    public void calculateMinimumHP(){
        subjectService.calculateMinimumHP();
    }

    /**
     * 175. 组合两个表
     * SELECT a.FirstName AS firstName, a.LastName AS lastName, b.City as city, b.State as state FROM Person a LEFT JOIN Address b ON a.PersonId = b.PersonId
     */

    /**
     * 176. 第二高的薪水
     * SELECT (SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT 1 OFFSET 1) AS SecondHighestSalary
     */

    /**
     * 177. 第N高的薪水
     * SELECT DISTINCT salary FROM Employee ORDER BY salary DESC LIMIT M, 1
     */

    /**
     * 178. 分数排名
     * SELECT s.score, DENSE_RANK() OVER (ORDER BY s.score DESC) AS 'rank' FROM scores s;
     */

    /**
     * 179. 最大数
     */
    @GetMapping(value = "/largestNumber")
    public void largestNumber(){
        subjectService.largestNumber();
    }

    /**
     * 180. 连续出现的数字
     * Logs -> id, num
     * 查询所有至少连续出现三次的数字
     * select distinct a.num as ConsecutiveNums  from logs a, logs b, logs c where a.id = b.id + 1 and a.id = c.id + 2 and a.num = b.num and a.num = c.num
     */

    /**
     * 181. 超过经理收入的员工
     * Employee -> name, salary, managerId
     * 查询挣得比经理多的雇员
     * SELECT a.name AS Employee FROM Employee a LEFT JOIN Employee b ON a.managerId = b.id WHERE  a.salary > b.salary
     */

    /**
     * 182. 查找重复的电子邮箱
     * Person -> id, email
     * select Email from Person group by Email having count(Email) > 1;
     */

    /**
     * 183. 从不订购的客户
     * Customers -> id, name
     * Orders -> id, customerId
     * 找出所有从不点任何东西的顾客。
     * SELECT a.name AS Customers FROM Customers a LEFT JOIN Orders b ON a.id = b.customerId WHERE b.id IS NULL
     */

    /**
     * 184. 部门工资最高的员工
     * Employee -> id, name, salary, departmentId
     * Department -> id, name
     * 查找出每个部门中薪资最高的员工。
     * SELECT a.name AS Department, r.name AS Employee, salary Salary FROM (SELECT `name`, departmentId, salary, DENSE_RANK() over(PARTITION BY departmentId ORDER BY salary DESC) ranking FROM Employee) AS r LEFT JOIN Department a ON r.departmentId = a.idWHERE r.ranking = 1
     */

    /**
     * 185. 部门工资前三高的所有员工
     * Employee -> id, name, salary, departmentId; Department -> id, name
     * 查询部门工资前三高的所有员工, 工资重复不影响排名
     * select c.name as Department, a.name as Employee,a.salary from employee a left join department c on a.departmentId = c.id where (select count(distinct(b.salary)) from employee b where a.departmentId = b.departmentId and a.salary < b.salary) < 3
     * select Department, Employee, salary from (select b.name as Department, a.name as Employee, a.salary as Salary, dense_rank() over(partition by departmentId order by salary desc) as ranks from Employee a left join Department b on a.departmentId = b.id) c where ranks <= 3
     */

    /**
     * 187. 重复的DNA序列
     */
    @GetMapping(value = "/findRepeatedDnaSequences")
    public void findRepeatedDnaSequences(){
        subjectService.findRepeatedDnaSequences();
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     */
    @GetMapping(value = "/max/profit4")
    public void maxProfit4(){
        subjectService.maxProfit4();
    }

    /**
     * 189. 轮转数组
     */
    @GetMapping(value = "/rotate189")
    public void rotate189(){
        subjectService.rotate189();
    }

    /**
     * 190. 颠倒二进制位
     */
    @GetMapping(value = "/reverseBits")
    public void reverseBits(){
        subjectService.reverseBits();
    }


    /**
     * 191. 位1的个数
     * 同yuan.study.demo.controller.subject.OfferSubjectController#hammingWeight() 故跳过
     */

    /**
     * 192. 统计词频
     * 写一个 bash 脚本以统计一个文本文件 words.txt 中每个单词出现的频率
     *
     * 为了简单起见，你可以假设：
     * 	words.txt只包括小写字母和 ' ' 。
     * 	每个单词只由小写字母组成。
     * 	单词间由一个或多个空格字符分隔。
     *
     * 示例:
     * 假设 words.txt 内容如下：
     * the day is sunny the the
     * the sunny is is
     *
     * 你的脚本应当输出（以词频降序排列）：
     * the 4
     * is 3
     * sunny 2
     * day 1
     *
     * 说明:
     * 不要担心词频相同的单词的排序问题，每个单词出现的频率都是唯一的。
     * 你可以使用一行 Unix pipes 实现吗？
     * cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -nr | awk '{print $2" "$1}'
     *      tr：用于转换或删除文件中的字符
     *      tr -s：缩减连续重复的字符成指定的单个字符
     *      uniq：用于检查及删除文本文件中重复出现的行列
     *      uniq -c：计算每个单词出现的次数
     *      sort -nr：根据出现次数对单词进行降序排序
     *      awk '{print $2" "$1}'：调整输出格式，使单词出现在前面，次数出现在后面
     */

    /**
     * 196. 删除重复的电子邮箱
     * Person -> id, email
     * 删除重复的电子邮箱,需要保留id较小的重复数据
     * DELETE p1 FROM Person p1, Person p2 WHERE p1.Email = p2.Email AND p1.Id > p2.Id
     */

    /**
     * 197. 上升的温度
     * Weather -> id, recordDate, temperature
     * 找出与昨天的日期相比温度更高的所有日期的 id
     * select w2.id from Weather w1, Weather w2 where w2.Temperature > w1.Temperature and datediff(w2.recordDate, w1.recordDate) = 1
     */

    /**
     * 198. 打家劫舍
     */
    @GetMapping(value = "/rob")
    public void rob(){
        subjectService.rob();
    }

    /**
     * 199. 二叉树的右视图
     */
    @GetMapping(value = "/rightSideView")
    public void rightSideView(){
        subjectService.rightSideView();
    }

    /**
     * 200. 岛屿数量
     */
    @GetMapping(value = "/numIslands")
    public void numIslands(){
        subjectService.numIslands();
    }

    /**
     * 201. 数字范围按位与
     */
    @GetMapping(value = "/rangeBitwiseAnd")
    public void rangeBitwiseAnd(){
        subjectService.rangeBitwiseAnd();
    }

    /**
     * 202. 快乐数
     */
    @GetMapping(value = "/isHappy")
    public void isHappy(){
        subjectService.isHappy();
    }

    /**
     * 203. 移除链表元素
     */
    @GetMapping(value = "/removeElements")
    public void removeElements(){
        subjectService.removeElements();
    }

    /**
     * 204. 计数质数
     */
    @GetMapping(value = "/countPrimes")
    public void countPrimes(){
        subjectService.countPrimes();
    }

    /**
     * 205. 同构字符串
     */
    @GetMapping(value = "/isIsomorphic")
    public void isIsomorphic(){
        subjectService.isIsomorphic();
    }

    /**
     * 206. 反转链表
     * 同yuan.study.demo.controller.subject.OfferSubjectController#reverseList(), 故跳过
     */

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
     * 209. 长度最小的子数组
     */
    @GetMapping(value = "/minSubArrayLen")
    public void minSubArrayLen(){
        subjectService.minSubArrayLen();
    }

    /**
     * 210. 课程表 II
     */
    @GetMapping(value = "/findOrder")
    public void findOrder(){
        subjectService.findOrder();
    }

    /**
     * 211. 添加与搜索单词 - 数据结构设计
     */
    @GetMapping(value = "/wordDictionary")
    public void wordDictionary(){
        subjectService.wordDictionary();
    }

    /**
     * 212. 单词搜索 II
     */
    @GetMapping(value = "/findWords")
    public void findWords(){
        subjectService.findWords();
    }

    /**
     * 213. 打家劫舍 II
     */
    @GetMapping(value = "/rob2")
    public void rob2(){
        subjectService.rob2();
    }

    /**
     * 214. 最短回文串
     */
    @GetMapping(value = "/shortestPalindrome")
    public void shortestPalindrome(){
        subjectService.shortestPalindrome();
    }

    /**
     * 215. 数组中的第K个最大元素
     * 同yuan.study.demo.controller.subject.SubjectController#kthLargest() 故跳过
     */

    /**
     * 216. 组合总和 III
     */
    @GetMapping(value = "/combinationSum3")
    public void combinationSum3(){
        subjectService.combinationSum3();
    }

    /**
     * 217. 存在重复元素
     */
    @GetMapping(value = "/containsDuplicate")
    public void containsDuplicate(){
        subjectService.containsDuplicate();
    }

    /**
     * 218. 天际线问题
     */
    @GetMapping(value = "/getSkyline")
    public void getSkyline(){
        subjectService.getSkyline();
    }

    /**
     * 219. 存在重复元素 II
     */
    @GetMapping(value = "/containsNearbyDuplicate")
    public void containsNearbyDuplicate(){
        subjectService.containsNearbyDuplicate();
    }

    /**
     * 220. 存在重复元素 III
     */
    @GetMapping(value = "/containsNearbyAlmostDuplicate")
    public void containsNearbyAlmostDuplicate(){
        subjectService.containsNearbyAlmostDuplicate();
    }

    /**
     * 221. 最大正方形
     */
    @GetMapping(value = "/maximalSquare")
    public void maximalSquare(){
        subjectService.maximalSquare();
    }

    /**
     * 222. 完全二叉树的节点个数
     */
    @GetMapping(value = "/countNodes")
    public void countNodes(){
        subjectService.countNodes();
    }

    /**
     * 223. 矩形面积
     */
    @GetMapping(value = "/computeArea")
    public void computeArea(){
        subjectService.computeArea();
    }

    /**
     * 224. 基本计算器
     */
    @GetMapping(value = "/calculate")
    public void calculate(){
        subjectService.calculate();
    }

    /**
     * 225. 用队列实现栈
     */
    @GetMapping(value = "/my/stack")
    public void myStack(){
        subjectService.myStack();
    }

    /**
     * 226. 翻转二叉树
     */
    @GetMapping(value = "/invertTree")
    public void invertTree(){
        subjectService.invertTree();
    }

    /**
     * 227. 基本计算器 II
     */
    @GetMapping(value = "/calculate227")
    public void calculate227(){
        subjectService.calculate227();
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     */
    @GetMapping(value = "/kthSmallest")
    public void kthSmallest(){
        subjectService.kthSmallest();
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
     * 238. 除自身以外数组的乘积
     */
    @GetMapping(value = "/productExceptSelf")
    public void productExceptSelf(){
        subjectService.productExceptSelf();
    }

    /**
     * 239. 滑动窗口最大值
     */
    @GetMapping(value = "/max/sliding/window")
    public void maxSlidingWindow(){
        subjectService.maxSlidingWindow();
    }

    /**
     * 241. 为运算表达式设计优先级
     */
    @GetMapping(value = "/diffWaysToCompute")
    public void diffWaysToCompute(){
        subjectService.diffWaysToCompute();
    }

    /**
     * 242. 有效的字母异位词
     */
    @GetMapping(value = "/is/anagram")
    public void isAnagram(){
        subjectService.isAnagram();
    }

    /**
     * 258. 各位相加
     */
    @GetMapping(value = "/addDigits")
    public void addDigits(){
        subjectService.addDigits();
    }
    /**
     * 264. 丑数 II
     * 同yuan.study.demo.service.subjectService.OfferSubjectService#nthUglyNumber(), 故跳过
     */

    /**
     * 268. 丢失的数字
     * 同yuan.study.demo.controller.subject.OfferSubjectController#missingNumber(), 故跳过
     */

    /**
     * 274. H 指数
     */
    @GetMapping(value = "/hIndex")
    public void hIndex(){
        subjectService.hIndex();
    }

    /**
     * 279. 完全平方数
     */
    @GetMapping(value = "/numSquares")
    public void numSquares(){
        subjectService.numSquares();
    }

    /**
     * 283. 移动零
     */
    @GetMapping(value = "/moveZeroes")
    public void moveZeroes(){
        subjectService.moveZeroes();
    }

    /**
     * 289. 生命游戏
     */
    @GetMapping(value = "/gameOfLife")
    public void gameOfLife(){
        subjectService.gameOfLife();
    }

    /**
     * 295. 数据流的中位数
     * 同yuan.study.demo.controller.subject.OfferSubjectController#medianFinder(),故跳过
     */

    /**
     * 300. 最长递增子序列
     */
    @GetMapping(value = "/lengthOfLIS")
    public void lengthOfLIS(){
        subjectService.lengthOfLIS();
    }

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     */
    @GetMapping(value = "/numMatrix")
    public void numMatrix(){
        subjectService.numMatrix();
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
     * 313. 超级丑数
     */
    @GetMapping(value = "/nthSuperUglyNumber")
    public void nthSuperUglyNumber(){
        subjectService.nthSuperUglyNumber();
    }

    /**
     * 315. 计算右侧小于当前元素的个数
     */
    @GetMapping(value = "/countSmaller")
    public void countSmaller(){
        subjectService.countSmaller();
    }

    /**
     * 316. 去除重复字母
     */
    @GetMapping(value = "/removeDuplicateLetters")
    public void removeDuplicateLetters(){
        subjectService.removeDuplicateLetters();
    }

    /**
     * 318. 最大单词长度乘积
     */
    @GetMapping(value = "/maxProduct318")
    public void maxProduct318(){
        subjectService.maxProduct318();
    }

    /**
     * 321. 拼接最大数
     */
    @GetMapping(value = "/maxNumber")
    public void maxNumber(){
        subjectService.maxNumber();
    }

    /**
     * 322. 零钱兑换
     */
    @GetMapping(value = "/coinChange")
    public void coinChange(){
        subjectService.coinChange();
    }

    /**
     * 324. 摆动排序 II
     */
    @GetMapping(value = "/wiggleSort")
    public void wiggleSort(){
        subjectService.wiggleSort();
    }

    /**
     * 328. 奇偶链表
     */
    @GetMapping(value = "/oddEvenList")
    public void oddEvenList(){
        subjectService.oddEvenList();
    }

    /**
     * 329. 矩阵中的最长递增路径
     */
    @GetMapping(value = "/longestIncreasingPath")
    public void longestIncreasingPath(){
        subjectService.longestIncreasingPath();
    }

    /**
     * 334. 递增的三元子序列
     */
    @GetMapping(value = "/increasingTriplet")
    public void increasingTriplet(){
        subjectService.increasingTriplet();
    }

    /**
     * 337. 打家劫舍 III
     */
    @GetMapping(value = "/rob3")
    public void rob3(){
        subjectService.rob3();
    }

    /**
     * 338. 比特位计数
     */
    @GetMapping(value = "/countBits")
    public void countBits(){
        subjectService.countBits();
    }

    /**
     * 343. 整数拆分
     */
    @GetMapping(value = "/integerBreak")
    public void integerBreak(){
        subjectService.integerBreak();
    }

    /**
     * 345. 反转字符串中的元音字母
     */
    @GetMapping(value = "/reverseVowels")
    public void reverseVowels(){
        subjectService.reverseVowels();
    }

    /**
     * 349. 两个数组的交集
     */
    @GetMapping(value = "/intersection")
    public void intersection(){
        subjectService.intersection();
    }

    /**
     * 354. 俄罗斯套娃信封问题
     */
    @GetMapping(value = "/maxEnvelopes")
    public void maxEnvelopes(){
        subjectService.maxEnvelopes();
    }

    /**
     * 365. 水壶问题
     */
    @GetMapping(value = "/canMeasureWater")
    public void canMeasureWater(){
        subjectService.canMeasureWater();
    }

    /**
     * 373. 查找和最小的 K 对数字
     */
    @GetMapping(value = "/kSmallestPairs")
    public void kSmallestPairs(){
        subjectService.kSmallestPairs();
    }

    /**
     * 374. 猜数字大小
     */
    @GetMapping(value = "/guessNumber")
    public void guessNumber(){
        subjectService.guessNumber();
    }

    /**
     * 376. 摆动序列
     */
    @GetMapping(value = "/wiggleMaxLength")
    public void wiggleMaxLength(){
        subjectService.wiggleMaxLength();
    }

    /**
     * 386. 字典序排数
     */
    @GetMapping(value = "/lexicalOrder")
    public String lexicalOrder() {
        return subjectService.lexicalOrder();
    }

    /**
     * 387. 字符串中的第一个唯一字符
     */
    @GetMapping(value = "/firstUniqChar")
    public String firstUniqChar() {
        return subjectService.firstUniqChar();
    }

    /**
     * 392. 判断子序列
     */
    @GetMapping(value = "/isSubsequence")
    public String isSubsequence() {
        return subjectService.isSubsequence();
    }

    /**
     * 394. 字符串解码
     */
    @GetMapping(value = "/decodeString")
    public String decodeString() {
        return subjectService.decodeString();
    }

    /**
     * 399. 除法求值
     */
    @GetMapping(value = "/calcEquation")
    public String calcEquation() {
        return subjectService.calcEquation();
    }

    /**
     * 402. 移掉 K 位数字
     */
    @GetMapping(value = "/removeKdigits")
    public String removeKdigits() {
        return subjectService.removeKdigits();
    }

    /**
     * 403. 青蛙过河
     */
    @GetMapping(value = "/canCross")
    public String canCross() {
        return subjectService.canCross();
    }

    /**
     * 406. 根据身高重建队列
     */
    @GetMapping(value = "/reconstructQueue")
    public String reconstructQueue() {
        return subjectService.reconstructQueue();
    }

    /**
     * 407. 接雨水 II
     */
    @GetMapping(value = "/trapRainWater")
    public String trapRainWater() {
        return subjectService.trapRainWater();
    }

    /**
     * 409. 最长回文串
     */
    @GetMapping(value = "/longestPalindrome")
    public String longestPalindrome() {
        return subjectService.longestPalindrome();
    }

    /**
     * 410. 分割数组的最大值
     */
    @GetMapping(value = "/splitArray")
    public String splitArray() {
        return subjectService.splitArray();
    }

    /**
     * 416. 分割等和子集
     */
    @GetMapping(value = "/canPartition")
    public String canPartition() {
        return subjectService.canPartition();
    }

    /**
     * 435. 无重叠区间
     */
    @GetMapping(value = "/eraseOverlapIntervals")
    public String eraseOverlapIntervals() {
        return subjectService.eraseOverlapIntervals();
    }

    /**
     * 436. 寻找右区间
     */
    @GetMapping(value = "/findRightInterval")
    public String findRightInterval() {
        return subjectService.findRightInterval();
    }

    /**
     * 437. 路径总和 III
     */
    @GetMapping(value = "/pathSum")
    public String pathSum() {
        return subjectService.pathSum();
    }

    /**
     * 438. 找到字符串中所有字母异位词
     */
    @GetMapping(value = "/findAnagrams")
    public String findAnagrams() {
        return subjectService.findAnagrams();
    }

    /**
     * 443. 压缩字符串
     */
    @GetMapping(value = "/compress")
    public String compress() {
        return subjectService.compress();
    }

    /**
     * 450. 删除二叉搜索树中的节点
     */
    @GetMapping(value = "/deleteNode")
    public String deleteNode() {
        return subjectService.deleteNode();
    }

    /**
     * 452. 用最少数量的箭引爆气球
     */
    @GetMapping(value = "/findMinArrowShots")
    public String findMinArrowShots() {
        return subjectService.findMinArrowShots();
    }

    /**
     * 455. 分发饼干
     */
    @GetMapping(value = "/findContentChildren")
    public String findContentChildren() {
        return subjectService.findContentChildren();
    }

    /**
     * 480. 滑动窗口中位数
     */
    @GetMapping(value = "/medianSlidingWindow")
    public String medianSlidingWindow() {
        return subjectService.medianSlidingWindow();
    }

    /**
     * 486. 预测赢家
     */
    @GetMapping(value = "/predictTheWinner")
    public String predictTheWinner() {
        return subjectService.predictTheWinner();
    }

    /**
     * 493. 翻转对
     */
    @GetMapping(value = "/reversePairs2")
    public String reversePairs() {
        return subjectService.reversePairs();
    }

    /**
     * 525. 连续数组
     */
    @GetMapping(value = "/findMaxLength")
    public String findMaxLength() {
        return subjectService.findMaxLength();
    }

    /**
     * 529. 扫雷游戏
     */
    @GetMapping(value = "/updateBoard")
    public String updateBoard() {
        return subjectService.updateBoard();
    }

    /**
     * 547. 省份数量
     */
    @GetMapping(value = "/findCircleNum")
    public String findCircleNum() {
        return subjectService.findCircleNum();
    }

    /**
     * 550. 游戏玩法分析 IV
     * Activity -> player_id, device_id, event_date, games_played
     * 查询首次登录的第二天登录的玩家比率
     * select IFNULL(round(count(distinct(b.player_id)) / count(distinct(Activity.player_id)), 2), 0) as fraction from (select Activity.player_id as player_id from (select player_id, DATE_ADD(MIN(event_date), INTERVAL 1 DAY) as second_date from Activity group by player_id) as a, Activity where Activity.event_date = a.second_date and Activity.player_id = a.player_id) as b, Activity
     */

    /**
     * 560. 和为 K 的子数组
     */
    @GetMapping(value = "/subarraySum")
    public String subarraySum() {
        return subjectService.subarraySum();
    }

    /**
     * 567. 字符串的排列
     */
    @GetMapping(value = "/checkInclusion")
    public String checkInclusion() {
        return subjectService.checkInclusion();
    }

    /**
     * 570. 至少有5名直接下属的经理
     * Employee -> id, name, department, managerId
     * select name from Employee where id in ( select managerId from Employee group by managerId having count(id)>=5 )
     */

    /**
     * 577. 员工奖金
     * select a.name,b.bonus from Employee a left join Bonus b on a.empId = b.empId where b.bonus < 1000 or b.bonus is null
     */

    /**
     * 584. 寻找用户推荐人
     * 方案一: SELECT name FROM customer WHERE referee_id <> 2 OR referee_id IS NULL;
     * 方案二: SELECT name FROM customer WHERE NOT referee_id <=> 2;
     */

    /**
     * 585. 2016年的投资
     * Insurance -> pid, tiv_2015, tiv_2016, lat, lon
     * 查询 2015年的投保额至少跟一个其他投保人在 2015 年的投保额相同 && lat, lon不能跟其他任何一个投保人完全相同的tiv_2016之和
     * select round(sum(a.tiv_2016) , 2) as tiv_2016 from Insurance a join (select * from Insurance group by tiv_2015 having count(*) > 1) b on a.tiv_2015 = b.tiv_2015 join (select * from Insurance group by lat,lon having count(*) = 1) c on a.lat = c.lat and a.lon = c.lon
     */

    /**
     * 595. 大的国家
     * SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000
     */

    /**
     * 596. 超过5名学生的课
     * select class from courses group by class having count(student) >= 5
     */

    /**
     * 602. 好友申请 II ：谁有最多的好友
     * RequestAccepted -> requester_id, accepter_id, accept_date
     * 查询拥有最多的好友的人和他拥有的好友数
     * select id, count(*) as num from ((select requester_id as id from RequestAccepted) union all (select accepter_id as id from RequestAccepted)) a group by id order by num desc limit 1
     */

    /**
     * 605. 种花问题
     */
    @GetMapping(value = "/canPlaceFlowers")
    public String canPlaceFlowers() {
        return subjectService.canPlaceFlowers();
    }

    /**
     * 610. 判断三角形
     * Triangle -> x, y, z
     * 判断x,y,z是否可以组成三角形
     * select x,y,z,if(x + y > z and x + z > y and y + z > x, 'Yes', 'No') as triangle  from Triangle
     * SELECT x, y, z, IF(x + y + z > 2 * GREATEST(x, y, z) , 'Yes', 'No') AS triangle FROM Triangle
     */

    /**
     * 619. 只出现一次的最大数字
     * MyNumbers -> num
     * 查询最大的 单一数字 。如果不存在则返回 null 。(单一数字: 在 MyNumbers 表中只出现一次的数字)
     * SELECT IF(COUNT(num)=1, num, NULL) AS num FROM MyNumbers GROUP BY num ORDER BY COUNT(num), num DESC LIMIT 1
     */

    /**
     * 620. 有趣的电影
     * select * from cinema where id & 1 and description <> 'boring' order by rating DESC;
     */

    /**
     * 626. 换座位
     * SELECT s1.id, COALESCE(s2.student, s1.student) AS student FROM seat s1 LEFT JOIN seat s2 ON ((s1.id + 1) ^ 1) - 1 = s2.id ORDER BY s1.id;
     */

    /**
     * 632. 最小区间
     */
    @GetMapping(value = "/smallestRange")
    public String smallestRange() {
        return subjectService.smallestRange();
    }

    /**
     * 643. 子数组最大平均数 I
     */
    @GetMapping(value = "/findMaxAverage")
    public String findMaxAverage() {
        return subjectService.findMaxAverage();
    }

    /**
     * 647. 回文子串
     */
    @GetMapping(value = "/countSubstrings")
    public String countSubstrings() {
        return subjectService.countSubstrings();
    }

    /**
     * 649. Dota2 参议院
     */
    @GetMapping(value = "/predictPartyVictory")
    public String predictPartyVictory() {
        return subjectService.predictPartyVictory();
    }

    /**
     * 658. 找到 K 个最接近的元素
     */
    @GetMapping(value = "/findClosestElements")
    public String findClosestElements() {
        return subjectService.findClosestElements();
    }

    /**
     * 700. 二叉搜索树中的搜索
     */
    @GetMapping(value = "/searchBST")
    public String searchBST() {
        return subjectService.searchBST();
    }

    /**
     * 703. 数据流中的第 K 大元素
     */
    @GetMapping(value = "/k/th/largest")
    public String kthLargest() {
        return subjectService.kthLargest();
    }

    /**
     * 709. 转换成小写字母
     */
    @GetMapping(value = "/toLowerCase")
    public String toLowerCase() {
        return subjectService.toLowerCase();
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     */
    @GetMapping(value = "/max/profit6")
    public String maxProfit6() {
        return subjectService.maxProfit6();
    }

    /**
     * 718. 最长重复子数组
     */
    @GetMapping(value = "/findLength")
    public String findLength() {
        return subjectService.findLength();
    }

    /**
     * 724. 寻找数组的中心下标
     */
    @GetMapping(value = "/pivotIndex")
    public String pivotIndex() {
        return subjectService.pivotIndex();
    }

    /**
     * 735. 小行星碰撞
     */
    @GetMapping(value = "/asteroidCollision")
    public String asteroidCollision() {
        return subjectService.asteroidCollision();
    }

    /**
     * 739. 每日温度
     */
    @GetMapping(value = "/dailyTemperatures")
    public String dailyTemperatures() {
        return subjectService.dailyTemperatures();
    }

    /**
     * 746. 使用最小花费爬楼梯
     */
    @GetMapping(value = "/minCostClimbingStairs")
    public String minCostClimbingStairs() {
        return subjectService.minCostClimbingStairs();
    }

    /**
     * 785. 判断二分图
     */
    @GetMapping(value = "/isBipartite")
    public String isBipartite() {
        return subjectService.isBipartite();
    }

    /**
     * 787. K 站中转内最便宜的航班
     */
    @GetMapping(value = "/findCheapestPrice")
    public String findCheapestPrice() {
        return subjectService.findCheapestPrice();
    }

    /**
     * 790. 多米诺和托米诺平铺
     */
    @GetMapping(value = "/numTilings")
    public String numTilings() {
        return subjectService.numTilings();
    }

    /**
     * 797. 所有可能的路径
     */
    @GetMapping(value = "/allPathsSourceTarget")
    public String allPathsSourceTarget() {
        return subjectService.allPathsSourceTarget();
    }

    /**
     * 841. 钥匙和房间
     */
    @GetMapping(value = "/canVisitAllRooms")
    public String canVisitAllRooms() {
        return subjectService.canVisitAllRooms();
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
     * 872. 叶子相似的树
     */
    @GetMapping(value = "/leafSimilar")
    public String leafSimilar() {
        return subjectService.leafSimilar();
    }

    /**
     * 875. 爱吃香蕉的珂珂
     */
    @GetMapping(value = "/minEatingSpeed")
    public String minEatingSpeed() {
        return subjectService.minEatingSpeed();
    }

    /**
     * 901. 股票价格跨度
     */
    @GetMapping(value = "/stockSpanner")
    public String stockSpanner() {
        return subjectService.stockSpanner();
    }

    /**
     * 906. 超级回文数
     */
    @GetMapping(value = "/superpalindromesInRange")
    public String superpalindromesInRange() {
        return subjectService.superpalindromesInRange();
    }

    /**
     * 933. 最近的请求次数
     */
    @GetMapping(value = "/recentCounter")
    public String recentCounter() {
        return subjectService.recentCounter();
    }

    /**
     * 936. 戳印序列
     */
    @GetMapping(value = "/movesToStamp")
    public String movesToStamp() {
        return subjectService.movesToStamp();
    }

    /**
     * 941. 有效的山脉数组
     */
    @GetMapping(value = "/validMountainArray")
    public String validMountainArray() {
        return subjectService.validMountainArray();
    }

    /**
     * 952. 按公因数计算最大组件大小
     */
    @GetMapping(value = "/largestComponentSize")
    public String largestComponentSize() {
        return subjectService.largestComponentSize();
    }

    /**
     * 994. 腐烂的橘子
     */
    @GetMapping(value = "/orangesRotting")
    public String orangesRotting() {
        return subjectService.orangesRotting();
    }

    /**
     * 1004. 最大连续1的个数 III
     */
    @GetMapping(value = "/longestOnes")
    public String longestOnes() {
        return subjectService.longestOnes();
    }

    /**
     * 1045. 买下所有产品的客户
     * select b.customer_id from (select c.customer_id as customer_id,a.count as count,count(distinct c.product_key) as customer_id_count from customer c, (select count(product_key) as count from product) a group by c.customer_id) as b where b.count = b.customer_id_count
     */

    /**
     * 1068. 产品销售分析 I
     * select b.product_name,a.year,a.price from Sales a left join Product b on a.product_id = b.product_id
     */

    /**
     * 1071. 字符串的最大公因子
     */
    @GetMapping(value = "/gcdOfStrings")
    public String gcdOfStrings() {
        return subjectService.gcdOfStrings();
    }

    /**
     * 1075. 项目员工 I
     * project -> project_id, employee_id
     * employee -> employee_id, name, experience_years
     * 查询每一个项目中员工的 平均 工作年限，精确到小数点后两位
     * select a.project_id, round(avg(b.experience_years), 2) as average_years from project a left join Employee b on a.employee_id = b.employee_id group by a.project_id
     */

    /**
     * 1081. 不同字符的最小子序列
     * 同yuan.study.demo.service.subjectService.SubjectService#removeDuplicateLetters(), 故跳过
     */

    /**
     * 1084. 销售分析III
     * Product -> product_id, product_name
     * sales -> product_id, sale_date
     * 查询仅在2019-01-01至2019-03-31之间出售的商品。
     * select a.product_id,a.product_name from product a left join sales b on a.product_id = b. product_id group by b.product_id having min(b.sale_date) >= '2019-01-01' AND max(b.sale_date) <= '2019-03-31'
     */

    /**
     * 1095. 山脉数组中查找目标值
     */
    @GetMapping(value = "/findInMountainArray")
    public String findInMountainArray() {
        return subjectService.findInMountainArray();
    }

    /**
     * 1137. 第 N 个泰波那契数
     */
    @GetMapping(value = "/tribonacci")
    public String tribonacci() {
        return subjectService.tribonacci();
    }

    /**
     * 1141. 查询近30天活跃用户数
     * select activity_date as day, count(distinct user_id) as active_users from Activity where activity_date >= '2019-06-28' and activity_date <= '2019-07-27' group by activity_date
     */

    /**
     * 1143. 最长公共子序列
     */
    @GetMapping(value = "/longestCommonSubsequence")
    public String longestCommonSubsequence() {
        return subjectService.longestCommonSubsequence();
    }

    /**
     * 1148. 文章浏览 I
     * Views -> article_id, author_id, viewer_id, view_date
     * 查询出所有浏览过自己文章的作者
     * SELECT DISTINCT author_id AS id FROM Views WHERE author_id = viewer_id ORDER BY id
     */

    /**
     * 1161. 最大层内元素和
     */
    @GetMapping(value = "/maxLevelSum")
    public String maxLevelSum() {
        return subjectService.maxLevelSum();
    }

    /**
     * 1164. 指定日期的产品价格
     * Products -> product_id, new_price, change_date
     * 查询2019-08-16全部产品的价格, 产品价格未改动则默认是10
     * select distinct b.product_id,if(b.dates is null, 10, b.new_price) as price from (select *, rank() over(PARTITION BY a.product_id order by dates desc) as ranking from (select *,if(change_date > '2019-08-16', null, change_date) as dates from products) a) b where b.ranking = 1
     */

    /**
     * 1174. 即时食物配送 II
     * Delivery -> delivery_id, customer_id, order_date, customer_pref_delivery_date
     * 查询即时订单在所有用户的首次订单中的比例。
     * 如果顾客期望的配送日期和下单日期相同，则该订单称为 「即时订单」，否则称为「计划订单」
     * select round(avg(order_date=customer_pref_delivery_date)*100, 2) immediate_percentage from Delivery join (select customer_id, min(order_date) order_date from Delivery group by customer_id) t using(customer_id, order_date)
     */

    /**
     * 1193. 每月交易 I
     * Transactions -> id, country, state, amount, trans_date
     * 查找每个月和每个国家/地区的事务数及其总金额、已批准的事务数及其总金额。
     * select date_format(trans_date,'%Y-%m') as month,country,count(state) as trans_count,sum(if(state = 'approved', 1, 0)) as approved_count,sum(amount) as trans_total_amount, sum(if(state = 'approved', amount, 0)) as approved_total_amount  from transactions group by month,country
     */

    /**
     * 1204. 最后一个能进入巴士的人
     * Queue -> person_id, person_name, weight, turn
     * 查询未超重情况下(weight的和在1000kg以下) 最后一个 上巴士且不超过重量限制的乘客person_name
     * select a.person_name from (select person_name, @total := @total + weight as weight from queue, (select @total := 0) total order by turn) a where a.weight <= 1000 order by a.weight desc limit 1
     */

    /**
     * 1207. 独一无二的出现次数
     */
    @GetMapping(value = "/uniqueOccurrences")
    public String uniqueOccurrences() {
        return subjectService.uniqueOccurrences();
    }

    /**
     * 1211. 查询结果的质量和占比
     * Queries -> query_name, result, position, rating
     * quality：每个query_name的result 与position比率的平均值
     * poor_query_percentage：rating < 3 的查询结果占全部查询结果的百分比
     * 查询每个query_name的quality和poor_query_percentage分值
     * select query_name,round(avg(rating / position), 2) as quality,round(avg(rating < 3) * 100, 2) as poor_query_percentage from queries group by query_name having query_name is not null
     * select a.query_name, round(avg(a.rating / a.position), 2) as quality, ifnull(round(b.count / count(a.rating) * 100, 2), 0) as poor_query_percentage from Queries a left join (select d.query_name, count(d.rating) as count from Queries d where d.rating < 3 group by d.query_name) b on a.query_name = b.query_name group by a.query_name having a.query_name is not null
     */

    /**
     * 1251. 平均售价
     * Prices -> product_id, start_date, end_date, price
     * UnistsSold -> product_id, purchase_date, units
     * 查找每种产品的平均售价
     * select a.product_id, ifnull(round((sum(b.units * a.price) / sum(b.units)), 2), 0) as average_price from prices a left join unitssold b on a.product_id = b.product_id and b.purchase_date >= a.start_date and b.purchase_date <= end_date group by a.product_id
     */

    /**
     * 1268. 搜索推荐系统
     */
    @GetMapping(value = "/suggestedProducts")
    public String suggestedProducts() {
        return subjectService.suggestedProducts();
    }

    /**
     * 1280. 学生们参加各科测试的次数
     * Students -> student_id, student_name
     * Subjects -> subject_name
     * Examinations -> student_id, subject_name
     * 查询出每个学生参加每一门科目测试的次数，结果按 student_id 和 subject_name 排序
     * select a.student_id,a.student_name,b.subject_name,ifnull(d.attended_exams, 0) as attended_exams from Students a cross join subjects b left join (select c.student_id, c.subject_name, count(*) as attended_exams from Examinations c group by c.student_id,c.subject_name) as d on a.student_id = d.student_id and b.subject_name = d.subject_name order by a.student_id,b.subject_name;
     */

    /**
     * 1318. 或运算的最小翻转次数
     */
    @GetMapping(value = "/minFlips")
    public String minFlips() {
        return subjectService.minFlips();
    }

    /**
     * 1321. 餐馆营业额变化增长
     * Customer -> customer_id, name, visited_on, amount
     * 查询每天的7天内消费总额的平均值
     * SELECT DISTINCT visited_on, sum_amount AS amount, ROUND(sum_amount/7, 2) AS average_amount FROM (SELECT visited_on, SUM(amount) OVER ( ORDER BY visited_on RANGE interval 6 day preceding  ) AS sum_amount FROM Customer) t WHERE DATEDIFF(visited_on, (SELECT MIN(visited_on) FROM Customer)) >= 6
     */

    /**
     * 1327. 列出指定时间段内所有的下单产品
     * select b.product_name, sum(a.unit) as unit from orders a left join Products b on a.product_id = b.product_id where a.order_date >= '2020-02-01' and a.order_date <= '2020-02-29' group by a.product_id having unit >= 100
     */

    /**
     * 1341. 电影评分
     * (select name as results from (select a.user_id,b.name as name,count(*) as count from MovieRating a left join users b on a.user_id = b.user_id group by a.user_id) c order by count desc, name asc limit 1) union all (SELECT title FROM Movies m left JOIN MovieRating mr ON m.movie_id = mr.movie_id AND created_at BETWEEN '2020-02-01' AND '2020-02-29' GROUP BY m.movie_id ORDER BY AVG(rating) DESC, title asc LIMIT 1)
     */

    /**
     * 1372. 二叉树中的最长交错路径
     */
    @GetMapping(value = "/longestZigZag")
    public String longestZigZag() {
        return subjectService.longestZigZag();
    }

    /**
     * 1378. 使用唯一标识码替换员工ID
     * select b.unique_id,a.name from Employees a left join EmployeeUNI b on a.id = b.id
     */

    /**
     * 1431. 拥有最多糖果的孩子
     */
    @GetMapping(value = "/kidsWithCandies")
    public String kidsWithCandies() {
        return subjectService.kidsWithCandies();
    }

    /**
     * 1448. 统计二叉树中好节点的数目
     */
    @GetMapping(value = "/goodNodes")
    public String goodNodes() {
        return subjectService.goodNodes();
    }

    /**
     * 1456. 定长子串中元音的最大数目
     */
    @GetMapping(value = "/maxVowels")
    public String maxVowels() {
        return subjectService.maxVowels();
    }

    /**
     * 1466. 重新规划路线
     */
    @GetMapping(value = "/minReorder")
    public String minReorder() {
        return subjectService.minReorder();
    }

    /**
     * 1484. 按日期分组销售产品
     * Activities -> sell_date, product
     * 查询每个日期、销售的不同产品的数量及其名称(逗号隔开)
     * select sell_date, count(distinct product) as num_sold, group_concat(distinct product order by product separator ',') as products from Activities group by sell_date order by sell_date
     */

    /**
     * 1486. 数组异或操作
     */
    @GetMapping(value = "/xorOperation")
    public String xorOperation() {
        return subjectService.xorOperation();
    }

    /**
     * 1492. n 的第 k 个因子
     */
    @GetMapping(value = "/kthFactor")
    public String kthFactor() {
        return subjectService.kthFactor();
    }

    /**
     * 1493. 删掉一个元素以后全为 1 的最长子数组
     */
    @GetMapping(value = "/longestSubarray")
    public String longestSubarray() {
        return subjectService.longestSubarray();
    }

    /**
     * 1512. 好数对的数目
     */
    @GetMapping(value = "/numIdenticalPairs")
    public String numIdenticalPairs() {
        return subjectService.numIdenticalPairs();
    }

    /**
     * 1517. 查找拥有有效邮箱的用户
     * select user_id,name,mail from Users where mail regexp '^[a-zA-Z][a-zA-Z0-9\\_\\.\\-]*@leetcode\\.com$'
     */

    /**
     * 1527. 患某种疾病的患者
     * Patients -> patient_id, patient_name, conditions
     * 病例conditions是以空格隔开, 查询包含有DIAB1开头病的患者信息
     * SELECT patient_id, patient_name, conditions FROM Patients WHERE conditions REGEXP '\\bDIAB1'
     */

    /**
     * 1581. 进店却未进行过交易的顾客
     * Visits -> visit_id, customer_id
     * Transactions -> transaction_id, visit_id, amount
     * visit_id为唯一id, 同个顾客来一次给一个id
     * 查找光顾了购物中心但没有进行交易顾客的 ID ，以及他们只光顾不交易的次数
     * SELECT customer_id,count(visit_id) as count_no_trans FROM Visits WHERE visit_id not in (SELECT DISTINCT visit_id FROM Transactions) GROUP BY customer_id
     */

    /**
     * 1633. 各赛事的用户注册率
     * Users -> user_id, user_name
     * Register -> contest_id, user_id
     * 查询各赛事的用户注册百分率
     * select b.contest_id,round(count(b.user_id) * 100 / u.count, 2) as percentage  from register b cross join (select count(*) as count from users a) u group by b.contest_id order by percentage desc,b.contest_id
     */

    /**
     * 1657. 确定两个字符串是否接近
     */
    @GetMapping(value = "/closeStrings")
    public String closeStrings() {
        return subjectService.closeStrings();
    }

    /**
     * 1661. 每台机器的进程平均运行时间
     * Activity -> machine_id,process_id,activity_type,timestamp
     * 计算每台机器各自完成一个进程任务的平均耗时 (完成一个进程任务的时间指进程的'end' 时间戳 减去 'start' 时间戳)
     * select a.machine_id, round(avg(b.timestamp-a.timestamp), 3) as processing_time from Activity a join activity b on a.machine_id = b.machine_id and a.process_id = b.process_id and a.activity_type = 'start' and b.activity_type = 'end' group by a.machine_id
     */

    /**
     * 1667. 修复表中的名字
     * Users -> user_id, name
     * 将表中的name字段, 首字母大写, 其余字母小写 然后返回
     * select user_id, concat(upper(substring(name, 1, 1)), lower(substring(name, 2))) as name from users order by user_id
     */

    /**
     * 1679. K 和数对的最大数目
     */
    @GetMapping(value = "/maxOperations")
    public String maxOperations() {
        return subjectService.maxOperations();
    }

    /**
     * 1683. 无效的推文
     * Tweets -> tweet_id, content
     * 查询所有无效推文的编号ID。当推文内容中的字符数大于 15 时，该推文是无效的
     * select tweet_id from Tweets where length(content) > 15
     */

    /**
     * 1729. 求关注者的数量
     * select user_id, count(follower_id) as followers_count  from followers group by user_id order by user_id
     */

    /**
     * 1731. 每位经理的下属员工数量
     * Employees -> employee_id, name, reports_to, age
     * 查询所有经理的ID、名称、直接向该经理汇报的员工人数，以及这些员工的平均年龄，其中该平均年龄需要四舍五入到最接近的整数
     * select b.employee_id, b.name, count(a.reports_to) reports_count, round(avg(a.age),0) average_age from Employees a,Employees b where a.reports_to = b.employee_id group by a.reports_to having reports_count > 0 order by employee_id
     */

    /**
     * 1732. 找到最高海拔
     */
    @GetMapping(value = "/largestAltitude")
    public String largestAltitude() {
        return subjectService.largestAltitude();
    }

    /**
     * 1735. 生成乘积数组的方案数
     */
    @GetMapping(value = "/waysToFillArray")
    public String waysToFillArray() {
        return subjectService.waysToFillArray();
    }

    /**
     * 1757. 可回收且低脂的产品
     * select product_id  from Products  where low_fats = "Y" and recyclable  = 'Y'
     */

    /**
     * 1768. 交替合并字符串
     */
    @GetMapping(value = "/mergeAlternately")
    public String mergeAlternately() {
        return subjectService.mergeAlternately();
    }

    /**
     * 1789. 员工的直属部门
     * Employee -> employee_id, department_id, primary_flag
     * 查出员工所属的直属部门 (直属部门: primary_flag = 'Y' / 仅一条为'N'的数据)
     * select employee_id,department_id from Employee where primary_flag = 'Y' union select employee_id,department_id from Employee group by employee_id having count(employee_id) = 1
     * select employee_id, IF(COUNT(*) = 1, department_id, SUM(IF(primary_flag = "Y",department_id,null))) department_id FROM Employee group by 1
     * SELECT employee_id,department_id FROM (SELECT  *,COUNT(*) OVER(PARTITION BY employee_id) cnt FROM Employee)t WHERE cnt = 1 OR primary_flag = 'Y'
     */

    /**
     * 1907. 按分类统计薪水
     * accounts -> account_id, income
     * 查询每种类型账户的数据(Low Salary -> (负无穷, 20000); Average Salary -> [20000, 50000]; High Salary -> (50000, 正无穷)
     * select 'Low Salary' AS category,count(*) as accounts_count  from accounts where income < 20000 union select 'Average Salary' AS category,count(*) as accounts_count  from accounts where income >= 20000 and income <= 50000 union select 'High Salary' AS category,count(*) as accounts_count  from accounts where income > 50000
     */

    /**
     * 1926. 迷宫中离入口最近的出口
     */
    @GetMapping(value = "/nearestExit")
    public String nearestExit() {
        return subjectService.nearestExit();
    }

    /**
     * 1934. 确认率
     * Signups -> user_id, time_stamp
     * Confirmations -> user_id, time_stamp, action
     * 查找每个用户的 确认率, 用户的确认率是 'confirmed' 消息的数量除以请求的确认消息的总数。没有请求任何确认消息的用户的确认率为 0
     * select s.user_id as 'user_id', round((sum(if (c.action = 'confirmed', 1, 0))) / count(*), 2) as confirmation_rate from Signups s left join Confirmations c on s.user_id = c.user_id group by s.user_id
     */

    /**
     * 1978. 上级经理已离职的公司员工
     * select a.employee_id from employees a left join employees b on a.manager_id = b.employee_id where a.salary < 30000 and a.manager_id is not null and b.name is null order by a.employee_id
     */

    /**
     * 1979. 找出数组的最大公约数
     */
    @GetMapping(value = "/findGCD")
    public String findGCD() {
        return subjectService.findGCD();
    }

    /**
     * 2071. 你可以安排的最多任务数目
     */
    @GetMapping(value = "/maxTaskAssign")
    public String maxTaskAssign() {
        return subjectService.maxTaskAssign();
    }

    /**
     * 2095. 删除链表的中间节点
     */
    @GetMapping(value = "/deleteMiddle")
    public String deleteMiddle() {
        return subjectService.deleteMiddle();
    }

    /**
     * 2130. 链表最大孪生和
     */
    @GetMapping(value = "/pairSum")
    public String pairSum() {
        return subjectService.pairSum();
    }

    /**
     * 2215. 找出两数组的不同
     */
    @GetMapping(value = "/findDifference")
    public String findDifference() {
        return subjectService.findDifference();
    }

    /**
     * 2235. 两整数相加
     */
    @GetMapping(value = "/sum")
    public String sum() {
        return subjectService.sum();
    }

    /**
     * 2236. 判断根结点是否等于子结点之和
     */
    @GetMapping(value = "/checkTree")
    public String checkTree() {
        return subjectService.checkTree();
    }

    /**
     * 2300. 咒语和药水的成功对数
     */
    @GetMapping(value = "/successfulPairs")
    public String successfulPairs() {
        return subjectService.successfulPairs();
    }

    /**
     * 2336. 无限集中的最小数字
     */
    @GetMapping(value = "/smallestInfiniteSet")
    public String smallestInfiniteSet() {
        return subjectService.smallestInfiniteSet();
    }

    /**
     * 2352. 相等行列对
     */
    @GetMapping(value = "/equalPairs")
    public String equalPairs() {
        return subjectService.equalPairs();
    }

    /**
     * 2356. 每位教师所教授的科目种类的数量
     * Teacher -> teacher_id, subject_id, dept_id
     * 查询每位老师在大学里教授的科目种类的数量
     * SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt FROM Teacher GROUP BY teacher_id
     */

    /**
     * 2390. 从字符串中移除星号
     */
    @GetMapping(value = "/removeStars")
    public String removeStars() {
        return subjectService.removeStars();
    }

    /**
     * 2413. 最小偶倍数
     */
    @GetMapping(value = "/smallestEvenMultiple")
    public String smallestEvenMultiple() {
        return subjectService.smallestEvenMultiple();
    }

    /**
     * 2462. 雇佣 K 位工人的总代价
     */
    @GetMapping(value = "/totalCost")
    public String totalCost() {
        return subjectService.totalCost();
    }

    /**
     * 2469. 温度转换
     */
    @GetMapping(value = "/convertTemperature")
    public String convertTemperature() {
        return subjectService.convertTemperature();
    }

    /**
     * 2542. 最大子序列的分数
     */
    @GetMapping(value = "/maxScore")
    public String maxScore() {
        return subjectService.maxScore();
    }
}

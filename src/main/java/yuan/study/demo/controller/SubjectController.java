package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.SubjectService;

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
     * 三数之和
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
     * K 个一组翻转链表
     */
    @GetMapping(value = "/reverse/k/group")
    public void reverseKGroup(){
        subjectService.reverseKGroup();
    }
}

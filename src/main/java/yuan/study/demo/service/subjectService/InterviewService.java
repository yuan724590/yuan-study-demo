package yuan.study.demo.service.subjectService;


public interface InterviewService {

    /**
     * 示例 1：
     * 输入: s = "leetcode"
     * 输出: false
     *
     * 示例 2：
     * 输入: s = "abc"
     * 输出: true
     *
     * 限制：
     * 0 <= len(s) <= 100
     * s[i]仅包含小写字母
     * 如果你不使用额外的数据结构，会很加分。
     */
    String isUnique();

    /**
     * 给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     *
     * 示例 1：
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     *
     * 示例 2：
     * 输入: s1 = "abc", s2 = "bad"
     * 输出: false
     *
     * 说明：
     * 0 <= len(s1) <= 100
     * 0 <= len(s2) <= 100
     */
    String checkPermutation();

    /**
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * 示例 1：
     * 输入："Mr John Smith    ", 13
     * 输出："Mr%20John%20Smith"
     *
     * 示例 2：
     * 输入："               ", 5
     * 输出："%20%20%20%20%20"
     *
     * 提示：
     * 字符串长度在 [0, 500000] 范围内。
     */
    String replaceSpaces();
}

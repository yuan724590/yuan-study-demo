package yuan.study.demo.service;


public interface SubjectService {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     *
     * 示例 2：
     *
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     *
     *
     * 示例 3：
     *
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     *
     *
     *
     *
     * 提示：
     *
     *
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     *
     *
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     * Related Topics 数组 哈希表
     * 👍 11871 👎 0
     */
    void subjectTest();

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     *  请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     *  你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     *
     *
     *  示例 1：
     *
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     *
     *  示例 2：
     *
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     *
     *  示例 3：
     *
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     *
     *
     *
     *  提示：
     *
     *
     *  每个链表中的节点数在范围 [1, 100] 内
     *  0 <= Node.val <= 9
     *  题目数据保证列表表示的数字不含前导零
     *
     *  Related Topics 递归 链表 数学
     *  👍 6624 👎 0
     */
    void addTwoNumbers();

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     *
     *
     *  示例 1:
     *
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     *
     *  示例 2:
     *
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     *
     *  示例 3:
     *
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     *
     *  示例 4:
     *
     *
     * 输入: s = ""
     * 输出: 0
     *
     *
     *
     *
     *  提示：
     *
     *
     *  0 <= s.length <= 5 * 104
     *  s 由英文字母、数字、符号和空格组成
     *
     *  Related Topics 哈希表 字符串 滑动窗口
     *  👍 5986 👎 0
     */
    void longestSubstringWithoutRepeatedCharacters();

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     *
     *
     *  示例 1：
     *
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     *
     *  示例 2：
     *
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     *
     *
     *  示例 3：
     *
     *
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     *
     *
     *  示例 4：
     *
     *
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     *
     *
     *  示例 5：
     *
     *
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     *
     *
     *
     *
     *  提示：
     *
     *
     *  nums1.length == m
     *  nums2.length == n
     *  0 <= m <= 1000
     *  0 <= n <= 1000
     *  1 <= m + n <= 2000
     *  -106 <= nums1[i], nums2[i] <= 106
     *
     *
     *
     *
     *  进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     *  Related Topics 数组 二分查找 分治
     *  👍 4427 👎 0
     */
    void queryPositiveArrayMedian();

    /**
     * //给你一个字符串 s，找到 s 中最长的回文子串。
     * //
     * //
     * //
     *  示例 1：
     *
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     *
     *  示例 2：
     *
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     *
     *  示例 3：
     *
     *
     * 输入：s = "a"
     * 输出："a"
     *
     *
     *  示例 4：
     *
     *
     * 输入：s = "ac"
     * 输出："a"
     *
     *
     *
     *
     *  提示：
     *
     *
     *  1 <= s.length <= 1000
     *  s 仅由数字和英文字母（大写和/或小写）组成
     *
     *  Related Topics 字符串 动态规划
     *  👍 4065 👎 0
     */
    void getTheLongestPalindromeString();

    /**
     * //将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * //
     * // 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * //
     * //
     * //P   A   H   N
     * //A P L S I I G
     * //Y   I   R
     * //
     * // 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * //
     * // 请你实现这个将字符串进行指定行数变换的函数：
     * //
     * //
     * //string convert(string s, int numRows);
     * //
     * //
     * //
     * // 示例 1：
     * //
     * //
     * //输入：s = "PAYPALISHIRING", numRows = 3
     * //输出："PAHNAPLSIIGYIR"
     * //
     * //示例 2：
     * //
     * //
     * //输入：s = "PAYPALISHIRING", numRows = 4
     * //输出："PINALSIGYAHRPI"
     * //解释：
     * //P     I    N
     * //A   L S  I G
     * //Y A   H R
     * //P     I
     * //
     * //
     * // 示例 3：
     * //
     * //
     * //输入：s = "A", numRows = 1
     * //输出："A"
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 1 <= s.length <= 1000
     * // s 由英文字母（小写和大写）、',' 和 '.' 组成
     * // 1 <= numRows <= 1000
     */
    void zigzagTransformation();

    /**
     * //给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * //
     * // 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
     * //假设环境不允许存储 64 位整数（有符号或无符号）。
     * //
     * //
     * //
     * // 示例 1：
     * //
     * //
     * //输入：x = 123
     * //输出：321
     * //
     * //
     * // 示例 2：
     * //
     * //
     * //输入：x = -123
     * //输出：-321
     * //
     * //
     * // 示例 3：
     * //
     * //
     * //输入：x = 120
     * //输出：21
     * //
     * //
     * // 示例 4：
     * //
     * //
     * //输入：x = 0
     * //输出：0
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // -231 <= x <= 231 - 1
     */
    void integerInversion();

    /**
     * //请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * //
     * // 函数 myAtoi(string s) 的算法如下：
     * //
     * //
     * // 读入字符串并丢弃无用的前导空格
     * // 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * // 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * // 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤
     * //2 开始）。
     * // 如果整数数超过 32 位有符号整数范围 [−231, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固
     * //定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * // 返回整数作为最终结果。
     * //
     * //
     * // 注意：
     * //
     * //
     * // 本题中的空白字符只包括空格字符 ' ' 。
     * // 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     * //
     * //
     * //
     * //
     * // 示例 1：
     * //
     * //
     * //输入：s = "42"
     * //输出：42
     * //解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
     * //第 1 步："42"（当前没有读入字符，因为没有前导空格）
     * //         ^
     * //第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     * //         ^
     * //第 3 步："42"（读入 "42"）
     * //           ^
     * //解析得到整数 42 。
     * //由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
     * //
     * // 示例 2：
     * //
     * //
     * //输入：s = "   -42"
     * //输出：-42
     * //解释：
     * //第 1 步："   -42"（读入前导空格，但忽视掉）
     * //            ^
     * //第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
     * //             ^
     * //第 3 步："   -42"（读入 "42"）
     * //               ^
     * //解析得到整数 -42 。
     * //由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
     * //
     * //
     * // 示例 3：
     * //
     * //
     * //输入：s = "4193 with words"
     * //输出：4193
     * //解释：
     * //第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
     * //         ^
     * //第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     * //         ^
     * //第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
     * //             ^
     * //解析得到整数 4193 。
     * //由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 0 <= s.length <= 200
     * // s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
     */
    void convertStringNumber();

    /**
     * //给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * //
     * // 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * //
     * //
     * // 例如，121 是回文，而 123 不是。
     * //
     * //
     * //
     * //
     * // 示例 1：
     * //
     * //
     * //输入：x = 121
     * //输出：true
     * //
     * //
     * // 示例 2：
     * //
     * //
     * //输入：x = -121
     * //输出：false
     * //解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * //
     * //
     * // 示例 3：
     * //
     * //
     * //输入：x = 10
     * //输出：false
     * //解释：从右向左读, 为 01 。因此它不是一个回文数。
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // -231 <= x <= 231 - 1
     */
    void palindromeNumber();

    /**
     * //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * //
     * //
     * // '.' 匹配任意单个字符
     * // '*' 匹配零个或多个前面的那一个元素
     * //
     * //
     * // 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * //
     * //
     * // 示例 1：
     * //
     * //
     * //输入：s = "aa", p = "a"
     * //输出：false
     * //解释："a" 无法匹配 "aa" 整个字符串。
     * //
     * //
     * // 示例 2:
     * //
     * //
     * //输入：s = "aa", p = "a*"
     * //输出：true
     * //解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * //
     * //
     * // 示例 3：
     * //
     * //
     * //输入：s = "ab", p = ".*"
     * //输出：true
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 1 <= s.length <= 20
     * // 1 <= p.length <= 30
     * // s 只包含从 a-z 的小写字母。
     * // p 只包含从 a-z 的小写字母，以及字符 . 和 *。
     * // 保证每次出现字符 * 时，前面都匹配到有效的字符
     */
    void regularExpressionMatching();

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     *
     * 说明：你不能倾斜容器。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    void holdMostWater();

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给你一个整数，将其转为罗马数字。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: num = 3
     * 输出: "III"
     * 示例 2:
     *
     * 输入: num = 4
     * 输出: "IV"
     * 示例 3:
     *
     * 输入: num = 9
     * 输出: "IX"
     * 示例 4:
     *
     * 输入: num = 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * 示例 5:
     *
     * 输入: num = 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *  
     *
     * 提示：
     *
     * 1 <= num <= 3999
     */
    void intToRoman();

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: s = "III"
     * 输出: 3
     * 示例 2:
     *
     * 输入: s = "IV"
     * 输出: 4
     * 示例 3:
     *
     * 输入: s = "IX"
     * 输出: 9
     * 示例 4:
     *
     * 输入: s = "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     *
     * 输入: s = "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     */
    void romanToInt();

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *  
     *
     * 提示：
     *
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    void longestCommonPrefix();

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     *
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：[]
     *  
     *
     * 提示：
     *
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */
    void threeSum();

    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     *
     * 返回这三个数的和。
     *
     * 假定每组输入只存在恰好一个解。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * 示例 2：
     *
     * 输入：nums = [0,0,0], target = 1
     * 输出：0
     *  
     *
     * 提示：
     *
     * 3 <= nums.length <= 1000
     * -1000 <= nums[i] <= 1000
     * -104 <= target <= 104
     *
     */
    void threeSumClosest();

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     *
     * 示例 1：
     *
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     *
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     *
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *  
     *
     * 提示：
     *
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    void letterCombinations();
}

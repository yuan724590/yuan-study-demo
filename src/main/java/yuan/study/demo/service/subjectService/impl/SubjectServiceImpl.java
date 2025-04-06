package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;
import yuan.study.demo.entity.ListNode;
import yuan.study.demo.service.subjectService.SubjectService;

import java.util.*;

import static java.util.Arrays.stream;


@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {

    @Override
    public void subjectTest(){
        twoSum(new int[]{3,2,4}, 6);
    }
    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i){
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Override
    public void addTwoNumbers(){
        ListNode l12 = new ListNode(1, null);
        ListNode l11 = new ListNode(9, l12);
        ListNode l1 = new ListNode(9, l11);

        ListNode l22 = new ListNode(5, null);
        ListNode l21 = new ListNode(6, l22);
        ListNode l2 = new ListNode(1, l21);

        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.print("[");
        for(;;){
            System.out.print(l3.val);
            if(l3.next == null){
                break;
            }
            l3 = l3.next;
            System.out.print(",");
        }
        System.out.print("]");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addListNode(l1, l2, 0);
    }

    private ListNode addListNode(ListNode l1, ListNode l2, int carry) {
        if(l1 == null && l2 == null){
            return carry > 0 ? new ListNode(carry, null) : null;
        }else if(l1 == null){
            return new ListNode((l2.val + carry) % 10, (l2.val + carry) / 10 == 0 ? l2.next : addListNode(null, l2.next, (l2.val + carry) / 10));
        }else if(l2 == null){
            return new ListNode((l1.val + carry) % 10, (l1.val + carry) / 10 == 0 ? l1.next : addListNode(l1.next, null, (l1.val + carry) / 10));
        }else{
            return new ListNode((l1.val + l2.val + carry) % 10, addListNode(l1.next, l2.next, (l1.val + l2.val + carry) / 10));
        }
    }

    @Override
    public void longestSubstringWithoutRepeatedCharacters(){
        lengthOfLongestSubstring("dvdf");
    }

    /**
     * 滑动窗口的解析思路
     */
    private int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128] ;
        for (int j = 0, i = 0; j < n; j++) {
            //当前字符上次出现的位置, 或者为i, 如果没有重复过则固定为0
            i = index[s.charAt(j)] > i ? index[s.charAt(j)] : i;
            //取最长的长度覆盖至ans值中, 要么是j的值, 要么是ans的值, 加一是用于保证为空时也能取到值1
            ans = ans > j - i + 1 ? ans : j - i + 1;
            //赋值 index[当前ascell码值] = 第X个元素(从1开始, 用于保证为空时也能取到值1)
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    @Override
    public void queryPositiveArrayMedian(){
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {

                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

    @Override
    public void getTheLongestPalindromeString(){
        System.out.println(getTheLongestPalindromeString("addc"));
    }

    public String getTheLongestPalindromeString(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    @Override
    public void zigzagTransformation(){
        convert("PAYPALISHIRING", 4);
    }

    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        int charIndex = 0;
        char[] chars = new char[s.length()];
        for(int i = 0; i < numRows; i++){
            for(int j = i; j < s.length();){
                chars[charIndex++] = s.charAt(j);
                j = j + ((i == numRows - 1) ? 2 * numRows - 2 : (i == 0 || j / (numRows - 1) % 2 == 0 ? 2 * numRows - 2 - 2 * i : 2 * i));
            }
        }
        return new String(chars);
    }

    @Override
    public void integerInversion(){
        System.out.println(reverse(1534236469));
    }

    public int reverse(int x) {
        long result = 0;
        while (x != 0){
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return 0;
        }
        return (int)result;
    }

    @Override
    public void convertStringNumber(){
        System.out.println(myAtoi("words and 987"));
    }

    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-' && str.charAt(0) != '+'){
            return 0;
        }
        long ans = 0L;
        boolean neg = str.charAt(0) == '-';
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }

    @Override
    public void palindromeNumber(){
        System.out.println(isPalindrome(12322));
    }

    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    @Override
    public void regularExpressionMatching(){
        System.out.println(isMatch("aaa", "baa"));//.*
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] booleanArr = new boolean[m + 1][n + 1];
        booleanArr[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    booleanArr[i][j] = booleanArr[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        booleanArr[i][j] = booleanArr[i][j] || booleanArr[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        booleanArr[i][j] = booleanArr[i - 1][j - 1];
                    }
                }
            }
        }
        return booleanArr[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    @Override
    public void holdMostWater(){
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            int minH = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, area);
            // 快速跳过这步可太妙了
            while (height[l] <= minH && l < r) {
                l++;
            }
            while (height[r] <= minH && l < r) {
                r--;
            }
        }
        return maxArea;
    }

    @Override
    public void intToRoman(){
        System.out.println(intToRoman(1998));
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    @Override
    public void romanToInt(){
        System.out.println(romanToInt("IX"));
    }

    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    @Override
    public void longestCommonPrefix(){
        System.out.println(longestCommonPrefix(new String[]{"a"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void threeSum(){
        System.out.println(threeSum(new int[]{-2,0,0,2,2}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3){
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int left, right, sum;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            left = i + 1;
            right = nums.length - 1;
            while (right > left) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    @Override
    public void threeSumClosest(){
        System.out.println(threeSumClosest(new int[]{0,2,1,-3}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, sum, diff = Integer.MAX_VALUE, j, k;
        for(int i = 0; i < nums.length - 2; ++i){
            j = i + 1;
            k = nums.length - 1;
            while(j < k){
                sum = nums[i] + nums[j] + nums[k];
                if(sum == target) {
                    return sum;
                }
                if(Math.abs(sum - target) < diff){
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if(sum < target) {
                    j++;
                }else if(sum > target) {
                    k--;
                }
            }
        }
        return res;
    }

    @Override
    public void letterCombinations(){
        System.out.println(letterCombinations("259"));
    }

    // 数字到号码的映射
    private String[] map = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    // 路径
    private StringBuilder sb = new StringBuilder();

    // 结果集
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(digits,0);
        return res;
    }

    // 回溯函数
    private void backtrack(String digits,int index) {
        if(sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String val = map[digits.charAt(index)-'2'];
        for(char ch:val.toCharArray()) {
            sb.append(ch);
            backtrack(digits,index+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    @Override
    public void fourSum(){
        fourSum(new int[]{1,0,-1,0,-2,2}, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    @Override
    public void removeNthFromEnd(){
        ListNode head = getListNode();
        head = removeNthFromEnd(head, 2);
        for(;;){
            System.out.println(head.val + ",");
            head = head.next;
            if(head == null){
                break;
            }
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        ListNode preNode = new ListNode(0, head);
        int count = 1;
        while (current.next != null) {
            count++;
            if (count > n) {
                preNode = preNode.next;
            }
            current = current.next;
        }
        preNode.next = preNode.next.next;
        return count == n ? head.next : head;
    }

    @Override
    public void isValidBrackets(){
        System.out.println(isValid("[(])]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            } else if(c == '['){
                stack.push(']');
            } else if(c == '{'){
                stack.push('}');
            } else if(stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Override
    public void mergeTwoLists(){
        ListNode head = new ListNode();
        head.val = 1;
        ListNode head1 = new ListNode();
        head1.val = 2;
        head.next = head1;
        ListNode head2 = new ListNode();
        head2.val = 3;
        head1.next = head2;
        ListNode head3 = new ListNode();
        head3.val = 4;
        head2.next = head3;
        ListNode head4 = new ListNode();
        head4.val = 5;
        head3.next = head4;
        ListNode headCopy = head;

        head = new ListNode();
        head.val = 1;
        head1 = new ListNode();
        head1.val = 3;
        head.next = head1;
        head2 = new ListNode();
        head2.val = 5;
        head1.next = head2;
        ListNode headCopy1 = head;
        ListNode listNode = mergeTwoLists(headCopy, headCopy1);
        while(listNode.next != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    @Override
    public void generateParenthesis(){
        System.out.println(generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    @Override
    public void mergeKLists(){
        ListNode head = new ListNode();
        head.val = 1;
        ListNode head1 = new ListNode();
        head1.val = 2;
        head.next = head1;
        ListNode head2 = new ListNode();
        head2.val = 3;
        head1.next = head2;
        ListNode head3 = new ListNode();
        head3.val = 4;
        head2.next = head3;
        ListNode head4 = new ListNode();
        head4.val = 5;
        head3.next = head4;
        ListNode headCopy = head;

        head = new ListNode();
        head.val = 1;
        head1 = new ListNode();
        head1.val = 3;
        head.next = head1;
        head2 = new ListNode();
        head2.val = 5;
        head1.next = head2;
        ListNode headCopy1 = head;
        ListNode listNode = mergeKLists(new ListNode[]{headCopy, headCopy1});
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    @Override
    public String swapPairs(){
        ListNode head = getListNode();
        ListNode listNode = swapPairs(head);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        return "success";
    }

    private ListNode getListNode(){
        ListNode head = new ListNode();
        head.val = 1;
        ListNode head1 = new ListNode();
        head1.val = 2;
        head.next = head1;
        ListNode head2 = new ListNode();
        head2.val = 3;
        head1.next = head2;
        ListNode head3 = new ListNode();
        head3.val = 4;
        head2.next = head3;
        ListNode head4 = new ListNode();
        head4.val = 5;
        head3.next = head4;
        ListNode head5 = new ListNode();
        head5.val = 6;
        head4.next = head5;
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    @Override
    public String reverseKGroup(){
        ListNode head = getListNode();
        ListNode listNode = reverseKGroup(head, 3);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        return "success";
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), prev = dummy, curr = head, next;
        dummy.next = head;
        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }
        for(int i = 0; i < length / k; i++) {
            for(int j = 1; j < k; j++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
            curr = prev.next;
        }
        return dummy.next;
    }

    @Override
    public String removeDuplicates(){
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
        return "success";
    }

    public int removeDuplicates(int[] nums) {
        if(nums.length == 1){
            return nums.length;
        }
        // 使用双指针
        int i = 0, j =1;
        while(j < nums.length){
            if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    @Override
    public String removeElement() {
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
        return "success";
    }

    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        for(; j < nums.length; j++){
            if(nums[j] != val){
                if(i != j){
                    nums[i] = nums[j];
                }
                i++;
            }
        }
        return i;
    }

    @Override
    public String findStrIndex(){
        System.out.println(strStr("sadbutsad", "sad"));
        return "success";
    }

    public int strStr(String haystack, String needle) {
//        return haystack.indexOf(needle);
        for(int i = 0; i < haystack.length(); i++){
            if(haystack.charAt(i) == needle.charAt(0) && i + needle.length() <= haystack.length() && haystack.substring(i, i + needle.length()).equals(needle)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String divide(){
        System.out.println(divide(100, 3));
        return "success";
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        //用异或来计算是否符号相异
        boolean negative = (dividend ^ divisor) < 0;
        long t = Math.abs((long) dividend), d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            //找出足够大的数2^n * divisor
            if ((t >> i) >= d) {
                //将结果加上2^n
                result += 1 << i;
                //将被除数减去2^n*divisor
                t -= d << i;
            }
        }
        return negative ? -result : result;
    }

    @Override
    public String findSubstring(){
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        return "success";
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // 所有单词的个数
        int num = words.length;
        // 每个单词的长度（是相同的）
        int wordLen = words[0].length();
        // 字符串长度
        int stringLen = s.length();

        for (int i = 0; i < wordLen; i++) {
            // 遍历的长度超过了整个字符串的长度，退出循环
            if (i + num * wordLen > stringLen) {
                break;
            }
            // differ表示窗口中的单词频次和words中的单词频次之差
            Map<String, Integer> differ = new HashMap<>();
            // 初始化窗口，窗口长度为num * wordLen,依次计算窗口里每个切分的单词的频次
            for (int j = 0; j < num; j++) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            // 遍历words中的word，对窗口里每个单词计算差值
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                // 差值为0时，移除掉这个word
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            // 开始滑动窗口
            for (int start = i; start < stringLen - num * wordLen + 1; start += wordLen) {
                if (start != i) {
                    // 右边的单词滑进来
                    String word = s.substring(start + (num - 1) * wordLen, start + num * wordLen);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    // 左边的单词滑出去
                    word = s.substring(start - wordLen, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - wordLen, start);
                }
                // 窗口匹配的单词数等于words中对应的单词数
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    @Override
    public String nextPermutation(){
        int[] nums = new int[]{5,4,7,5,3,2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        return "success";
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 反转数组
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Override
    public String longestValidParentheses(){
        System.out.println(longestValidParentheses("(()(()())"));
        return "success";
    }

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    @Override
    public String search(){
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        return "success";
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    @Override
    public String searchRange(){
        System.out.println(JSON.toJSONString(searchRange(new int[]{2,2,2}, 2)));
        return "success";
    }

    public int[] searchRange(int[] nums, int target) {
        int res[] = new int[] {-1, -1};
        if (nums.length == 0) {
            return res;
        }

        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                // 找第一个目标值
                int i = mid;
                while (i > -1 && nums[i] == target) {
                    i--;
                }
                res[0] = ++i;
                // 找最后一个目标值
                i = mid;
                while (i < nums.length && nums[i] == target) {
                    i++;
                }
                res[1] = --i;
                return res;
            }
            mid = (low + high) / 2;
        }

        return res;
    }

    @Override
    public String searchInsert(){
        System.out.println(JSON.toJSONString(searchInsert(new int[]{1,3,5,6}, 2)));
        return "success";
    }

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (right + left) >> 1;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Override
    public String isValidSudoku(){
        System.out.println(JSON.toJSONString(isValidSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}})));
        return "success";
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String solveSudoku(){
        char[][] arr = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(arr);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        // 一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，一行一列确定下来之后，递归遍历这个位置放9个数字的可能性
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    continue;
                }
                for (char k = '1'; k <= '9'; k++){
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if (solveSudokuHelper(board)){
                            // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     * 同行是否重复
     * 同列是否重复
     * 9宫格里是否重复
     */
    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String countAndSay(){
        System.out.println(JSON.toJSONString(countAndSay(4)));
        return "success";
    }

    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        String num = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            int count = 1;
            while (i < num.length() - 1 && num.charAt(i) == num.charAt(i + 1)){
                count++;
                i++;
            }
            sb.append(count);
            sb.append(num.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public String combinationSum(){
        System.out.println(JSON.toJSONString(combinationSum(new int[]{2,3,6,7}, 7)));
        return "success";
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int sum = 0;
        int startIndex = 0;
        backtracking(ans, temp, candidates, target, sum, startIndex);
        return ans;
    }

    private void backtracking(List<List<Integer>> ans, List<Integer> temp, int[] candidates, int target, int sum, int startIndex){
        if(sum > target) {
            return;
        }else if(sum == target){
            ans.add(new ArrayList<>(temp));
        }
        for(int i = startIndex; i < candidates.length; i++){
            temp.add(candidates[i]);
            sum += candidates[i];
            backtracking(ans, temp, candidates, target, sum, i);
            sum -= candidates[i];
            temp.remove(temp.size() - 1);
        }
    }

    @Override
    public String combinationSum2(){
        System.out.println(JSON.toJSONString(combinationSum2(new int[]{2,5,2,1,2}, 5)));
        return "success";
    }

    List<int[]> freq = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    private void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

    @Override
    public String firstMissingPositive(){
        System.out.println(JSON.toJSONString(firstMissingPositive(new int[]{3,4,-1,1})));
        return "success";
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    @Override
    public String trap(){
        System.out.println(JSON.toJSONString(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})));
        return "success";
    }

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxL = height[left], maxR = height[right];
        int res = 0;
        while (left < right) {
            maxL = Math.max(maxL, height[left]);
            maxR = Math.max(maxR, height[right]);
            res += maxR > maxL ? maxL - height[left++] : maxR - height[right--];
        }
        return res;
    }

    @Override
    public String multiply(){
        System.out.println(JSON.toJSONString(multiply("123", "456")));
        return "success";
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    @Override
    public String isMatch(){
        System.out.println(JSON.toJSONString(isMatch2("babecce", "*a*e")));
        return "success";
    }

    public boolean isMatch2(String s, String p) {
        boolean[][] value = new boolean[p.length() + 1][s.length() + 1];
        value[0][0] = true;
        for(int i = 1; i <= s.length(); i++){
            value[0][i] = false;
        }
        for(int i = 1; i <= p.length(); i++){
            if(p.charAt(i - 1) == '*'){
                value[i][0] = value[i - 1][0];
                for(int j = 1; j <= s.length(); j++){
                    //表示为空时状态 || 之前内容成立状态的延续
                    value[i][j] = value[i][j - 1] || value[i - 1][j];
                }
            }else if(p.charAt(i - 1) == '?'){
                value[i][0] = false;
                for(int j = 1; j <= s.length(); j++){
                    //之前数据的表示状态
                    value[i][j] = value[i - 1][j - 1];
                }
            }else {
                value[i][0] = false;
                for(int j = 1; j <= s.length(); j++){
                    //两个值相同 && 之前数据的表示状态
                    value[i][j] = s.charAt(j - 1) == p.charAt(i - 1) && value[i - 1][j - 1];
                }
            }
        }
        return value[p.length()][s.length()];
    }

    @Override
    public String jump(){
        System.out.println(JSON.toJSONString(jump(new int[]{2,3,1,1,4})));
        return "success";
    }

    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0, maxPosition = 0, steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    @Override
    public String permute(){
        System.out.println(JSON.toJSONString(permute(new int[]{1,2,3})));
        return "success";
    }

    List<List<Integer>> permuteList = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        dfs(list,nums);
        return permuteList;
    }

    private void dfs(List<Integer> list,int[] nums){
        if(list.size() == nums.length){
            permuteList.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                dfs(list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    @Override
    public String permuteUnique(){
        System.out.println(JSON.toJSONString(permuteUnique(new int[]{1,2,3})));
        return "success";
    }

    private List<List<Integer>> permuteUniqueResultListList = new ArrayList<>();
    private LinkedList<Integer> permuteUniqueList = new LinkedList<>();
    private boolean[] permuteUniqueUsedArr;
    public List<List<Integer>> permuteUnique(int[] nums) {
        permuteUniqueUsedArr = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums);
        return permuteUniqueResultListList;
    }

    private void backtracking(int[] nums) {
        if (permuteUniqueList.size() == nums.length) {
            permuteUniqueResultListList.add(new ArrayList<>(permuteUniqueList));
        }
        for (int i = 0; i < nums.length; ++i) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !permuteUniqueUsedArr[i - 1]) {
                continue;
            }
            if (permuteUniqueUsedArr[i]) {
                continue;
            }
            permuteUniqueUsedArr[i] = true;
            permuteUniqueList.add(nums[i]);
            backtracking(nums);
            permuteUniqueList.removeLast();
            permuteUniqueUsedArr[i] = false;
        }
    }

    @Override
    public String rotate(){
        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(arr);
        return "success";
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 上下翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                swap(matrix, i, j, n - i - 1, j);
            }
        }
        // 对角线翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }

    @Override
    public String groupAnagrams(){
        System.out.println(JSON.toJSONString(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})));
        return "success";
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            map.computeIfAbsent(String.valueOf(ch), value -> new ArrayList<>()).add(s);
        }
        return new ArrayList(map.values());
    }

    @Override
    public String solveNQueens(){
        System.out.println(JSON.toJSONString(solveNQueens(6)));
        return "success";
    }

    private List<List<String>> solveNQueenList = new ArrayList<>();

    // boolean数组中的每个元素代表一条直(斜)线
    private boolean[] usedCol, usedDiag45, usedDiag135;

    public List<List<String>> solveNQueens(int n) {
        // 列方向的直线条数为 n
        usedCol = new boolean[n];
        // 45°方向的斜线条数为 2 * n - 1
        usedDiag45 = new boolean[2 * n - 1];
        // 135°方向的斜线条数为 2 * n - 1
        usedDiag135 = new boolean[2 * n - 1];
        //用于收集结果, 元素的index表示棋盘的row，元素的value代表棋盘的column
        int[] board = new int[n];
        backTracking(board, n, 0);
        return solveNQueenList;
    }

    private void backTracking(int[] board, int n, int row) {
        if (row == n) {
            //收集结果
            List<String> temp = new ArrayList<>();
            for (int i : board) {
                char[] str = new char[n];
                Arrays.fill(str, '.');
                str[i] = 'Q';
                temp.add(new String(str));
            }
            solveNQueenList.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (usedCol[col] | usedDiag45[row + col] | usedDiag135[row - col + n - 1]) {
                continue;
            }
            board[row] = col;
            // 标记该列出现过
            usedCol[col] = true;
            // 同一45°斜线上元素的row + col为定值, 且各不相同
            usedDiag45[row + col] = true;
            // 同一135°斜线上元素row - col为定值, 且各不相同
            // row - col 值有正有负, 加 n - 1 是为了对齐零点
            usedDiag135[row - col + n - 1] = true;
            // 递归
            backTracking(board, n, row + 1);
            usedCol[col] = false;
            usedDiag45[row + col] = false;
            usedDiag135[row - col + n - 1] = false;
        }
    }

    @Override
    public String totalNQueens(){
        System.out.println(JSON.toJSONString(totalNQueens(4)));
        return "success";
    }

    private int totalNQueensCount = 0;

    public int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        dfs( 0, 0, 0, 0, n);
        return totalNQueensCount;
    }

    private void dfs(int row, int col, int pie, int na, int n) {
        if (row >= n) {
            totalNQueensCount++;
            return;
        }
        // 获取当前空位 标识为1  &  去掉所有高位
        int bit = (~(col | pie | na)) & ((1 << n) - 1);
        //遍历所有空位
        while (bit > 0){
            //获取最后的空位 1(用二进制表示实际上此行皇后的位点)
            int p = bit & -bit;
            //col | p 表示 p 位被占用(用二进制表示所有行皇后列的位点)
            //(pie | p ) << 1 ,表示pie往斜左方移一位 被占用
            //(na | p) >> 1,表示na往斜右方移一位 被占用
            dfs(row + 1, col | p, (pie | p ) << 1, (na | p) >> 1, n);
            // 打掉最后的1
            bit &= (bit - 1);
        }
    }

    @Override
    public String canJump(){
        System.out.println(JSON.toJSONString(canJump(new int[]{3,2,1,0,4})));
        return "success";
    }

    public boolean canJump(int[] nums) {
        int residue = 0, length = nums.length;
        for(int i = 0; i < length; i++){
            residue = Math.max(nums[i], residue);
            residue--;
            if(residue == -1 && i != length - 1){
                return false;
            }
        }
        return true;
    }

    @Override
    public String merge(){
        System.out.println(JSON.toJSONString(merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
        return "success";
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> list = new ArrayList<>();
        int[] let = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= let[1]) {
                let[1] = Math.max(let[1], intervals[i][1]);
            } else {
                list.add(let);
                let = intervals[i];
            }
        }
        list.add(let);
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    @Override
    public String insert(){
        System.out.println(JSON.toJSONString(insert(new int[][]{{1,2},{3,5},{6,9}}, new int[]{-2,1})));
        return "success";
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    @Override
    public String lengthOfLastWord(){
        System.out.println(JSON.toJSONString(lengthOfLastWord("   fly me   to   the moon  ")));
        return "success";
    }

    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        for(; i >= 0; i--){
            if(s.charAt(i) != ' '){
                break;
            }
        }
        int length = 0;
        for(; i >= 0; i--){
            if(s.charAt(i) != ' '){
                length++;
            }else{
                break;
            }
        }
        return length;
    }

    @Override
    public String generateMatrix(){
        System.out.println(JSON.toJSONString(generateMatrix(5)));
        return "success";
    }

    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            // left to right.
            for(int i = l; i <= r; i++) {
                mat[t][i] = num++;
            }
            t++;
            // top to bottom.
            for(int i = t; i <= b; i++) {
                mat[i][r] = num++;
            }
            r--;
            // right to left.
            for(int i = r; i >= l; i--) {
                mat[b][i] = num++;
            }
            b--;
            // bottom to top.
            for(int i = b; i >= t; i--) {
                mat[i][l] = num++;
            }
            l++;
        }
        return mat;
    }

    @Override
    public String getPermutation(){
        System.out.println(JSON.toJSONString(getPermutation(4, 9)));
        return "success";
    }

    /**
     * 首先确定排列中的首个元素
     *      以1开头的有(n - 1)! 个
     *      以2开头的有(n - 1)! 个
     *      以n开头的有(n - 1)! 个
     *      因此:
     *          如果 k <= (n - 1)!, 那么首个元素是1
     *          如果 (n - 1)! < k <= 2 * (n - 1)!, 那么首个元素是2
     *          如果 (n - 1) * (n - 1)! < k <= n * (n - 1)!, 那么首个元素是n
     *          那么第k个排列的首个元素 k = (k - 1) / (n - 1)! + 1
     * 其次确认第二个元素
     *      计算第二个元素的排列k' = (k - 1) % ((n - 1)!) + 1
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuilder ans = new StringBuilder();
        //用来记录数字使用的状态, 为1即未使用, 为0即已使用
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; i++) {
            //valid数组中, 第order个未使用的数字  即为剩余排序的首数字
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; j++) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }

    @Override
    public String rotateRight(){
        ListNode node1 = new ListNode(5, null);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(1, node4);
        ListNode listNode = rotateRight(node5, 2);
        System.out.print("[");
        for(;;){
            System.out.print(listNode.val);
            if(listNode.next == null){
                break;
            }
            listNode = listNode.next;
            System.out.print(",");
        }
        System.out.print("]");
        return "success";
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        int length = 1;
        while(pre.next != null){
            pre = pre.next;
            length++;
        }
        int diff = length - k % length;
        if(diff == length){
            return head;
        }
        pre = head;
        for(int i = 1; i < diff; i++){
            pre = pre.next;
        }
        ListNode tail = pre.next;
        pre.next = null;
        pre = tail;
        while(pre.next != null){
            pre = pre.next;
        }
        pre.next = head;
        return tail;
    }

    @Override
    public String uniquePaths(){
        System.out.println(JSON.toJSONString(uniquePaths(7, 6)));
        return "success";
    }

    /**
     * 从左上角到右下角的过程中，我们需要移动 m + n − 2 次
     *      有 m − 1 次向下移动
     *      有 n − 1 次向右移动
     * 因此路径的总数，就等于从 m + n − 2 次移动中选择 m − 1 次向下移动的方案数
     * 即组合数：(m + n - 2)! / ((m - 1)! * (n - 1)!)
     */
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

//    /**
//     * 常规-动态规划
//     * f(i,j) = f(i−1, j) + f(i, j−1)
//     */
//    public int uniquePaths(int m, int n) {
//        int[] arr = new int[m + 1];
//        arr[1] = 1;
//        for(; n > 0; n--){
//            for(int i = 1; i <= m; i++){
//                arr[i] = arr[i - 1] + arr[i];
//            }
//        }
//        return arr[m];
//    }

    @Override
    public String uniquePathsWithObstacles(){
        System.out.println(JSON.toJSONString(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));
        return "success";
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] arr = new int[m];

        arr[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    arr[j] = 0;
                    continue;
                }
                if (j > 0 && obstacleGrid[i][j - 1] == 0) {
                    arr[j] += arr[j - 1];
                }
            }
        }
        return arr[m - 1];
    }

    @Override
    public String minPathSum(){
        System.out.println(JSON.toJSONString(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}})));
        return "success";
    }

    public int minPathSum(int[][] grid) {
        int x = grid[0].length, y = grid.length;
        int[] arr = new int[x + 1];
        arr[0] = Integer.MAX_VALUE;
        arr[1] = grid[0][0];
        for(int i = 1; i < x; i++){
            arr[i + 1] = arr[i] + grid[0][i];
        }
        for(int j = 1; j < y; j++){
            for(int i = 0; i < x; i++){
               arr[i + 1] = Math.min(arr[i], arr[i + 1]) + grid[j][i];
            }
        }
        return arr[x];
    }

    @Override
    public String plusOne(){
        System.out.println(JSON.toJSONString(plusOne(new int[]{1,2,3})));
        return "success";
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    @Override
    public String addBinary(){
        System.out.println(JSON.toJSONString(addBinary("101", "1")));
        return "success";
    }

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    @Override
    public String fullJustify(){
        System.out.println(JSON.toJSONString(fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 16)));
        return "success";
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        //words中当前指针
        int right = 0;
        int n = words.length;
        while (true) {
            //当前行中第一个单词在words中的指针
            int left = right;
            //当前行单词长度
            int sumLength = 0;
            //判断当前行能放多少单词，每个单词有一个空格（right - left）
            while (right < n && sumLength + words[right].length() + right - left <= maxWidth) {
                sumLength += words[right++].length();
            }
            //当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuilder builder = new StringBuilder();
                //每个单词后拼接一个空格
                builder.append(join(words, left, right, " "));
                //最后全拼接空格
                builder.append(blank(maxWidth - builder.length()));
                //加入结果集
                ans.add(builder.toString());
                return ans;
            }
            int sumWord = right - left;
            //当只有一个单词时
            if (sumWord == 1) {
                //拼接单词
                StringBuilder builder = new StringBuilder(words[left]);
                //最后全拼接空格
                builder.append(blank(maxWidth - builder.length()));
                //加入结果集
                ans.add(builder.toString());
                continue;
            }
            //当有多个单词时
            //空格数
            int numSpace = maxWidth - sumLength;
            //平均每个单词后面的空格数，（为什么sumWord - 1：最后一个单词不拼空格）
            int avgSpace = numSpace / (sumWord - 1);
            //每个单词分配avgSpace个空格后余出来的空格数
            int extraSpaces = numSpace % (sumWord - 1);
            StringBuilder builder = new StringBuilder();
            //给前extraSpaces个单词 拼接额外加一个空格的单词
            builder.append(join(words, left, left + extraSpaces + 1, blank(avgSpace + 1)));
            //拼接avgSpace个空格（因为拼接上一个拼接字符是最后一个字符没有拼接空格）
            builder.append(blank(avgSpace));
            // 其余单词拼接avgSpace个空格
            builder.append(join(words, left + extraSpaces + 1, right, blank(avgSpace)));
            //加入结果集
            ans.add(builder.toString());
        }
    }

    /**
     * 返回长度为 n 的由空格组成的字符串
     */
    private String blank(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(' ');
        }
        return builder.toString();
    }

    /**
     * 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
     */
    private String join(String[] words, int left, int right, String sep) {
        StringBuilder builder = new StringBuilder(words[left]);
        for (int i = left + 1; i < right; i++) {
            //空格加在前面需要注意
            builder.append(sep);
            builder.append(words[i]);
        }
        return builder.toString();
    }

    @Override
    public String mySqrt(){
        System.out.println(JSON.toJSONString(mySqrt(183692038)));
        return "success";
    }

    public int mySqrt(int x) {
        if(x == 1){
            return 1;
        }
        int min = 0, max = x;
        while(max - min > 1) {
            int mid = (max + min) / 2;
            if(x / mid < mid){
                max = mid;
            } else{
                min = mid;
            }
        }
        return min;
    }

    @AllArgsConstructor
    @Data
    class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Override
    public String climbStairs(){
        System.out.println(JSON.toJSONString(climbStairs(3)));
        return "success";
    }

    public int climbStairs(int n) {
        if(n <= 2) {
            return n;
        }
        int a = 1, b = 2, sum;

        for(int i = 3; i <= n; i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    @Override
    public String simplifyPath(){
        System.out.println(JSON.toJSONString(simplifyPath("/a/./b/../../c/")));
        return "success";
    }

    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            // 对于两个点，需要将目录切换到上一级
            if ("..".equals(name)) {
                // 所以只要栈不为空【因为不管如何取上一级，到/根目录也就停了】，就弹出栈顶元素
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                // 不是空字符串或者一个点
                // 否则就是目录名，直接入栈
                stack.offerLast(name);
            }
        }
        // 全部对last进行poll或者offer就是栈的形式
        StringBuilder builder = new StringBuilder();
        if (stack.isEmpty()) {
            builder.append('/');
        } else {
            while (!stack.isEmpty()) {
                builder.append('/').append(stack.pollFirst());
            }
        }
        return builder.toString();
    }

    @Override
    public String minDistance(){
        System.out.println(JSON.toJSONString(minDistance("horse", "ros")));
        return "success";
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] =  i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //元素相同, 什么都不用改动
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    @Override
    public String setZeroes(){
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        System.out.println(JSON.toJSONString(matrix));
        return "success";
    }

    public void setZeroes(int[][] matrix) {
        int[] rowArr = new int[matrix.length];
        int[] colArr = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    rowArr[i] = 1;
                    colArr[j] = 1;
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(colArr[j] == 1 || rowArr[i] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    @Override
    public String sortColors(){
        int[] arr = new int[]{2,2,1};
        sortColors(arr);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    @Override
    public String minWindow(){
        System.out.println(JSON.toJSONString(minWindow("ADOBECODEBANC", "ABC")));
        return "success";
    }

    public String minWindow(String s, String t) {
        int[] arr = new int[128];
        int length = t.length(), cnt = 0;
        for (int i = 0; i < length; i++) {
            if (arr[t.charAt(i)]++ == 0) {
                cnt++;
            }
        }
        int n = s.length(), count = 0;
        int left = 0, right = 0;
        int rLeft = 0, rRight = Integer.MAX_VALUE;
        int[] window = new int[128];
        while (right < n) {
            int index = s.charAt(right);
            right++;
            window[index]++;
            if (window[index] == arr[index]) {
                count++;
            }
            while (count == cnt) {
                if (right - left < rRight - rLeft) {
                    rLeft = left;
                    rRight = right;
                }
                int li = s.charAt(left);
                left++;
                window[li]--;
                if (window[li] < arr[li]) {
                    count--;
                }
            }
        }
        return rRight - rLeft == Integer.MAX_VALUE ? "" : s.substring(rLeft, rRight);
    }

    @Override
    public String combine(){
        System.out.println(JSON.toJSONString(combine(1,1)));
        return "success";
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }

        // 为了防止底层动态数组扩容，初始化的时候传入最大长度
        Deque<Integer> path = new ArrayDeque<>(k);
        dfs(1, n, k, path, res);
        return res;
    }

    private void dfs(int begin, int n, int k, Deque<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 基础版本的递归终止条件：if (begin == n + 1) {
        if (begin > n - k + 1) {
            return;
        }
        // 不选当前考虑的数 begin，直接递归到下一层
        dfs(begin + 1, n, k, path, res);

        // 不选当前考虑的数 begin，递归到下一层的时候 k - 1，这里 k 表示还需要选多少个数
        path.addLast(begin);
        dfs(begin + 1, n, k - 1, path, res);
        // 深度优先遍历有回头的过程，因此需要撤销选择
        path.removeLast();
    }

    @Override
    public String subsets(){
        int[] nums = new int[]{1,2,3};
        System.out.println(JSON.toJSONString(subsets(nums)));
        return "success";
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(0));
        for (int num : nums) {
            int size = res.size();
            //当前元素的所有可能是 前面每种可能 + 当前元素
            for (int j = 0; j < size; j++) {
                List<Integer> list = new ArrayList<>(res.get(j));
                list.add(num);
                res.add(list);
            }
        }
        return res;
    }


    @Override
    public String exist(){
        System.out.println(JSON.toJSONString(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB")));
        return "success";
    }

    public boolean exist(char[][] board, String word) {
        char firstChar = word.charAt(0);
        boolean[][] arr = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == firstChar){
                    if(exist(board, word, i, j, 0, arr)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int i, int j, int index, boolean[][] arr) {
        if(index == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(index) != board[i][j] || arr[i][j]){
            return false;
        }
        arr[i][j] = true;
        if(exist(board, word, i + 1, j, index + 1, arr) || exist(board, word, i - 1, j, index + 1, arr)
                || exist(board, word, i, j + 1, index + 1, arr) || exist(board, word, i, j - 1, index + 1, arr)){
            return true;
        }else{
            arr[i][j] = false;
            return false;
        }
    }

    @Override
    public String removeDuplicates80(){
        int[] arr = new int[]{1,2,2};
        System.out.println(removeDuplicates80(arr));
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public int removeDuplicates80(int[] nums) {
        if(nums.length <= 2){
            return nums.length;
        }
        int count = 2;
        for(int i = 2 ; i < nums.length ; i++) {
            if(nums[i] != nums[count - 2]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    @Override
    public String search81(){
        System.out.println(JSON.toJSONString(search81(new int[]{1,0,1,1,1}, 6)));
        return "success";
    }

    public boolean search81(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return true;
            }
            if(nums[left] == nums[mid] && nums[mid] == nums[right]){
                left++;
                right--;
            }else if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    @Override
    public String deleteDuplicates(){
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(4, node4);
        ListNode node6 = new ListNode(4, node5);
        ListNode node7 = new ListNode(5, node6);
        System.out.println(JSON.toJSONString(deleteDuplicates(node7)));
        return "success";
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode listNode = new ListNode(0, head);
        head = listNode;
        while (listNode.next != null && listNode.next.next != null){
            if (listNode.next.val == listNode.next.next.val) {
                int val = listNode.next.val;
                while (listNode.next != null && listNode.next.val == val) {
                    listNode.next = listNode.next.next;
                }
            } else {
                listNode = listNode.next;
            }
        }
        return head.next;
    }

    @Override
    public String deleteDuplicates83(){
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(4, node4);
        ListNode node6 = new ListNode(4, node5);
        ListNode node7 = new ListNode(5, node6);
        System.out.println(JSON.toJSONString(deleteDuplicates83(node7)));
        return "success";
    }

    public ListNode deleteDuplicates83(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode listNode = head;
        while (head != null && head.next != null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return listNode;
    }

    @Override
    public String largestRectangleArea(){
        System.out.println(JSON.toJSONString(largestRectangleArea(new int[]{2,1,5,6,2,3})));
        return "success";
    }

    /**
     * 算法核心: 找到左右两侧 最近 && 比自己小的数
     * 最大面积 = 左右两侧数的最小值 * 两数之间的列差
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //对应索引下, 左侧比当前值小的索引
        int[] left = new int[n];
        //对应索引下, 右侧比当前值小的索引
        int[] right = new int[n];
        //如果数字全部一样会直接跳过去, 所以先填充最大值
        Arrays.fill(right, n);

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //deque不为空 && 当前值 <= 上个小数字
            while (!deque.isEmpty() && heights[i] <= heights[deque.peek()]) {
                //计算出右侧 最近 && 比自己小的位置
                right[deque.peek()] = i;
                deque.pop();
            }
            //计算出左侧 最近 && 比自己小的位置
            left[i] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    @Override
    public String maximalRectangle(){
        System.out.println(JSON.toJSONString(maximalRectangle(new char[][]{{'1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','0','0','0'},{'0','1','1','1','1','0','0','0'}})));
        return "success";
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + 1;
                int maxArea = dp[i][j], minLength = dp[i][j];
                for (int height = 2; i >= height && matrix[i - height][j - 1] != '0'; height++) {
                    minLength = Math.min(minLength, dp[i - height + 1][j]);
                    maxArea = Math.max(maxArea, height * minLength);
                }
                res = Math.max(res, maxArea);
            }
        }
        return res;
    }

    @Override
    public String partition(){
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(5, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(3, node4);
        ListNode node6 = new ListNode(4, node5);
        ListNode node7 = new ListNode(1, node6);
        System.out.println(JSON.toJSONString(partition(node7, 2)));
        return "success";
    }

    public ListNode partition(ListNode head, int x) {
        ListNode beforeListNode = new ListNode(0, null);
        ListNode before = beforeListNode;
        ListNode afterListNode = new ListNode(0, null);
        ListNode after = afterListNode;
        while(head != null){
            if(head.val < x){
                beforeListNode.next = head;
                beforeListNode = beforeListNode.next;
            }else{
                afterListNode.next = head;
                afterListNode = afterListNode.next;
            }
            head = head.next;
        }
        afterListNode.next = null;
        beforeListNode.next = after.next;
        return before.next;
    }

    @Override
    public String isScramble(){
        System.out.println(isScramble("great", "rgeat"));
        return "success";
    }

    /**
     * s1 = a1 + a2, s2 = b1 +　b2
     * 那么 有两种情况, 任满足其一即正确
     *      a1 是由 b1 变来 && a2 是由 b2 变来
     *      a1 是由 b2 变来 && a2 是由 b1 变来
     * 动态规划: dp[i][j][len]
     *      i: s1的坐标
     *      j: s2的坐标
     *      len: a1的长度
     */
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        boolean[][][] dp = new boolean[n][n][n + 1];
        //初始化当切割的字符串长度为1时, 是否相同
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = char1[i] == char2[j];
            }
        }
        //a1的长度 len,最少切2故从2开始
        for(int len = 2; len <= n; len++){
            //a1的校验范围: [0, n - len]
            for(int i = 0; i <= n - len; i++){
                //与a1匹配的字符串(b1 / b2)的校验范围: [0, n - len], 因为两者长度一致
                for(int j = 0; j <= n - len; j++){
                    //循环遍历每个元素是否满足规则
                    for(int k = 1; k <= len - 1; k++){
                        if(dp[i][j][k] && dp[i + k][j + k][len - k]){
                            //a1 是由 b1 变来 && a2 是由 b2 变来
                            dp[i][j][len] = true;
                            break;
                        }else if(dp[i][j + len - k][k] && dp[i + k][j][len - k]){
                            //a1 是由 b2 变来 && a2 是由 b1 变来
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

    public boolean isScramble1(String s1, String s2) {
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        // 初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        // 枚举区间长度 2～n
        for (int len = 2; len <= n; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len-k ，S2 起点 i + 前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

    @Override
    public String merge88(){
        int[] num1 = new int[]{1,2,3,0,0,0};
        merge(num1, 3, new int[]{2,5,6}, 3);
        System.out.println(JSON.toJSONString(num1));
        return "success";
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, l = nums1.length - 1;
        for(; l >= 0 && i >= 0 && j >= 0; l--){
            if(nums1[i] >= nums2[j]){
                nums1[l] = nums1[i];
                i--;
            }else{
                nums1[l] = nums2[j];
                j--;
            }
        }
        while(j >= 0){
            nums1[l] = nums2[j];
            l--;
            j--;
        }
    }

    @Override
    public String grayCode(){
        System.out.println(JSON.toJSONString(grayCode(2)));
        return "success";
    }

    /**
     * 实现格雷码
     * n = 1  [0, 1]
     * n = 2  [00，01，11，10]
     * n = 3  [000, 001, 011, 010, 110, 111, 101, 100]
     * ....
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }

    @Override
    public String reverseBetween(){
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(5, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(3, node4);
        ListNode node6 = new ListNode(4, node5);
        ListNode node7 = new ListNode(1, node6);
        System.out.println(JSON.toJSONString(reverseBetween(node7, 2, 3)));
        return "success";
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    @Override
    public String restoreIpAddresses(){
        System.out.println(JSON.toJSONString(restoreIpAddresses("25525511135")));
        return "success";
    }

    List<String> ipList = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        restoreIpAddresses(s, 0, n, new ArrayList<>(), 0);
        return ipList;
    }

    public void restoreIpAddresses(String s, int startIndex, int n, List<Integer> list, int loopCount) {
        if(loopCount >= 4){
            if(startIndex == n){
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    stringBuilder.append(list.get(i)).append(".");
                }
                ipList.add(stringBuilder.substring(0, stringBuilder.length() - 1));
            }
            return;
        }
        for (int i = 1; i < 4 && startIndex + i <= n; i++) {
            if(i != 1 && s.charAt(startIndex) == '0'){
                continue;
            }
            int a = Integer.parseInt(s.substring(startIndex, startIndex + i));
            if(i == 3 && a > 255){
                continue;
            }
            list.add(a);
            restoreIpAddresses(s, startIndex + i, n, list, loopCount + 1);
            list.remove(list.size() - 1);
        }
    }

    @Override
    public String inorderTraversal(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(6);
        System.out.println(JSON.toJSONString(inorderTraversal(treeNode)));
        return "success";
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
        list.add(root.val);
        inorderTraversal(root.left, list);
        inorderTraversal(root.right, list);
    }

    @Override
    public String generateTrees(){
        System.out.println(JSON.toJSONString(generateTrees(3)));
        return "success";
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if(start > end){
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点
        for(int i = start; i <= end; i++){
            // 获得所有可行的左子树集合
            List<TreeNode> leftList = generateTrees(start, i - 1);
            // 获得所有可行的右子树集合
            List<TreeNode> rightList = generateTrees(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;
                    allTrees.add(treeNode);
                }
            }
        }
        return allTrees;
    }

    @Override
    public String numTrees(){
        System.out.println(JSON.toJSONString(numTrees(3)));
        return "success";
    }

    /**
     * 当节点为n时, 结果数 = 每种根节点下(左树可能的情况数 * 右树可能的情况数)之和
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //i 为 节点个数
        for(int i = 2; i <= n; i++){
            // j为根节点的值
            for (int j = 1; j <= i; j++) {
                int left = dp[j - 1];
                int right = dp[i - j];
                dp[i] += left * right;
            }
        }
        return dp[n];
    }

    @Override
    public String isInterleave(){
        System.out.println(JSON.toJSONString(isInterleave("aabcc", "dbbca", "aadbbcbcac")));
        return "success";
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        if (m + n != s3.length()) {
            return false;
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i > 0){
                    dp[j] = dp[j] && s3.charAt(i + j - 1) == s1.charAt(i - 1);
                }
                if(j > 0){
                    dp[j] = dp[j] || (dp[j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
                }
            }
        }

        return dp[n];
    }

    @Override
    public String isValidBST(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(6);
        System.out.println(JSON.toJSONString(isValidBST(treeNode)));
        return "success";
    }

    public boolean isValidBST(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;

        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    @Override
    public String recoverTree(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(2);
        recoverTree(treeNode);
        System.out.println(JSON.toJSONString(treeNode));
        return "success";
    }

    public void recoverTree(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }
        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    @Override
    public String isSameTree(){
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(JSON.toJSONString(isSameTree(p, q)));
        return "success";
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs(p, q);
    }

    public boolean dfs(TreeNode p, TreeNode q){
        if(p == null && q == null) {
            return true;
        }
        if(p == null || q == null) {
            return false;
        }
        return p.val == q.val && dfs(p.left, q.left) && dfs(p.right, q.right);
    }

    @Override
    public String zigzagLevelOrder(){
        TreeNode treeNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(JSON.toJSONString(zigzagLevelOrder(treeNode)));
        return "success";
    }

    Map<Integer, List<Integer>> zigzagLevelOrderMap = new HashMap<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        dfs(root, 0);
        List<List<Integer>> listList = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : zigzagLevelOrderMap.entrySet()) {
            if(entry.getKey() % 2 == 0){
                listList.add(entry.getValue());
            }else{
                Collections.reverse(entry.getValue());
                listList.add(entry.getValue());
            }
        }
        return listList;
    }

    public void dfs(TreeNode root, int level) {
        if(root == null){
            return;
        }
        zigzagLevelOrderMap.computeIfAbsent(level, v -> new ArrayList<>()).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    @Override
    public String buildTree(){
        System.out.println(JSON.toJSONString(buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3})));
        return "success";
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                //两值不相等, 所以此为右节点
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                //两值相等, 所以此为左节点
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    //弹出右节点直至最后一个, 此节点为最后一个节点的左节点
                    node = stack.pop();
                    //更改偏移量
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

    @Override
    public String levelOrderBottom(){
        TreeNode treeNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(JSON.toJSONString(levelOrderBottom(treeNode)));
        return "success";
    }

    Map<Integer, List<Integer>> levelOrderBottomMap = new HashMap<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        dfs(root, 0);
        List<List<Integer>> listList = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : levelOrderBottomMap.entrySet()) {
            listList.add(0, entry.getValue());
        }
        return listList;
    }

    @Override
    public String sortedArrayToBST(){
        System.out.println(JSON.toJSONString((new int[]{-10,-3,0,5,9})));
        return "success";
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    @Override
    public String sortedListToBST(){
        ListNode listNode = new ListNode(-10, new ListNode(-3, new ListNode(0, new ListNode(5, new ListNode(9)))));
        System.out.println(JSON.toJSONString(sortedListToBST(listNode)));
        return "success";
    }

    /**
     * 双指针 + 递归
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        //只有一个节点, 则直接返回
        if (head.next == null){
            return new TreeNode(head.val);
        }
        //找出当前链表中间位置(偶数个节点则取中间靠后那个节点), slow节点最终的位置即为中间节点
        ListNode slow = head;
        ListNode fast = head;
        ListNode slowPre = slow;
        while (fast != null && fast.next != null){
            slowPre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //断开slow前面的节点和slow的连接
        slowPre.next = null;
        return new TreeNode(slow.val, sortedListToBST(head), sortedListToBST(slow.next));
    }

    @Override
    public String minDepth(){
        TreeNode treeNode = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(5);
        treeNode.right.right.right.right = new TreeNode(6);
        System.out.println(JSON.toJSONString(minDepth(treeNode)));
        return "success";
    }

    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }else if(root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        }else if(root.left != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }else {
            return 1;
        }
    }

    @Override
    public String hasPathSum(){
        TreeNode treeNode = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(JSON.toJSONString(hasPathSum(treeNode, 3)));
        return "success";
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    @Override
    public String flatten(){
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));
        flatten(treeNode);
        return "success";
    }

    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        if(root.left != null){
            if(root.right != null){
                TreeNode left = root.left;
                while(left.right != null){
                    left = left.right;
                }
                left.right = root.right;
            }
            root.right = root.left;
            root.left = null;
        }
    }

    @Override
    public String numDistinct(){
        System.out.println(JSON.toJSONString(numDistinct("rabbbit","rabbit")));
        return "success";
    }

//    /**
//     * 递推公式
//     *  值相同时: dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1]
//     *      当前的情况数 = 按照当前相同元素造成的情况数 + 按照之前相同元素造成的情况数
//     *  值不同时: dp[i + 1][j + 1] = dp[i][j + 1];
//     *      当前的情况数 = 按照之前相同元素造成的情况数
//     */
//    public int numDistinct(String s, String t) {
//        int sLength = s.length();
//        int tLength = t.length();
//        if(sLength < tLength){
//            return 0;
//        }
//        int[][] dp = new int[sLength + 1][tLength + 1];
//        for (int i = 0; i < s.length(); i++) {
//            dp[i][0] = 1;
//        }
//        for (int i = 0; i < sLength; i++) {
//            char sChar = s.charAt(i);
//            for (int j = 0; j < tLength; j++) {
//                if(sChar == t.charAt(j)){
//                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
//                }else{
//                    dp[i + 1][j + 1] = dp[i][j + 1];
//                }
//            }
//        }
//        return dp[sLength][tLength];
//    }

    public int numDistinct(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        if(sLength < tLength){
            return 0;
        }
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            //逆序遍历, 因为在二维的递推公式中, 后面的值依赖之前的原始值, 正序的话之前的空位已经计算过了(非原始值)
            for (int j = t.length() - 1; j >= 0; j--) {
                if(sChar == t.charAt(j)){
                    dp[j + 1] = dp[j] + dp[j + 1];
                }
            }
        }
        return dp[tLength];
    }

    @Override
    public String connect(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        System.out.println(JSON.toJSONString(connect(node1)));
        return "success";
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                // 情况一: 设置当前节点的左节点下个为右节点
                head.left.next = head.right;
                // 情况二: 设置当前节点的右节点为next的左节点
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }

    @Override
    public String connect117(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;
        System.out.println(JSON.toJSONString(connect117(node1)));
        return "success";
    }

    private final List<Node> list117 = new ArrayList<>();

    public Node connect117(Node root) {
        // 根节点的深度为 0
        dfs117(root, 0);
        return root;
    }

    private void dfs117(Node node, int depth) {
        if (node == null) {
            return;
        }
        // node 是这一层最左边的节点
        if (depth == list117.size()) {
            list117.add(node);
        } else {
            // list117[depth] 是 node 左边的节点
            list117.get(depth).next = node;
            // node 左边的节点指向 node
            list117.set(depth, node);
        }
        dfs117(node.left, depth + 1);
        dfs117(node.right, depth + 1);
    }

    @Override
    public String generate(){
        System.out.println(JSON.toJSONString(generate(5)));
        return "success";
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            listList.add(new ArrayList<>());
            for (int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i){
                    listList.get(i).add(1);
                }else{
                    listList.get(i).add(listList.get(i - 1).get(j) + listList.get(i - 1).get(j - 1));
                }
            }
        }
        return listList;
    }

    @Override
    public String getRow(){
        System.out.println(JSON.toJSONString(getRow(5)));
        return "success";
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            list.add(0);
        }
        for (int i = 1; i <= rowIndex; i++) {
            for(int j = i; j > 0; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }
        return list;
    }

    @Override
    public String minimumTotal(){
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Lists.newArrayList(2));
        triangle.add(Lists.newArrayList(3,4));
        triangle.add(Lists.newArrayList(6,5,7));
        triangle.add(Lists.newArrayList(4,1,8,3));
        System.out.println(JSON.toJSONString(minimumTotal(triangle)));
        return "success";
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 只需要记录每一层的最小值即可
        int[] dp = new int[triangle.size()+1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> curTr = triangle.get(i);
            for (int j = 0; j < curTr.size(); j++) {
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
                dp[j] = Math.min(dp[j], dp[j+1]) + curTr.get(j);
            }
        }
        return dp[0];
    }

    @Override
    public String maxProfit(){
        System.out.println(JSON.toJSONString(maxProfit(new int[]{7,1,5,3,6,4})));
        return "success";
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int min = prices[0], result = 0;
        for(int i = 1; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            result = Math.max(prices[i] - min, result);
        }
        return result;
    }

    @Override
    public String maxProfit2(){
        System.out.println(JSON.toJSONString(maxProfit2(new int[]{7,1,5,3,6,4})));
        return "success";
    }

    public int maxProfit2(int[] prices) {
        int sum = 0;
        for(int i = 0; i < prices.length - 1; i++){
            if(prices[i + 1] > prices[i]){
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

    @Override
    public String maxProfit3(){
        System.out.println(JSON.toJSONString(maxProfit3(new int[]{1,2,4,2,5,7,2,4,9,0})));
        return "success";
    }

    /**
     * 对于任意一天考虑四个变量:
     * fstBuy: 在该天第一次买入股票可获得的最大收益
     * fstSell: 在该天第一次卖出股票可获得的最大收益
     * secBuy: 在该天第二次买入股票可获得的最大收益
     * secSell: 在该天第二次卖出股票可获得的最大收益
     * 分别对四个变量进行相应的更新, 最后secSell就是最大
     * 收益值(secSell >= fstSell)
     */
    public int maxProfit3(int[] prices) {
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int price : prices) {
            fstBuy = Math.max(fstBuy, -price);
            fstSell = Math.max(fstSell, fstBuy + price);
            secBuy = Math.max(secBuy, fstSell - price);
            secSell = Math.max(secSell, secBuy + price);
        }
        return secSell;
    }

    @Override
    public String maxPathSum(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        System.out.println(JSON.toJSONString(maxPathSum(treeNode)));
        return "success";
    }

    private int maxPathSumRet = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMax(root);
        return maxPathSumRet;
    }

    /**
     * 对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
     * 1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
     * 2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
     */
    private int getMax(TreeNode r) {
        if(r == null) {
            return 0;
        }
        // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int left = Math.max(0, getMax(r.left));
        int right = Math.max(0, getMax(r.right));
        // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        maxPathSumRet = Math.max(maxPathSumRet, r.val + left + right);
        return Math.max(left, right) + r.val;
    }

    @Override
    public String isPalindrome(){
        System.out.println(JSON.toJSONString(isPalindrome("A man, a plan, a canal: Panama")));
        return "success";
    }

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                stringBuilder.append(Character.toLowerCase(c));
            }
        }
        return stringBuilder.toString().contentEquals(stringBuilder.reverse());
    }

    @Override
    public String findLadders(){
        System.out.println(JSON.toJSONString(findLadders("hit", "cog", Lists.newArrayList("hot","dot","dog","lot","log","cog"))));
        return "success";
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
        Set<String> dict = new HashSet<>(wordList);
        // 特殊用例判断
        if (!dict.contains(endWord)) {
            return res;
        }

        dict.remove(beginWord);

        // 第 1 步：广度优先搜索建图
        // 记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先搜索的第几层
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系
        Map<String, List<String>> from = new HashMap<>();
        int step = 1;
        boolean found = false;
        int wordLen = beginWord.length();

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                // 将每一位替换成 26 个小写英文字母
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                            from.get(nextWord).add(currWord);
                        }
                        if (!dict.contains(nextWord)) {
                            continue;
                        }
                        // 如果从一个单词扩展出来的单词以前遍历过，距离一定更远，为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 dict 中删除
                        dict.remove(nextWord);
                        // 这一层扩展出的单词进入队列
                        queue.offer(nextWord);

                        // 记录 nextWord 从 currWord 而来
                        from.putIfAbsent(nextWord, new ArrayList<>());
                        from.get(nextWord).add(currWord);
                        // 记录 nextWord 的 step
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            found = true;
                        }
                    }
                    charArray[j] = origin;
                }
            }
            step++;
            if (found) {
                break;
            }
        }

        // 第 2 步：回溯找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            backtrack(from, path, beginWord, endWord, res);
        }
        return res;
    }

    public void backtrack(Map<String, List<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            backtrack(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }

    @Override
    public String ladderLength(){
        System.out.println(JSON.toJSONString(ladderLength("hit", "cog", Lists.newArrayList("hot","dot","dog","lot","log","cog"))));
        return "success";
    }

    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; i++) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<>());
        }
    }

    @Override
    public String longestConsecutive(){
        System.out.println(JSON.toJSONString(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1})));
        return "success";
    }

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int max = Math.min(length, 1);
        int res = max;
        for (int i = 1; i < length; i++) {
            if(nums[i] == nums[i - 1] + 1){
                max++;
            }else if(nums[i] != nums[i - 1]){
                res = Math.max(max, res);
                max = 1;
            }
        }
        return Math.max(max, res);
    }

    @Override
    public String sumNumbers(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.right.left = new TreeNode(3);
        treeNode.left.right.right = new TreeNode(5);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(7);
        treeNode.right.right = new TreeNode(9);
        System.out.println(JSON.toJSONString(sumNumbers(treeNode)));
        return "success";
    }

    int sum129 = 0;

    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sum129;
    }

    public void sumNumbers(TreeNode root, int value) {
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            sum129 += value * 10 + root.val;
            return;
        }
        sumNumbers(root.left, value * 10 + root.val);
        sumNumbers(root.right, value * 10 + root.val);
    }

    @Override
    public String solve(){
        char[][] chars = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(chars);
        System.out.println(JSON.toJSONString(chars));
        return "success";
    }

    public void solve(char[][] board) {
        int m = board.length, a = m - 1;
        int n = board[0].length, b = n - 1;
        if(m == 1 || n <= 1){
            return;
        }
        for (int i = 0; i < m; i++) {
            solve(board, i, 0);
            solve(board, i, b);
        }
        for (int i = 1; i < n; i++) {
            solve(board, 0, i);
            solve(board, a, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void solve(char[][] board, int i, int j) {
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'A';
        solve(board, i - 1, j);
        solve(board, i + 1, j);
        solve(board, i, j - 1);
        solve(board, i, j + 1);
    }

    boolean[][] dp131;
    List<List<String>> listList131 = new ArrayList<>();
    List<String> list131 = new ArrayList<>();
    int length131;

    public List<List<String>> partition131(String s) {
        length131 = s.length();
        dp131 = new boolean[length131][length131];
        for (int i = 0; i < length131; i++) {
            Arrays.fill(dp131[i], true);
        }

        for (int i = length131 - 1; i >= 0; i--) {
            for (int j = i + 1; j < length131; ++j) {
                dp131[i][j] = (s.charAt(i) == s.charAt(j)) && dp131[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return listList131;
    }

    public void dfs(String s, int i) {
        if (i == length131) {
            listList131.add(new ArrayList<>(list131));
            return;
        }
        for (int j = i; j < length131; ++j) {
            if (dp131[i][j]) {
                list131.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                list131.remove(list131.size() - 1);
            }
        }
    }

    @Override
    public String partition131(){
        System.out.println(JSON.toJSONString(partition131("aab")));
        return "success";
    }

    @Override
    public String minCut(){
        System.out.println(JSON.toJSONString(minCut("aab")));
        return "success";
    }

    public int minCut(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(dp[i], true);
        }
        for(int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        int[] dpArr = new int[length + 1];
        Arrays.fill(dpArr, length);
        dpArr[0] = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if(dp[i][j]){
                    dpArr[j + 1] = Math.min(dpArr[j + 1], dpArr[i] + 1);
                }else{
                    dpArr[j + 1] = Math.min(dpArr[j + 1], dpArr[i] + j - i + 1);
                }
            }
        }
        return dpArr[length] - 1;
    }

    @Override
    public String cloneGraph(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors = Lists.newArrayList(node2, node4);
        node2.neighbors = Lists.newArrayList(node1, node3);
        node3.neighbors = Lists.newArrayList(node2, node4);
        node4.neighbors = Lists.newArrayList(node1, node3);
        System.out.println(JSON.toJSONString(cloneGraph(node1)));
        return "success";
    }

    private Map<Node, Node> cloneGraphVisitedMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (cloneGraphVisitedMap.containsKey(node)) {
            return cloneGraphVisitedMap.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<>());

        cloneGraphVisitedMap.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    @Override
    public String canCompleteCircuit(){
        System.out.println(JSON.toJSONString(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2})));
        return "success";
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    @Override
    public String candy(){
        System.out.println(JSON.toJSONString(candy(new int[]{1,0,2})));
        return "success";
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    @Override
    public String copyRandomList(){
        Node node = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = node;
        node2.random = node4;
        node3.random = node2;
        node4.random = node2;

        System.out.println(JSON.toJSONString(copyRandomList(node)));
        return "success";
    }

    Map<Node, Node> copyRandomListMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!copyRandomListMap.containsKey(head)) {
            Node headNew = new Node(head.val);
            copyRandomListMap.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return copyRandomListMap.get(head);
    }

    @Override
    public String wordBreak(){
        System.out.println(JSON.toJSONString(wordBreak("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat"))));
        return "success";
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (String str : wordDict) {
                if(i - str.length() >= 0){
                    dp[i] = dp[i] || (dp[i - str.length()] && s.startsWith(str, i - str.length()));
                }
            }
        }
        return dp[s.length()];
    }

    @Override
    public String wordBreak140(){
        System.out.println(JSON.toJSONString(wordBreak140("catsandog", Lists.newArrayList("cats", "dog", "sand", "and", "cat"))));
        return "success";
    }

    List<String> wordBreakList = new ArrayList<>();
    List<String> wordBreakAnsList = new ArrayList<>();
    int wordBreakLength;

    public List<String> wordBreak140(String s, List<String> wordDict) {
        wordBreakLength = s.length();
        wordBreakDfs(s, 0, wordDict);
        return wordBreakAnsList;
    }

    private void wordBreakDfs (String s, int i, List<String> wordDict){
        if(i == wordBreakLength){
            wordBreakAnsList.add(String.join(" ", wordBreakList));
            return;
        }
        for (String str : wordDict) {
            int index = str.length() + i;
            if(index <= wordBreakLength && s.substring(i, index).equals(str)){
                wordBreakList.add(str);
                wordBreakDfs(s, index, wordDict);
                wordBreakList.remove(wordBreakList.size() - 1);
            }
        }
    }

    @Override
    public String hasCycle(){
        ListNode node1 = new ListNode(-4, null);
        ListNode node2 = new ListNode(0, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(3, node3);
        node1.next = node3;
        System.out.println(JSON.toJSONString(hasCycle(node4)));
        return "success";
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 空链表、单节点链表一定不会有环
        while (fast != null && fast.next != null) {
            // 快指针，一次移动两步
            fast = fast.next.next;
            // 慢指针，一次移动一步
            slow = slow.next;
            // 快慢指针相遇，表明有环
            if (fast == slow) {
                return true;
            }
        }
        // 正常走到链表末尾，表明没有环
        return false;
    }

    @Override
    public String detectCycle(){
        ListNode node1 = new ListNode(-4, null);
        ListNode node2 = new ListNode(0, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(3, node3);
        ListNode node5 = new ListNode(4, node4);
        node1.next = node3;
        System.out.println(JSON.toJSONString(detectCycle(node5)));
        return "success";
    }

    /**
     * 假设非环形部分为x, 环长度为y, 快慢指针在y的第b个元素重叠, 此时慢指针走过a1圈, 快指针走过a2圈
     * 故有以下等式
     * x + a1 * y + b = n
     * x + a2 * y + b = 2n
     *
     * 如果把等式1 * 2 - 等式2即获得以下结果
     * x + (2a1 - a2) * y + b = 0  =>  x + b = (a2 - 2a1) * y
     * 即b位置, 走过x个元素后, 为y的整数圈
     * 故在有环情况下, 从b出发和从开始出发 经过x个元素后到达y的第一个元素并产生交集
     */
    public ListNode detectCycle(ListNode head) {
        // 步骤一：使用快慢指针判断链表是否有环
        ListNode node1 = head, node2 = head;
        boolean hasCycle = false;
        while (node2 != null && node2.next != null && node2.next.next != null) {
            node1 = node1.next;
            node2 = node2.next.next;
            if (node1 == node2) {
                hasCycle = true;
                break;
            }
        }

        // 步骤二：若有环，找到入环开始的节点
        if (hasCycle) {
            ListNode listNode = head;
            while (node1 != listNode) {
                node1 = node1.next;
                listNode = listNode.next;
            }
            return listNode;
        } else{
            return null;
        }
    }

    @Override
    public String reorderList(){
        ListNode l4 = new ListNode(4, null);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);
        reorderList(l1);
        System.out.println(JSON.toJSONString(l1));
        return "success";
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        //通过快慢指针获取中间的元素
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    @Override
    public String preorderTraversal(){
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode.right = treeNode1;
        treeNode1.left = treeNode2;
        System.out.println(JSON.toJSONString(preorderTraversal(treeNode)));
        return "success";
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    @Override
    public String lruCache(){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
        System.out.println(JSON.toJSONString(maxProduct()));
        return "success";
    }

    class LRUCache {

        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return map.size() > capacity;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

//    class LRUCache1 {
//
//        // 双向链表（方便当前节点前一个节点指向后一个节点）
//        static class Node{
//            int key;
//            int val;
//            Node pre;
//            Node next;
//
//            public Node(){
//                this.key = -1;
//                this.val = -1;
//            }
//
//            public Node(int key, int val){
//                this.key = key;
//                this.val = val;
//            }
//        }
//
//        // 链表最大容量
//        int capacity;
//        // 链表当前容量
//        int size;
//
//        // 维护虚拟头尾指针方便头插和删尾
//        Node dummyHead;
//        Node dummyTail;
//
//        Map<Integer, Node> cache = new HashMap<>();
//
//        public LRUCache1(int capacity) {
//            this.capacity = capacity;
//            size = 0;
//            dummyHead = new Node();
//            dummyTail = new Node();
//            dummyHead.next = dummyTail;
//            dummyTail.pre = dummyHead;
//        }
//
//        public int get(int key) {
//            if(!cache.containsKey(key)){
//                return -1;
//            }
//
//            Node node = cache.get(key);
//            unLink(node);
//            addHead(node);
//            return node.val;
//        }
//
//        // 移除链表节点（未移除缓存）
//        public void unLink(Node node){
//            node.pre.next = node.next;
//            node.next.pre = node.pre;
//            size--;
//        }
//
//        // 添加节点到头部
//        public void addHead(Node node){
//            // 放置到链表头部
//            node.next = dummyHead.next;
//            dummyHead.next.pre = node;
//            dummyHead.next = node;
//            node.pre = dummyHead;
//            size++;
//            cache.put(node.key, node);
//        }
//
//        // 移除链表尾部节点
//        public void removeTail(){
//            Node tail = dummyTail.pre;
//            unLink(tail);
//            cache.remove(tail.key);
//        }
//
//        public void put(int key, int value) {
//            Node cur = new Node(key, value);
//            if(cache.containsKey(key)){
//                Node node = cache.get(key);
//                // 移除原本节点
//                unLink(node);
//                // 将新节点放到链表头部
//                addHead(cur);
//            }else{
//                if(size == capacity){
//                    // 添加头部并删除尾巴
//                    addHead(cur);
//                    removeTail();
//                }else{
//                    // 添加在头部
//                    addHead(cur);
//                }
//            }
//        }
//    }

    @Override
    public String insertionSortList(){
        ListNode head3 = new ListNode(3);
        ListNode head2 = new ListNode(1, head3);
        ListNode head1 = new ListNode(2, head2);
        ListNode head = new ListNode(4, head1);
        System.out.println(JSON.toJSONString(insertionSortList(head)));
        return "success";
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    @Override
    public String sortList(){
        ListNode head3 = new ListNode(3);
        ListNode head2 = new ListNode(1, head3);
        ListNode head1 = new ListNode(2, head2);
        ListNode head = new ListNode(4, head1);
        System.out.println(JSON.toJSONString(sortList(head)));
        return "success";
    }

    public ListNode sortList(ListNode head) {
        ListNode copy = head;
        List<Integer> list = new ArrayList<>();
        while(copy != null){
            list.add(copy.val);
            copy = copy.next;
        }
        list.sort(Comparator.naturalOrder());

        copy = head;
        for (int i = 0; i < list.size(); i++) {
            copy.val = list.get(i);
            copy = copy.next;
        }
        return head;
    }

    @Override
    public String maxPoints(){
        System.out.println(JSON.toJSONString(maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}})));
        return "success";
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int xDiff = points[i][0] - points[j][0];
                int yDiff = points[i][1] - points[j][1];
                if (xDiff == 0) {
                    yDiff = 1;
                } else if (yDiff == 0) {
                    xDiff = 1;
                } else {
                    if (yDiff < 0) {
                        xDiff = -xDiff;
                        yDiff = -yDiff;
                    }
                    int gcdXY = maxPointsGcd(Math.abs(xDiff), Math.abs(yDiff));
                    xDiff /= gcdXY;
                    yDiff /= gcdXY;
                }
                int key = yDiff + xDiff * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int maxPointsGcd(int a, int b) {
        return b != 0 ? maxPointsGcd(b, a % b) : a;
    }

    @Override
    public String evalRPN(){
        System.out.println(JSON.toJSONString(evalRPN(new String[]{"2","1","+","3","*"})));
        return "success";
    }

    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

    @Override
    public String maxProduct(){
        System.out.println(JSON.toJSONString(maxProduct(new int[]{2,3,-2,4})));
        return "success";
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }

    @Override
    public String findPeakElement(){
        System.out.println(JSON.toJSONString(findPeakElement(new int[]{1,2,3,1})));
        return "success";
    }

    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }

    @Override
    public String compareVersion(){
        System.out.println(JSON.toJSONString(compareVersion("1.01", "1.001")));
        return "success";
    }

    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 0;
        }
        // 注意此处为正则匹配，不能用.
        String[] versionArr1 = version1.split("\\.");
        int length1 = versionArr1.length;
        String[] versionArr2 = version2.split("\\.");
        int length2 = versionArr2.length;
        int max = Math.max(length1, length2);
        for (int i = 0; i < max; i++) {
            int val1 = length1 > i ? Integer.parseInt(versionArr1[i]) : 0;
            int val2 = length2 > i ? Integer.parseInt(versionArr2[i]) : 0;
            if(val1 > val2){
                return 1;
            }else if(val1 < val2){
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String fractionToDecimal(){
        System.out.println(JSON.toJSONString(fractionToDecimal(333, 4)));
        return "success";
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorLong = numerator;
        long denominatorLong = denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuilder sb = new StringBuilder();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuilder fractionPart = new StringBuilder();
        Map<Long, Integer> remainderIndexMap = new HashMap<>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        // 有循环节
        if (remainder != 0) {
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart);

        return sb.toString();
    }

    @Override
    public String twoSum167(){
        System.out.println(JSON.toJSONString(twoSum167(new int[]{2,3,4}, 6)));
        return "success";
    }

    public int[] twoSum167(int[] numbers, int target) {
        int n = numbers.length, left = 0, right = n - 1;
        while(left < right){
            if(numbers[left] + numbers[right] == target){
                break;
            }
            if(numbers[left] + numbers[right] > target){
                right--;
            }else{
                left++;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    @Override
    public String convertToTitle(){
        System.out.println(JSON.toJSONString(convertToTitle(2147483647)));
        return "success";
    }

    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            int a0 = (columnNumber - 1) % 26 + 1;
            sb.append((char)(a0 - 1 + 'A'));
            columnNumber = (columnNumber - a0) / 26;
        }
        return sb.reverse().toString();
    }

    @Override
    public String titleToNumber(){
        System.out.println(JSON.toJSONString(titleToNumber  ("ZY")));
        return "success";
    }

    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int val = 0;
        for(int i = 0; i < n; i++){
            val = val * 26 + columnTitle.charAt(i) - 'A' + 1;
        }
        return val;
    }

    @Override
    public String trailingZeroes(){
        System.out.println(JSON.toJSONString(trailingZeroes(5)));
        return "success";
    }

    public int trailingZeroes (int n) {
        int ans = 0;
        while (n != 0) {
            n = n / 5;
            ans = ans + n;
        }
        return ans;
    }

    @Override
    public String bstIterator(){
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15, new TreeNode(9), new TreeNode(20));
        BSTIterator bSTIterator = new BSTIterator(root);
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        return "success";
    }

    class BSTIterator {
        private int idx;
        private final List<Integer> arr;

        public BSTIterator(TreeNode root) {
            idx = 0;
            arr = new ArrayList<>();
            inorderTraversal(root);
        }

        public int next() {
            return arr.get(idx++);
        }

        public boolean hasNext() {
            return idx < arr.size();
        }

        private void inorderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left);
            arr.add(root.val);
            inorderTraversal(root.right);
        }
    }

    @Override
    public String calculateMinimumHP(){
        System.out.println(JSON.toJSONString(calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}})));
        return "success";
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    @Override
    public String largestNumber(){
        System.out.println(JSON.toJSONString(largestNumber(new int[]{3,30,34,5,9})));
        return "success";
    }

    public String largestNumber(int[] nums) {
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long xl = x;
            long yl = y;
            int digitX = countDigits(x);
            int digitY = countDigits(y);

            long cmp1 = xl * (long) Math.pow(10, digitY) + yl;
            long cmp2 = yl * (long) Math.pow(10, digitX) + xl;

            return Long.compare(cmp2, cmp1);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    private int countDigits(int num) {
        int count = 0;
        if (num == 0) {
            return 1;
        }
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    @Override
    public String findRepeatedDnaSequences(){
        System.out.println(JSON.toJSONString(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")));
        return "success";
    }

    public List<String> findRepeatedDnaSequences(String s) {
        int length = 10;
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i <= n - length; i++) {
            String sub = s.substring(i, i + length);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (map.get(sub) == 2) {
                list.add(sub);
            }
        }
        return list;
    }

    @Override
    public String maxProfit4(){
        System.out.println(JSON.toJSONString(maxProfit4(2, new int[]{3,2,6,5,0,3})));
        return "success";
    }

    public int maxProfit4(int k, int[] prices) {
        //buy[i]表示买入i次股票后的利润
        int[] buy = new int[k + 1];
        //sell[i]表示完成i笔交易后的利润
        int[] sell = new int[k + 1];
        Arrays.fill(buy, -prices[0]);
        for(int i = 1; i < prices.length; i++){
            //最多支持k次交易
            for(int j = 1; j <= k; j++){
                //买入j次后的最大利润为:Math.max(当前利润, 上一笔交易的利润 - 当天的股票价格)
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                //完成j次交易后的最大利润为：Math.max(当前利润, 当前利润 + 本次交易后所得的利润)
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }

    @Override
    public String rotate189(){
        int[] arr = new int[]{-1,-100,3,99};
        rotate189(arr, 2);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void rotate189(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] arr = new int[n << 1];
        System.arraycopy(nums, 0, arr, 0, n);
        System.arraycopy(nums, 0, arr, n, n);
        System.arraycopy(arr, n - k, nums, 0, n);
    }

    @Override
    public String reverseBits(){
        System.out.println(JSON.toJSONString(reverseBits(43261596)));
        return "success";
    }

    public int reverseBits(int n) {
//        return Integer.reverse(n);

        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev = rev | (n & 1) << (31 - i);
            n = n >>> 1;
        }
        return rev;
    }

    @Override
    public String rob(){
        System.out.println(JSON.toJSONString(rob(new int[]{3, 1, 3, 1, 1, 9 })));
        return "success";
    }

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = 0; i < n; i++) {
            dp[i + 2] = Math.max(dp[i] + nums[i], dp[i + 1]);
        }
        return dp[n + 1];
    }

    @Override
    public String rightSideView(){
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(2, null, treeNode1);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(3, null, treeNode3);
        TreeNode root = new TreeNode(1, treeNode2, treeNode4);
        System.out.println(JSON.toJSONString(rightSideView(root)));
        return "success";
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideView(root, list, 0);
        return list;
    }

    public void rightSideView(TreeNode root, List<Integer> list, int i) {
        if(root == null){
            return;
        }

        if(list.size() <= i){
            list.add(root.val);
        }else{
            list.set(i, root.val);
        }

        rightSideView(root.left, list, i + 1);

        rightSideView(root.right, list, i + 1);
    }

    @Override
    public String numIslands(){
        System.out.println(JSON.toJSONString(numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}})));
        return "success";
    }

    public int numIslands(char[][] grid) {
        int islandNum = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    infect(grid, i, j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    public void infect(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }

    @Override
    public String rangeBitwiseAnd(){
        System.out.println(JSON.toJSONString(rangeBitwiseAnd(5, 7)));
        return "success";
    }

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    @Override
    public String isHappy(){
        System.out.println(JSON.toJSONString(isHappy(19)));
        return "success";
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            sum += d * d;
        }
        return sum;
    }

    @Override
    public String removeElements(){
        ListNode l7 = new ListNode(6, null);
        ListNode l6 = new ListNode(5, l7);
        ListNode l5 = new ListNode(4, l6);
        ListNode l4 = new ListNode(3, l5);
        ListNode l3 = new ListNode(6, l4);
        ListNode l2 = new ListNode(3, l3);
        ListNode l1 = new ListNode(1, l2);
        System.out.println(JSON.toJSONString(removeElements(l1, 6)));
        return "success";
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode();
        node.next = head;
        ListNode copyNode = node;
        while(copyNode.next != null){
            if(copyNode.next.val == val){
                copyNode.next = copyNode.next.next;
            }else{
                copyNode = copyNode.next;
            }
        }
        return node.next;
    }

    @Override
    public String countPrimes(){
        System.out.println(JSON.toJSONString(countPrimes(10)));
        return "success";
    }

    public int countPrimes(int n) {
        if(n < 3){
            return 0;
        }
        int count = 0;
        boolean[] arr = new boolean[n];
        for (int i = 2; i < n; i++) {
            if(arr[i]){
                continue;
            }
            count++;
            for (int j = 2; j * i < n; j++) {
                arr[j * i] = true;
            }
        }
        return count;
    }

    @Override
    public String isIsomorphic(){
        System.out.println(JSON.toJSONString(isIsomorphic("egg", "add")));
        return "success";
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((sMap.containsKey(x) && sMap.get(x) != y) || (tMap.containsKey(y) && tMap.get(y) != x)) {
                return false;
            }
            sMap.put(x, y);
            tMap.put(y, x);
        }
        return true;
    }

    @Override
    public String canFinish(){
        System.out.println(JSON.toJSONString(canFinish(5, new int[][]{{1,0},{1,2},{0,1}})));
        return "success";
    }

    private List<List<Integer>> canFinishList = new ArrayList<>();
    private int canFinishVisit[];
    private boolean canFinishIsValid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        canFinishVisit = new int[numCourses];
        for (int i = 0; i < numCourses; i++){
            canFinishList.add(new ArrayList<>());
        }
        for (int arr[] : prerequisites){
            //学arr[0]之前要先学arr[1],所以arr[1]指向arr[0],
            //所以在arr[1]的ArrayList中存储它指向哪个科目
            canFinishList.get(arr[1]).add(arr[0]);
        }
        for (int i = 0; i < numCourses; i++){
            if (canFinishVisit[i] == 0){
                //如果是未搜索的科目，则深搜
                canFinishDfs(i);
            }
        }
        return canFinishIsValid;
    }

    private void canFinishDfs(int v){
        //标记该科目为搜索中
        canFinishVisit[v] = 1;
        for (int w : canFinishList.get(v)){
            //遍历该科目指向的学科
            if (canFinishVisit[w] == 0){
                //如果指向的某一学科未搜索过，则深搜
                canFinishDfs(w);
                if (!canFinishIsValid){
                    return;
                }
            } else if (canFinishVisit[w] == 1){
                //如果指向的某一学科在搜索中，则有环，标记isValid
                canFinishIsValid = false;
                return;
            }
        }
        //因为该科目已经完成深搜，所以标记它的状态为搜索过
        canFinishVisit[v] = 2;
    }

    @Override
    public String trie(){
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(JSON.toJSONString(trie.search("apple")));
        System.out.println(JSON.toJSONString(trie.search("app")));
        System.out.println(JSON.toJSONString(trie.startsWith("app")));
        trie.insert("app");
        System.out.println(JSON.toJSONString(trie.search("app")));
        return "success";
    }

    class Trie {

        private class TrieNode {
            // 每个节点最多有26个不同的小写字母
            private boolean isEnd;
            private TrieNode[] next;

            public TrieNode() {
                isEnd = false;
                next = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0, len = word.length(), ch; i < len; i++) {
                ch = word.charAt(i) - 'a';
                if (cur.next[ch] == null){
                    cur.next[ch] = new TrieNode();
                }
                cur = cur.next[ch];
            }
            // 加上一个标记，表示为一个单词
            cur.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0, len = word.length(), ch; i < len; i++) {
                ch = word.charAt(i) - 'a';
                if (cur.next[ch] == null){
                    return false;
                }
                cur = cur.next[ch];
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0, len = prefix.length(), ch; i < len; i++) {
                ch = prefix.charAt(i) - 'a';
                if (cur.next[ch] == null){
                    // 若还没遍历完给定的前缀子串，则直接返回false
                    return false;
                }
                cur = cur.next[ch];
            }
            return true;
        }
    }

    @Override
    public String minSubArrayLen(){
        System.out.println(JSON.toJSONString(minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1})));
        return "success";
    }

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum = sum - nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    @Override
    public String findOrder(){
        System.out.println(JSON.toJSONString(findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
        return "success";
    }

    private List<List<Integer>> findOrderEdges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    private int[] findOrderVisited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    private int[] findOrderResult;
    // 判断有向图中是否有环
    private boolean findOrderValid = true;
    // 栈下标
    private int findOrderIndex;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        findOrderEdges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            findOrderEdges.add(new ArrayList<>());
        }
        findOrderVisited = new int[numCourses];
        findOrderResult = new int[numCourses];
        findOrderIndex = numCourses - 1;
        for (int[] info : prerequisites) {
            findOrderEdges.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && findOrderValid; ++i) {
            if (findOrderVisited[i] == 0) {
                dfs(i);
            }
        }
        if (!findOrderValid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return findOrderResult;
    }

    private void dfs(int u) {
        // 将节点标记为「搜索中」
        findOrderVisited[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v: findOrderEdges.get(u)) {
            // 如果「未搜索」那么搜索相邻节点
            if (findOrderVisited[v] == 0) {
                dfs(v);
                if (!findOrderValid) {
                    return;
                }
            } else if (findOrderVisited[v] == 1) {
                // 如果「搜索中」说明找到了环
                findOrderValid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        findOrderVisited[u] = 2;
        // 将节点入栈
        findOrderResult[findOrderIndex--] = u;
    }

    @Override
    public String wordDictionary(){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(JSON.toJSONString(wordDictionary.search("pad")));
        System.out.println(JSON.toJSONString(wordDictionary.search("bad")));
        System.out.println(JSON.toJSONString(wordDictionary.search(".ad")));
        System.out.println(JSON.toJSONString(wordDictionary.search("b..")));
        return "success";
    }

    static class WordDictionary {
        private final WordDictionary[] items;
        boolean isEnd;
        public WordDictionary() {
            items = new WordDictionary[26];
        }

        public void addWord(String word) {
            WordDictionary curr = this;
            int n = word.length();
            for(int i = 0; i < n; i++){
                int index = word.charAt(i) - 'a';
                if(curr.items[index] == null)
                    curr.items[index] = new WordDictionary();
                curr = curr.items[index];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            return search(this, word, 0);
        }

        private boolean search(WordDictionary curr, String word, int start){
            int n = word.length();
            if(start == n)
                return curr.isEnd;
            char c = word.charAt(start);
            if(c != '.'){
                WordDictionary item = curr.items[c - 'a'];
                return item != null && search(item, word, start + 1);
            }

            for(int j = 0; j < 26; j++){
                if(curr.items[j] != null && search(curr.items[j], word, start + 1))
                    return true;
            }
            return false;
        }
    }

    @Override
    public String findWords(){
        System.out.println(JSON.toJSONString(findWords(new char[][]{{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}}, new String[]{"oa","oaa"})));
        return "success";
    }

    static int[][] directs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        //深搜board所有长度为3的子串，构成词典，把包含非法子串的word排除，压缩字典树。
        findWordsThree findWordsThree = new findWordsThree(board);
        findWordsTrie findWordsTrie = new findWordsTrie();
        for (String word : words) {
            if (findWordsThree.check(word)) {
                findWordsTrie.insert(word);
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, findWordsTrie, i, j, ans);
            }
        }
        return ans;
    }

    private void dfs(char[][] board, findWordsTrie now, int i, int j, List<String> ans) {
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
            return;
        }
        if (!now.children.containsKey(board[i][j])) {
            return;
        }

        char ch = board[i][j];
        findWordsTrie nxt = now.children.get(ch);
        if (nxt.word.length() > 0) {
            ans.add(nxt.word);
            nxt.word = "";
        }

        if (!nxt.children.isEmpty()) {
            board[i][j] = '#';
            for (int[] direct : directs) {
                dfs(board, nxt, i + direct[0], j + direct[1], ans);
            }
            board[i][j] = ch;
        }

        if (nxt.children.isEmpty()) {
            now.children.remove(ch);
        }
    }

    class findWordsTrie {
        String word;
        Map<Character, findWordsTrie> children;

        public findWordsTrie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        public void insert(String word) {
            findWordsTrie cur = this;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new findWordsTrie());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;
        }
    }

    class findWordsThree {
        int[][] directs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<String> dict;

        public findWordsThree(char[][] board) {
            dict = new HashSet<>();
            char[] choose = new char[3];//长度定为3，时空开销适中。
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, 0, choose);
                }
            }
        }

        void dfs(char[][] board, int i, int j, int now, char[] choose) {
            if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) {
                return;
            }
            if (board[i][j] == '#') {
                return;
            }
            char ch = board[i][j];
            choose[now] = ch;
            if (now == choose.length - 1) {
                dict.add(String.valueOf(choose));
            } else {
                board[i][j] = '#';
                for (int[] direct : directs) {
                    dfs(board, i + direct[0], j + direct[1], now + 1, choose);
                }
                board[i][j] = ch;
            }
        }

        //随便选几段检查即可，降低开销。
        int[] checkPoint = {10, 3, 8, 5};

        public boolean check(String word) {
            int n = word.length();
            for (int p : checkPoint) {
                if (n >= p && !dict.contains(word.substring(p - 3, p))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public String rob2(){
        System.out.println(JSON.toJSONString(rob2(new int[]{1,2,3,1 })));
        return "success";
    }

    public int rob2(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }

    @Override
    public String shortestPalindrome(){
        System.out.println(shortestPalindrome("aacecaaa"));
        return "success";
    }

    public String shortestPalindrome(String s) {
        int n = s.length();
        int[] fail = new int[n];
        //当前元素在整个字符串中第一次出现的索引值
        Arrays.fill(fail, -1);
        //j和自己值相同的索引, 认为i和j对称
        for (int i = 1; i < n; i++) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                //如果找到了和第一个元素相同值的索引, 现在判断下一个元素值是否相同
                //如果不同就设置为-1,后续元素重新对比是否和第一个元素是否相同
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                //寻找和第一个元素相同值的索引
                fail[i] = j + 1;
            }
        }
        //缺少的字符串后缀的index
        int best = -1;
        for (int i = n - 1; i >= 0; i--) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                //当前已经跳过了x个元素, 认为已经进行轴对称对比
                //但左轴的值 不等于 右侧的值, 所以要进行重置
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                //当前元素的值 == 第一个元素的值
                best++;
            }
        }
        String add = best == n - 1 ? "" : s.substring(best + 1);
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }

    @Override
    public String combinationSum3(){
        System.out.println(combinationSum3(3, 9));
        return "success";
    }

    List<List<Integer>> combinationSum3List = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        if(n > 45){
            return new ArrayList<>();
        }
        combinationSum3(new ArrayList<>(), k, n);
        return combinationSum3List;
    }

    private void combinationSum3(List<Integer> list, int k, int remaining) {
        if (k <= 0 || remaining <= 0){
            if(k == 0 && remaining == 0){
                combinationSum3List.add(new ArrayList<>(list));
            }
            return;
        }
        int i = list.isEmpty() ? Math.min(remaining, 9) : list.get(list.size() - 1) - 1;
        for (; i >= 1; i--) {
            list.add(i);
            combinationSum3(list, k - 1, remaining - i);
            list.remove(list.size() - 1);
        }
    }

    @Override
    public String containsDuplicate(){
        int[] nums = new int[]{1,2,3,1};
        System.out.println(containsDuplicate(nums));
        return "success";
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSkyline(){
        System.out.println(JSON.toJSONString(getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}})));
        return "success";
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 如果将所有的建筑的边界作为一条线，那么所有的答案都在这些线上
        // 考虑任意一条线，那么这条线和所有相交的建筑（这里排除掉刚好和建筑右边界相交），取一个最高的高度，
        // 然后判断这个高度是否和ans末尾最后一个元素的高度相等，不相等就加入进去
        List<Integer> boundarieList = new ArrayList<>();
        for (int[] building : buildings) {
            boundarieList.add(building[0]);
            boundarieList.add(building[1]);
        }
        Collections.sort(boundarieList);

        // 创建一个堆，维护一个边界-高度值对
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        List<List<Integer>> resList = new ArrayList<>();
        int n = buildings.length, index = 0;
        for (int boundary : boundarieList) {
            // 对于一个建筑，如果其左边界在当前判断的边界线左边或重叠，那么向堆加入右边界-高度值对
            while (index < n && buildings[index][0] <= boundary) {
                pq.offer(new int[]{buildings[index][1], buildings[index][2]});
                index++;
            }
            // 对于那些加入了堆中的建筑，从堆的顶部移出建筑右边界在边界线左边或重叠的边界-高度值对
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }
            // 经过上面的两步操作之后，当前边界线穿过的建筑（不含右边界）全都在堆中，并且堆的顶端是所有穿过的建筑中，高度最高的，也就是天际线高度
            // 如果此时的堆为空，证明边界线没有穿过任何建筑，来到了建筑的分割位置，天际线为0
            int max = pq.isEmpty() ? 0 : pq.peek()[1];
            // 按照这种算法，每一条边界线都会产生一个天际线高度，如果这个高度和ans末尾元素的高度一致，那么就说明两条边界线穿过了同一个建筑，并且相邻，那么按照规则只取最左端
            if (resList.isEmpty() || max != resList.get(resList.size() - 1).get(1)) {
                resList.add(Arrays.asList(boundary, max));
            }
        }
        return resList;
    }

    @Override
    public String containsNearbyDuplicate(){
        System.out.println(JSON.toJSONString(containsNearbyDuplicate(new int[]{1,2,3,1}, 3)));
        return "success";
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(i > k){
                set.remove(nums[i - k - 1]);
            }
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public String containsNearbyAlmostDuplicate(){
        System.out.println(JSON.toJSONString(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3)));
        return "success";
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        //key: 根据valueDiff差距 放置到不同的桶
        //value: 数组中的值
        Map<Long, Long> map = new HashMap<>();
        long value = (long) valueDiff + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], value);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < value) {
                //可能在上个桶里有满足的
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < value) {
                //可能在下个桶里有满足的
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= indexDiff) {
                //移除掉非indexDiff区间中的数
                map.remove(getID(nums[i - indexDiff], value));
            }
        }
        return false;
    }

    public long getID(long x, long value) {
        return x >= 0 ? x / value : (x + 1) / value - 1;
    }

    @Override
    public String maximalSquare(){
        System.out.println(JSON.toJSONString(maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}})));
        return "success";
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length, maxSide = 0;
        int[][] dp = new int[rows + 1][columns + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
            }
        }
        return maxSide * maxSide;
    }

    @Override
    public String countNodes(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(6);
        System.out.println(JSON.toJSONString(countNodes(treeNode)));
        return "success";
    }

    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    @Override
    public String computeArea(){
        System.out.println(JSON.toJSONString(computeArea(-3,  0,  3,  4,  0,  -1,  9,  2)));
        return "success";
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        //第一个矩形面积
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        //第二个矩形面积
        int area2 = (bx2 - bx1) * (by2 - by1);

        //覆盖区域
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        //这里取math.max为了兼容完全没有覆盖的场景
        int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
        return area1 + area2 - overlapArea;
    }

    @Override
    public String calculate(){
        System.out.println(JSON.toJSONString(calculate("5-(1+5)")));
        return "success";
    }

    public int calculate(String s) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        int sign = 1, result = 0, n = s.length(), i = 0;
        while (i < n) {
            char c = s.charAt(i);
            switch (c){
                case ' ':
                    i++;
                    break;
                case '+':
                    sign = deque.peek();
                    i++;
                    break;
                case '-':
                    sign = - deque.peek();
                    i++;
                    break;
                case '(':
                    deque.push(sign);
                    i++;
                    break;
                case ')':
                    deque.pop();
                    i++;
                    break;
                default:
                    long num = 0;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                    }
                    result += sign * num;
            }
        }
        return result;
    }


    @Override
    public String myStack(){
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println("top: " +  myStack.top());
        System.out.println("pop: " +  myStack.pop());
        System.out.println("empty: " +  myStack.empty());
        return "success";
    }

    class MyStack {

        //输入队列
        private Queue<Integer> queue1;

        //输出队列
        private Queue<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue1.offer(x);
            // 将queue2队列中元素全部转给queue1队列
            while(!queue2.isEmpty()){
                queue1.offer(queue2.poll());
            }
            // 交换queue1和queue2,使得queue1队列没有在push()的时候始终为空队列
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            return queue2.poll();
        }

        public int top() {
            return queue2.peek();
        }

        public boolean empty() {
            return queue2.isEmpty();
        }
    }

    @Override
    public String invertTree(){
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right = new TreeNode(7);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(9);
        System.out.println(JSON.toJSONString(invertTree(treeNode)));
        return "success";
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    @Override
    public String calculate227(){
        System.out.println(JSON.toJSONString(calculate227("3+2*2")));
        return "success";
    }

    public int calculate227(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int n = s.length(), num = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = c;
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    @Override
    public String kthSmallest(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(4);
        treeNode.right = new TreeNode(6);
        System.out.println(JSON.toJSONString(kthSmallest(treeNode, 3)));
        return "success";
    }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

    @Override
    public String isPowerOfTwo(){
        System.out.println(JSON.toJSONString(isPowerOfTwo(56)));
        return "success";
    }

    public boolean isPowerOfTwo(int n) {
        if (n < 1){
            return false;
        }
        return (n & n-1) == 0;
    }

    @Override
    public String myQueue(){
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println("peek: " +  myQueue.peek());
        System.out.println("pop: " +  myQueue.pop());
        System.out.println("empty: " +  myQueue.empty());
        return "success";
    }

    class MyQueue {

        //输入栈
        private Stack<Integer> stack1;

        //输出栈
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public int peek() {
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack2.isEmpty() && stack1.isEmpty();
        }
    }

    @Override
    public String lowestCommonAncestor(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(0);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.right.left = new TreeNode(3);
        treeNode.left.right.right = new TreeNode(5);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(7);
        treeNode.right.right = new TreeNode(9);
        System.out.println(JSON.toJSONString(lowestCommonAncestor(treeNode, treeNode.left, treeNode.right)));
        return "success";
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }

    @Override
    public String lowestCommonAncestor2(){
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5);
        treeNode.left.left = new TreeNode(6);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(7);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right = new TreeNode(1);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(8);
        System.out.println(JSON.toJSONString(lowestCommonAncestor2(treeNode, treeNode.left, treeNode.right.right)));
        return "success";
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    @Override
    public String productExceptSelf(){
        System.out.println(JSON.toJSONString(productExceptSelf(new int[]{-1,1,0,-3,3})));
        return "success";
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftDp = new int[n + 1];
        int[] rightDp = new int[n + 1];
        leftDp[0] = 1;
        for (int i = 0; i < n; i++) {
            leftDp[i + 1] = nums[i] * leftDp[i];
        }
        rightDp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            rightDp[i] = nums[i] * rightDp[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = leftDp[i] * rightDp[i + 1];
        }
        return nums;
    }

    @Override
    public String maxSlidingWindow(){
        System.out.println(JSON.toJSONString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        return "success";
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 采用单向队列的方式，让队列中始终保持top处的值是最大的
        Deque<Integer> deque = new ArrayDeque<>();

        int[] res = new int[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.offer(nums[i]);
        }
        //把第一个窗口中的最大值存进去，下标递增
        res[index++] = deque.peek();


        // 接下来开始循环后面的数：策略就是进一个出一个
        for (int i = 0; i < nums.length - k; i++) {
            // 先弹出数据，如果要删的数，不是头部的数据，那就不执行。首先要保证我的头部是存在的，也就是队列不为空
            if (!deque.isEmpty() && deque.peek() == nums[i]) {
                deque.pop();
            }

            // 接下来加入数据，加入的数一个一个的和队列里面的数比大小，小的数弹出去，从后往头部比较
            while(!deque.isEmpty() && nums[i + k] > deque.peekLast()) {
                deque.removeLast();
            }

            // 把队列中比要添加的数都要小的数都去掉之后，将新的数添加进来
            deque.offer(nums[i + k]);

            // 存了一个数，也弹出了一个数，等于移动了一个窗长，这个时候，可以取窗长下的最大值，也就是头部的值
            res[index++] = deque.peek();
        }
        return res;
    }

    @Override
    public String diffWaysToCompute(){
        System.out.println(JSON.toJSONString(diffWaysToCompute("2*3-4*5")));
        return "success";
    }


    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> list = new ArrayList<>();
        int len = expression.length();
        int start;
        for (start = 0; start < len; start++) {
            //找到一个运算符
            if(!Character.isDigit(expression.charAt(start))) {
                break;
            }
        }
        //没找到等于字符串里没有运算符，那直接返回对应数字结果
        if(start == len) {
            list.add(Integer.parseInt(expression));
        }

        for (int i = start; i < len; i++) {
            if(Character.isDigit(expression.charAt(i))){
                continue;
            }
            char op = expression.charAt(i);
            //用该运算符分隔成 左边的字符串 和 右边的字符串
            //左边的字符串再递归继续求得 数字结果集
            List<Integer> left = diffWaysToCompute(expression.substring(0,i));
            //右边的字符串再递归继续求得 数字结果集
            List<Integer> right = diffWaysToCompute(expression.substring(i+1,len));

            //从左右两个求得数字结果集里拿出数字，基于当前运算符运算完 ，添加进最终list，得到最终数字结果集
            for (int j : left){
                for (int k : right){
                    if(op=='+'){
                        list.add(j+k);
                    } else if(op == '-') {
                        list.add(j-k);
                    } else if(op == '*') {
                        list.add(j*k);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public String isAnagram(){
        System.out.println(JSON.toJSONString(isAnagram("rat", "car")));
        return "success";
    }

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] sArr = new int[26];
        for(int i = 0; i < s.length(); i++){
            sArr[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++){
            sArr[t.charAt(i) - 'a']--;
        }
        for(int arr : sArr){
            if(arr != 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public String hIndex(){
        System.out.println(JSON.toJSONString(hIndex(new int[]{3,0,6,1,5})));
        return "success";
    }

    public int hIndex(int[] citations) {
        int n = citations.length, total = 0;
        int[] counter = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n) {
                counter[n]++;
            } else {
                counter[citation]++;
            }
        }
        for (int i = n; i >= 0; i--) {
            total += counter[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public String numSquares(){
        System.out.println(JSON.toJSONString(numSquares(12)));
        return "success";
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    @Override
    public String moveZeroes(){
        int[] arr = new int[]{0,1,0,3,12};
        moveZeroes(arr);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void moveZeroes(int[] nums) {
        int size = nums.length, startIndex = 0;
        for (int i = 0; i < size; i++) {
            if(nums[i] > 0){
                int val = nums[i];
                nums[i] = nums[startIndex];
                nums[startIndex++] = val;
            }
        }
    }

    @Override
    public String gameOfLife(){
        int[][] arr = new int[][]{{0,0,0,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,0,0,0}};
        gameOfLife(arr);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //计算周围的活细胞数量
                int count = gameOfLifeCount(board, m, n, i, j);
                if(board[i][j] == 1 && (count != 2 && count != 3)){
                    board[i][j] = 2;
                    continue;
                }
                if(board[i][j] == 0 && count == 3){
                    board[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 2){
                    board[i][j] = 0;
                }else if(board[i][j] == -1){
                    board[i][j] = 1;
                }
            }
        }
    }

    public int gameOfLifeCount(int[][] board, int m, int n, int i, int j) {
        int count = 0;
        if(i > 0){
            if(j > 0 && board[i - 1][j - 1] > 0){
                count++;
            }
            if(board[i - 1][j] > 0){
                count++;
            }
            if(j + 1 < n && board[i - 1][j + 1] > 0){
                count++;
            }
        }
        if(j > 0 && board[i][j - 1] > 0){
            count++;
        }
        if(j + 1 < n && board[i][j + 1] > 0){
            count++;
        }
        if(i + 1 < m){
            if(j > 0 && board[i + 1][j - 1] > 0){
                count++;
            }
            if(board[i + 1][j] > 0){
                count++;
            }
            if(j + 1 < n && board[i + 1][j + 1] > 0){
                count++;
            }
        }
        return count;
    }

    @Override
    public String lengthOfLIS(){
        System.out.println(JSON.toJSONString(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})));
        return "success";
    }

    /**
     * dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
     * 由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度.
     * 对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
     * 1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp数组尾部, 并将最长递增序列长度maxL加1
     * 2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
     */
    public int lengthOfLIS(int[] nums) {
        int maxL = 0;
        int[] dp = new int[nums.length];
        for(int num : nums) {
            // 二分法查找, 也可以调用库函数如binary_search
            int low = 0, hight = maxL;
            while(low < hight) {
                int mid = low + (hight - low) / 2;
                if(dp[mid] < num) {
                    low = mid + 1;
                }else {
                    hight = mid;
                }
            }
            dp[low] = num;
            if(low == maxL) {
                maxL++;
            }
        }
        return maxL;
    }

    @Override
    public String maxProfit5(){
        System.out.println(JSON.toJSONString(maxProfit5(new int[]{1,7,2,4})));
        return "success";
    }

    public int maxProfit5(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        //状态转移图：
        //       持股               不持股
        //     ↙-----、   卖出    ↙-----、
        //    持股-----↑--------→不持股---↑
        //      |
        //      |卖
        //      |出
        //      ↓
        //    冷冻期(期间什么都不能干，冷冻期结束后转为不持股状态)
        //
        int[][] dp = new int[prices.length][3];
        //dp[i][x]第i天进入(处于)x状态（0.不持股，1.持股，2.冷冻期）
        //不持股
        dp[0][0] = 0;
        //持股
        dp[0][1] = -prices[0];
        //冷冻期
        dp[0][2] = 0;
        for(int i = 1; i < prices.length; i++){
            //第i天不持股可以从两种状态转移而来，
            //1.第i-1天不持股，今天仍不买股票，保持不持股状态。
            //2.冷冻期结束了，但是今天不买股票。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);

            //第i天持股可从两种状态转移而来
            //1.第i-1天不持股(包含昨天是冷冻期，冷冻期结束后转为不持股状态和昨天本身就不持股这两种情况)，今天买股票
            //2.第i-1天持股，今天不卖出，保持持股状态。
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);

            //只有第i - 1天卖出了股票，第i天才处于冷冻期。
            dp[i][2] = dp[i - 1][1] + prices[i];
        }

        //只有最后一天不持股（不持股状态）或者前一天已经卖掉了（今天为冷冻期）这两种情况手里是拿着钱的，最大值在二者中产生。
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }

    @Override
    public String maxCoins(){
        System.out.println(JSON.toJSONString(maxCoins(new int[]{3,1,5,8,6})));
        return "success";
    }

    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        for(int i = 1; i < arr.length - 1; i++){
            arr[i] = nums[i - 1];
        }
        int[][] map = new int[arr.length][arr.length];
        for(int i = 1; i < map.length - 1; i++){
            map[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        for(int start = map.length - 2; start > 0; start--){
            for(int end = start + 1; end < map.length - 1; end++){
                int ans = 0;
                int p;
                for(int i = start; i <= end; i++){
                    //最后打爆i位置，则打爆i位置加上start到i - 1位置的得分 加上i + 1到end的得分就是得分
                    p = arr[start - 1] * arr[i] * arr[end + 1] + map[start][i - 1] + map[i + 1][end];
                    ans = Math.max(ans, p);
                }
                map[start][end] = ans;
            }
        }
        return map[1][map.length - 2];
    }

    @Override
    public String nthSuperUglyNumber(){
        System.out.println(JSON.toJSONString(nthSuperUglyNumber(12, new int[]{2,7,13,19})));
        return "success";
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] num = new int[m];
        Arrays.fill(num, 0);

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;

        int[] val = new int[m];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                val[j] = dp[num[j]] * primes[j];
                if (val[j] >= 0) {
                    //处理溢出的问题
                    dp[i] = Math.min(dp[i], val[j]);
                }
            }
            for (int j = 0; j < m; j++) {
                if (val[j] == dp[i]) {
                    num[j]++;
                }
            }
        }
        return dp[n - 1];
    }

    @Override
    public String countSmaller(){
        System.out.println(JSON.toJSONString(countSmaller(new int[]{5,2,6,1})));
        return "success";
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return result;
        }

        int[] temp = new int[len];
        int[] res = new int[len];

        // 索引数组,归并回去的时候,方便知道是哪个下标的元素
        int[] indexes = new int[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        //针对数组 nums 指定的区间 [left, right] 进行归并排序，在排序的过程中完成统计任务
        mergeAndCountSmaller(nums, 0, len - 1, indexes, temp, res);

        // 把 int[] 转换成为 List<Integer>
        for (int i = 0; i < len; i++) {
            result.add(res[i]);
        }
        return result;
    }

    /**
     * 针对数组 nums 指定的区间 [left, right] 进行归并排序，在排序的过程中完成统计任务
     */
    private void mergeAndCountSmaller(int[] nums, int left, int right, int[] indexes, int[] temp, int[] res) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeAndCountSmaller(nums, left, mid, indexes, temp, res);
        mergeAndCountSmaller(nums, mid + 1, right, indexes, temp, res);

        // 归并排序的优化，如果索引数组有序，则不存在逆序关系，没有必要合并
        if (nums[indexes[mid]] <= nums[indexes[mid + 1]]) {
            return;
        }
        //[left, mid] 是排好序的，[mid + 1, right] 是排好序的
        mergeOfTwoSortedArrAndCountSmaller(nums, left, mid, right, indexes, temp, res);
    }

    /**
     * [left, mid] 是排好序的，[mid + 1, right] 是排好序的
     */
    private void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int mid, int right, int[] indexes, int[] temp, int[] res) {
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                indexes[k] = temp[j];
                j++;
            } else if (j > right) {
                indexes[k] = temp[i];
                i++;
                res[indexes[k]] += (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                // 注意：这里是 <= ，保证稳定性
                indexes[k] = temp[i];
                i++;
                res[indexes[k]] += (j - mid - 1);
            } else {
                indexes[k] = temp[j];
                j++;
            }
        }
    }

    @Override
    public String removeDuplicateLetters(){
        System.out.println(JSON.toJSONString(removeDuplicateLetters("bcabc")));
        return "success";
    }

    public String removeDuplicateLetters(String s) {
        boolean[] flagArr = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char chars = s.charAt(i);
            int index = chars - 'a';
            //sb中是否包括此字母, 包括则跳过
            if (!flagArr[index]) {
                //当前值 < 最后的值
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > chars) {
                    //判断当前字母在后面还是否存在， 如果存在就干掉
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        flagArr[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        //sb的最后一个字母再后面没有了
                        break;
                    }
                }
                flagArr[index] = true;
                sb.append(chars);
            }
            //因为每次计算的是sb中最后一个字母而非本字母, 所以要进行扣除
            num[index] -= 1;
        }
        return sb.toString();
    }

    @Override
    public String maxNumber(){
        System.out.println(JSON.toJSONString(maxNumber(new int[]{3,4,6,5}, new int[]{9,1,2,5,8,3}, 5)));
        return "success";
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxArr = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        //循环选择所有的可能, 对比最大的
        for (int i = start; i <= end; i++) {
            //如果只要i个元素, 获取相应的最优解
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            //按照归并排序的逻辑进行合并
            int[] curArr = merge(subsequence1, subsequence2);
            //对比当前计算结果和上次的计算结果, 如果这次的计算结果更大就进行复制
            if (compare(curArr, 0, maxArr, 0) > 0) {
                System.arraycopy(curArr, 0, maxArr, 0, k);
            }
        }
        return maxArr;
    }

    /**
     * 如果只要i个元素, 获取相应的最优解
     */
    public int[] maxSubsequence(int[] nums, int i) {
        int length = nums.length;
        int[] stack = new int[i];
        int top = -1;
        int remain = length - i;
        for (int num : nums) {
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < i - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    /**
     * 从某个index开始, 对比数组的大小
     */
    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    @Override
    public String coinChange(){
        System.out.println(JSON.toJSONString(coinChange(new int[]{186,419,83,408}, 6249)));
        return "success";
    }

    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始化dp数组为最大值
        Arrays.fill(dp, max);
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    @Override
    public String wiggleSort(){
        int[] arr = new int[]{1,5,1,1,6,4};
        wiggleSort(arr);
        System.out.println(JSON.toJSONString(arr));
        return "success";
    }

    public void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        //[1,2,3,4]
        Arrays.sort(arr);
        int n = nums.length;
        int j = (n + 1) / 2 - 1;
        int k = n - 1;
        //[2,4,1,3], 以这样的顺序插入, 防止出现排序后的同值造成数组异常
        for (int i = 0; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    @Override
    public String oddEvenList(){
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(JSON.toJSONString(oddEvenList(listNode1)));
        return "success";
    }

    public ListNode oddEvenList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode listNode = head;
        ListNode odd = listNode;
        int i = 1;
        while(listNode.next != null){
            i++;
            if(i % 2 == 0){
                listNode = listNode.next;
                continue;
            }
            ListNode temp = listNode.next;
            listNode.next = temp.next;
            temp.next = odd.next;
            odd.next = temp;
            odd = odd.next;
        }
        return head;
    }

    @Override
    public String longestIncreasingPath(){
        System.out.println(JSON.toJSONString(longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}})));
        return "success";
    }

    public int[][] longestIncreasingPathDirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPathRows, longestIncreasingPathColumns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        longestIncreasingPathRows = matrix.length;
        longestIncreasingPathColumns = matrix[0].length;
        int[][] memo = new int[longestIncreasingPathRows][longestIncreasingPathColumns];
        int ans = 0;
        for (int i = 0; i < longestIncreasingPathRows; ++i) {
            for (int j = 0; j < longestIncreasingPathColumns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        memo[row][column]++;
        for (int[] dir : longestIncreasingPathDirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < longestIncreasingPathRows && newColumn >= 0 && newColumn < longestIncreasingPathColumns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }

    @Override
    public String increasingTriplet(){
        System.out.println(JSON.toJSONString(increasingTriplet(new int[]{2,1,5,0,4,6})));
        return "success";
    }

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    @Override
    public String rob3(){
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.right.right = new TreeNode(2);
        treeNode.left.right.right.right = new TreeNode(5);
        System.out.println(JSON.toJSONString(rob(treeNode)));
        return "success";
    }

    public int rob(TreeNode root) {
        int[] rootStatus = robDfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] robDfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = robDfs(node.left);
        int[] right = robDfs(node.right);
        int selected = node.val + left[1] + right[1];
        int notSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{selected, notSelected};
    }

    @Override
    public String countBits(){
        System.out.println(JSON.toJSONString(countBits(5)));
        return "success";
    }

    public int[] countBits(int num) {
        int dp[] = new int[num + 1];
        for (int i = 0; i <= num / 2; i++) {
            dp[i * 2] = dp[i];
            if(i * 2 + 1 <= num){
                dp[i * 2 + 1] = dp[i] + 1;
            }
        }
        return dp;
    }

    @Override
    public String integerBreak(){
        System.out.println(JSON.toJSONString(integerBreak(36)));
        return "success";
    }

    public int integerBreak(int n) {
        if(n <= 3){
            return n - 1;
        }
        int res = 1;
        while(n > 4){
            res = 3 * res;
            n = n - 3;
        }
        if(n >= 2){
            res = res * n;
        }
        return res;
    }

    @Override
    public String reverseVowels(){
        System.out.println(JSON.toJSONString(reverseVowels("IceCreAm")));
        return "success";
    }

    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int start = 0, end = n - 1;
        while (start < end) {
            while (start < n && isConsonant(arr[start])) {
                start++;
            }
            while (end > 0 && isConsonant(arr[end])) {
                end--;
            }
            if (start < end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return new String(arr);
    }

    private boolean isConsonant(char ch) {
        return "aeiouAEIOU".indexOf(ch) < 0;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String intersection(){
        System.out.println(JSON.toJSONString(intersection(new int[]{1,2,2,1}, new int[]{2,2})));
        return "success";
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    @Override
    public String maxEnvelopes(){
        System.out.println(JSON.toJSONString(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}})));
        return "success";
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e2[1] - e1[1]);

        List<Integer> list = new ArrayList<>();
        list.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int index = maxEnvelopesBinarySearch(list, num);
                list.set(index, num);
            }
        }
        return list.size();
    }

    private int maxEnvelopesBinarySearch(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    @Override
    public String canMeasureWater(){
        System.out.println(JSON.toJSONString(canMeasureWater(3, 5, 4)));
        return "success";
    }

    public boolean canMeasureWater(int x, int y, int z) {
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{0, 0});
        Set<Long> set = new HashSet<>();
        while (!stack.isEmpty()) {
            if (set.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            set.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remainX = state[0], remainY = state[1];
            if (remainX == z || remainY == z || remainX + remainY == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[]{x, remainY});
            // 把 Y 壶灌满。
            stack.push(new int[]{remainX, y});
            // 把 X 壶倒空。
            stack.push(new int[]{0, remainY});
            // 把 Y 壶倒空。
            stack.push(new int[]{remainX, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[]{remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[]{remainX + Math.min(remainY, x - remainX), remainY - Math.min(remainY, x - remainX)});
        }
        return false;
    }

    public long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    @Override
    public String kSmallestPairs(){
        System.out.println(JSON.toJSONString(kSmallestPairs(new int[]{2,4,6}, new int[]{1,7,11}, 3)));
        return "success";
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(k, (o1, o2)-> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            priorityQueue.offer(new int[]{i,0});
        }

        List<List<Integer>> ansList = new ArrayList<>();
        while (k-- > 0 && !priorityQueue.isEmpty()) {
            int[] idxPair = priorityQueue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ansList.add(list);
            if (idxPair[1] + 1 < n) {
                priorityQueue.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ansList;
    }

    @Override
    public String wiggleMaxLength(){
        System.out.println(JSON.toJSONString(wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8})));
        return "success";
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    @Override
    public String lexicalOrder(){
        System.out.println(JSON.toJSONString(lexicalOrder(56)));
        return "success";
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            list.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return list;
    }

    @Override
    public String firstUniqChar(){
        System.out.println(JSON.toJSONString(firstUniqChar("loveleetcode")));
        return "success";
    }

    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String isSubsequence(){
        System.out.println(JSON.toJSONString(isSubsequence("b", "abc")));
        return "success";
    }

    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()){
            return true;
        }
        int n = t.length(), m = s.length(), j = 0;
        for (int i = 0; i < n; i++) {
            if(t.charAt(i) != s.charAt(j)){
                continue;
            }
            j++;
            if(j == m){
                return true;
            }
        }
        return false;
    }

    @Override
    public String decodeString(){
        System.out.println(JSON.toJSONString(decodeString("100[leetcode]")));
        return "success";
    }

    public String decodeString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--){
            char c = s.charAt(i);
            if(c == ']'){
                int j = i - 1, count = 1;
                for(; j >= 0; j--){
                    c = s.charAt(j);
                    if(c == ']'){
                        count++;
                    }else if(c == '['){
                        count--;
                        if(count == 0){
                            String part = decodeString(s.substring(j + 1, i));
                            int k = j - 1;
                            for(; k >= 0; k--){
                                if(s.charAt(k) < '0' || s.charAt(k) > '9'){
                                    break;
                                }
                            }
                            int num = Integer.parseInt(s.substring(k + 1, j));
                            for (k = 0; k < num; k++) {
                                stringBuilder.insert(0, part);
                            }
                            i = j - 1;
                            break;
                        }
                    }
                }
            }else if(c >= 'a' && c <= 'z'){
                stringBuilder.insert(0, c);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String removeKdigits(){
        System.out.println(JSON.toJSONString(removeKdigits("1432219", 2)));
        return "success";
    }

    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char chars = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > chars) {
                //如果出现高位的数字 > 底位的数字, 那么就过滤掉
                deque.pollLast();
                k--;
            }
            deque.offerLast(chars);
        }

        for (int i = 0; i < k; i++) {
            //如果前面没过滤全, 那么从高位开始过滤
            deque.pollLast();
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            stringBuilder.append(digit);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

    @Override
    public String canCross(){
        System.out.println(JSON.toJSONString(canCross(new int[]{0,1,3,5,6,8,12,17})));
        return "success";
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k > j + 1) {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String reconstructQueue(){
        System.out.println(JSON.toJSONString(reconstructQueue(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}})));
        return "success";
    }

    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        int[][] ans = new int[n][2];
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));//按照people[0]升序, people[1]降序
        List<Integer> seats = new ArrayList<>();
        // 初始化座位列表
        for (int i = 0; i < n; i++) {
            seats.add(i);
        }
        for (int i = 0; i < n; i++) {
            int k = people[i][1];
            int seat = seats.get(k);
            // 移除第 k 个位置代表该位置已落座，且不参与到还未落座人的 k 中（其只会比还未落座的人矮，就算相同，座位也在该身高相同但未落座的人后面）
            seats.remove(k);
            ans[seat] = people[i];
        }
        return ans;
    }

    @Override
    public String trapRainWater(){
//        System.out.println(JSON.toJSONString(trapRainWater(new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}})));
//        System.out.println(JSON.toJSONString(trapRainWater(new int[][]{{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}})));
        System.out.println(JSON.toJSONString(trapRainWater(new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}})));
        return "success";
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }

    @Override
    public String longestPalindrome(){
        System.out.println(longestPalindrome("AAabccccddzz"));
        return "success";
    }

    public int longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1){
            return n;
        }
        int[] arr = new int[58];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'A']++;
        }
        int max = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            max += arr[i] >> 1 << 1;
            if(!flag && arr[i] % 2 == 1){
                flag = true;
            }
        }
        return max + (flag ? 1 : 0);
    }

    @Override
    public String splitArray(){
        System.out.println(JSON.toJSONString(splitArray(new int[]{1,2,3,4,5}, 2)));
        return "success";
    }

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    //前i个元素切为j份的结果 = min(历史计算的结果, max(按k切割时少一切份计算的结果, 按k切割时后半部分的结果))
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

    @Override
    public String canPartition(){
        System.out.println(JSON.toJSONString(canPartition(new int[]{1,5,11,5})));
        return "success";
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        //和为奇数, 返回false
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        //最大的数字超过预期值, 返回false
        if (maxNum > sum) {
            return false;
        }
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] | dp[j - num];
            }
        }
        return dp[sum];
    }

    @Override
    public String findRightInterval(){
        System.out.println(JSON.toJSONString(findRightInterval(new int[][]{{3,4},{2,3},{1,2}})));
        return "success";
    }

    /**
     * 扫描所有区间找到其起点大于当前区间的终点的区间（具有最小差值），时间复杂度为 O(n的平方)
     * 首先我们可以对区间 intervals 的起始位置进行排序，并将每个起始位置 intervals[i][0]对应的索引 i 存储在数组 arr 中
     * 然后枚举每个区间 i 的右端点 intervals[i][1]，利用二分查找来找到大于等于 intervals[i][1] 的最小值 val 即可
     * 此时区间 i 对应的右侧区间即为右端点 val 对应的索引
     */
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = i;
        }
        Arrays.sort(arr);

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            int target = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid][0] >= intervals[i][1]) {
                    target = arr[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
    }

    @Override
    public String findAnagrams(){
        System.out.println(JSON.toJSONString(findAnagrams("cbaebabacd", "abc")));
        return "success";
    }

    public List<Integer> findAnagrams(String s, String p) {
        int pSize = p.length(), sSize = s.length();
        if (sSize < pSize) {
            return new ArrayList<>();
        }

        int[] arr = new int[26];
        for (int i = 0; i < pSize; i++) {
            arr[p.charAt(i) - 'a']--;
            arr[s.charAt(i) - 'a']++;
        }
        int diff = 0;
        for (int j = 0; j < 26; j++) {
            if(arr[j] != 0){
                diff++;
            }
        }

        List<Integer> list = new ArrayList<>();
        if(diff == 0){
            list.add(0);
        }
        for (int i = pSize; i < sSize; i++) {
            //移除开头的字母
            if(arr[s.charAt(i - pSize) - 'a'] == 1){
                diff--;
            }else if(arr[s.charAt(i - pSize) - 'a'] == 0){
                diff++;
            }
            arr[s.charAt(i - pSize) - 'a']--;
            //补充当前字母
            if(arr[s.charAt(i) - 'a'] == -1){
                diff--;
            }else if(arr[s.charAt(i) - 'a'] == 0){
                diff++;
            }
            arr[s.charAt(i) - 'a']++;
            //完全相同, 添加索引
            if(diff == 0){
                list.add(i - pSize + 1);
            }
        }
        return list;
    }

    @Override
    public String compress(){
        System.out.println(JSON.toJSONString(compress(new char[]{'a','a','b','b','b','b','b','b','b','b','b','b','b','b'})));
        return "success";
    }

    public int compress(char[] chars) {
        int n = chars.length, write = 0, left = 0;
        for (int i = 0; i < n; i++) {
            if (i != n - 1 && chars[i] == chars[i + 1]) {
                continue;
            }
            chars[write++] = chars[i];
            int num = i - left + 1;
            if (num > 1) {
                int anchor = write;
                while (num > 0) {
                    chars[write++] = (char) (num % 10 + '0');
                    num /= 10;
                }
                reverse(chars, anchor, write - 1);
            }
            left = i + 1;
        }
        return write;
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }


    @Override
    public String findContentChildren(){
        System.out.println(JSON.toJSONString(findContentChildren(new int[]{1,2,3}, new int[]{3})));
        return "success";
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0, j = -1, gNum = g.length, sNum = s.length - 1;
        for (int i = 0; i < gNum; i++) {
            while(j < sNum) {
                j++;
                if(s[j] >= g[i]){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    @Override
    public String medianSlidingWindow(){
        System.out.println(JSON.toJSONString(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        return "success";
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k - 1; i++) {
            medianSlidingWindowAdd(minPriorityQueue, maxPriorityQueue, nums[i]);
        }
        double[] arr = new double[nums.length - k];
        int index = 0;
        for (int i = 0; i < nums.length - k; i++) {
            medianSlidingWindowAdd(minPriorityQueue, maxPriorityQueue, nums[i + k]);
            arr[index++] = medianSlidingWindowMedian(minPriorityQueue, maxPriorityQueue);
            minPriorityQueue.remove(nums[i]);
            maxPriorityQueue.remove(nums[i]);
        }
        return arr;
    }

    public double medianSlidingWindowMedian(PriorityQueue<Integer> minPriorityQueue, PriorityQueue<Integer> maxPriorityQueue) {
        if(minPriorityQueue.size() > maxPriorityQueue.size()){
            return minPriorityQueue.peek();
        }
        return (double)(maxPriorityQueue.peek() + minPriorityQueue.peek()) / 2;
    }

    private void medianSlidingWindowAdd(PriorityQueue<Integer> minPriorityQueue, PriorityQueue<Integer> maxPriorityQueue, int num){
        minPriorityQueue.add(num);
        if(minPriorityQueue.size() > maxPriorityQueue.size() + 1){
            maxPriorityQueue.add(minPriorityQueue.poll());
        }
    }

    @Override
    public String predictTheWinner(){
        System.out.println(JSON.toJSONString(predictTheWinner(new int[]{1,5,233,7})));
        return "success";
    }

    public boolean predictTheWinner(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                //计算出两个人差异值并存放在数组中继续使用
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    @Override
    public String reversePairs(){
        System.out.println(JSON.toJSONString(reversePairs(new int[]{1,3,2,3,1})));
        return "success";
    }

    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    public int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int n1 = reversePairsRecursive(nums, left, mid);
        int n2 = reversePairsRecursive(nums, mid + 1, right);
        int ret = n1 + n2;

        // 首先统计下标对的数量
        int i = left;
        int j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            ret += j - mid - 1;
            i++;
        }

        // 随后合并两个排序数组
        int[] sorted = new int[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) {
                sorted[p++] = nums[p2++];
            } else if (p2 > right) {
                sorted[p++] = nums[p1++];
            } else {
                if (nums[p1] < nums[p2]) {
                    sorted[p++] = nums[p1++];
                } else {
                    sorted[p++] = nums[p2++];
                }
            }
        }
        for (int k = 0; k < sorted.length; k++) {
            nums[left + k] = sorted[k];
        }
        return ret;
    }

    @Override
    public String updateBoard(){
        char[][] arr = new char[][]{{'E','E','E','E','E','E','E','E'},
                        {'E','E','E','E','E','E','E','M'},
                        {'E','E','M','E','E','E','E','E'},
                        {'M','E','E','E','E','E','E','E'},
                        {'E','E','E','E','E','E','E','E'},
                        {'E','E','E','E','E','E','E','E'},
                        {'E','E','E','E','E','E','E','E'},
                        {'E','E','M','M','E','E','E','E'}};
        System.out.println(JSON.toJSONString(updateBoard(arr, new int[]{0,0})));
        return "success";
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int xClick = click[0];
        int yClick = click[1];
        if(board[xClick][yClick] == 'M'){
            board[xClick][yClick] = 'X';
            return board;
        }
        updateBoard(board, xClick, yClick);
        return board;
    }

    private void updateBoard(char[][] board, int x, int y) {
        if(board[x][y] == 'M' || board[x][y] == 'B' || (board[x][y] >= '1' && board[x][y] <= '9')){
            return;
        }
        //此点周围的雷数
        int count = boardCount(board, x, y);
        if(count > 0){
            board[x][y] = (char) (count + '0');
            return;
        }
        board[x][y] = 'B';
        //向外扩散, 只有是空的时候才会扩散
        updateBoardDiffuse(board, x, y);
    }

    /**
     * 向外扩散
     * @param board
     * @param x
     * @param y
     */
    private void updateBoardDiffuse(char[][] board, int x, int y){
        if(x > 0){
            if(y > 0){
                //左上
                updateBoard(board, x - 1, y - 1);
            }
            //左
            updateBoard(board, x - 1, y);
            if(y < board[0].length - 1){
                //左下
                updateBoard(board, x - 1, y + 1);
            }
        }
        if(y > 0){
            //上
            updateBoard(board, x, y - 1);
        }
        if(y < board[0].length - 1){
            //下
            updateBoard(board, x, y + 1);
        }
        if(x < board.length - 1){
            if(y > 0){
                //右上
                updateBoard(board, x + 1, y - 1);
            }
            //右
            updateBoard(board, x + 1, y);
            if(y < board[0].length - 1){
                //右下
                updateBoard(board, x + 1, y + 1);
            }
        }
    }

    /**
     * 计算周围雷的数量
     * @param board
     * @param x
     * @param y
     * @return
     */
    private int boardCount(char[][] board, int x, int y) {
        int count = 0;
        if(x > 0){
            if(y > 0 && board[x - 1][y - 1] == 'M'){
                //左上
                count++;
            }
            if(board[x - 1][y] == 'M'){
                //左
                count++;
            }
            if(y < board[0].length - 1 && board[x - 1][y + 1] == 'M'){
                //左下
                count++;
            }
        }
        if(y > 0 && board[x][y - 1] == 'M'){
            //上
            count++;
        }
        if(y < board[0].length - 1 && board[x][y + 1] == 'M'){
            //下
            count++;
        }
        if(x < board.length - 1){
            if(y > 0 && board[x + 1][y - 1] == 'M'){
                //右上
                count++;
            }
            if(board[x + 1][y] == 'M'){
                //右
                count++;
            }
            if(y < board[0].length - 1 && board[x + 1][y + 1] == 'M'){
                //右下
                count++;
            }
        }
        return count;
    }

    @Override
    public String findCircleNum(){
        System.out.println(JSON.toJSONString(findCircleNum(new int[][]{{1,0,0,0,0},{1,1,0,1,0},{0,0,1,1,1},{0,0,1,1,1},{0,0,1,1,1}})));
        return "success";
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length, count = 0;
        boolean[] flagArr = new boolean[n];
        for (int i = 0; i < isConnected.length; i++) {
            if(!flagArr[i]){
                findCircleNumDfs(isConnected, flagArr, n, i);
                count++;
            }
        }
        return count;
    }

    private void findCircleNumDfs(int[][] isConnected, boolean[] flagArr, int n, int i){
        for (int j = 0; j < n; j++) {
            if(isConnected[i][j] == 1 && !flagArr[j]){
                // 第i行的第j列是1, 那么第j行和第i行相连, 标记为已处理
                flagArr[j] = true;
                // 递归处理第j行
                findCircleNumDfs(isConnected, flagArr, n, j);
            }
        }
    }

    @Override
    public String canPlaceFlowers(){
        System.out.println(JSON.toJSONString(canPlaceFlowers(new int[]{1,0,0,0,0,1}, 2)));
        return "success";
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0){
            return true;
        }
        int m = flowerbed.length;
        for (int i = 0; i < m; i++) {
            if(flowerbed[i] == 1 || (i < m - 1 && flowerbed[i + 1] == 1) || (i > 0 && flowerbed[i - 1] == 1)){
                continue;
            }
            flowerbed[i] = 1;
            n--;
            if(n <= 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public String smallestRange(){
        List<Integer> list1 = Lists.newArrayList(4,10,15,24,26);
        List<Integer> list2 = Lists.newArrayList(0,9,12,20);
        List<Integer> list3 = Lists.newArrayList(5,18,22,30);
        System.out.println(JSON.toJSONString(smallestRange(Lists.newArrayList(list1, list2, list3))));
        return "success";
    }

    /**
     * 核心思路:将每个数及其隶属组存入二维数组,然后按照数字大小升序,
     * (每个一维数组下标0记录数字下标1记录隶属组)
     * 再使用滑动窗口直到包含指定的组,然后再收缩窗口直到数据遍历完毕,取最小区间即可.
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        // 数据总数
        int n = 0;
        for(List<Integer> list : nums){
            n += list.size();
        }
        // 二维数组
        int x = 0, y = 0;
        int[][] ordered = new int[n][2];
        for(List<Integer> list : nums){
            for(Integer tmp : list){
                ordered[x][0] = tmp;
                ordered[x][1] = y;
                x++;
            }
            y++;
        }
        // 排序-按照数字排序
        Arrays.sort(ordered);
        // 存储区间结果
        int[] result = new int[2];
        // 统计窗口内每个组所对应数字的具体个数
        int[] count = new int[nums.size()];
        // 滑动窗口内组的个数
        int groupSize = 0;
        // 找到指定数量的组后缩小窗口即减少组内元素的数量
        int tmp = 0;
        for(int i = 0; i < n; i++){
            // 如果没有记录过这个组则组的数量+1并且标记这个组内的元素数量也+1
            // 否则只记录组内元素数量+1
            // 这一步就是滑动窗口直到包含所有组,组内元素数量一般大于组的个数
            if(count[ordered[i][1]]++ == 0){
                groupSize++;
            }
            if(groupSize == nums.size()){
                // 找到指定数量的组后缩小窗口即减少组内元素的数量
                while(count[ordered[tmp][1]] > 1){
                    count[ordered[tmp++][1]]--;
                }
                // 包含初始条件/后续缩小窗口的结果
                if((result[0] == 0 && result[1] == 0) || result[1] - result[0] > ordered[i][0] - ordered[tmp][0]){
                    result = new int[]{ordered[tmp][0],ordered[i][0]};
                }
            }
        }
        return result;
    }

    @Override
    public String findMaxAverage(){
        System.out.println(JSON.toJSONString(findMaxAverage(new int[]{5}, 1)));
        return "success";
    }

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length, sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = k; i < n; i++) {
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return (double) max / k;
    }

    @Override
    public String predictPartyVictory(){
        System.out.println(JSON.toJSONString(predictPartyVictory("DRRDRDRDRDDRDRDR")));
        return "success";
    }

    private String predictPartyVictory(String senate){
        //r的数量, 被ban之后未处理的数量, 总共被ban的数量
        int rNum = 0;
        int rBanDiff = 0;
        int rBanTotal = 0;
        //d的数量, 被ban之后未处理的数量, 总共被ban的数量
        int dNum = 0;
        int dBanDiff = 0;
        int dBanTotal = 0;

        char[] arr = senate.toCharArray();
        boolean flag = true;
        int n = arr.length;
        while(true){
            for (int i = 0; i < n; i++) {
                char c = arr[i];
                if(c == 'R'){
                    if(flag){
                        rNum++;
                    }
                    if(rBanDiff == 0){
                        dBanDiff++;
                        dBanTotal++;
                        //兼容R全部被ban的场景
                        if(dBanTotal == dNum && !flag){
                            return "Radiant";
                        }
                    }else{
                        //被ban的还没处理完, 把他从R改为标记为r
                        rBanDiff--;
                        arr[i] = 'r';
                    }
                }else if(c == 'D'){
                    if(flag){
                        dNum++;
                    }
                    if(dBanDiff == 0){
                        rBanDiff++;
                        rBanTotal++;
                        //兼容D全部被ban的场景
                        if(rBanTotal == rNum && !flag){
                            return "Dire";
                        }
                    }else{
                        //被ban的还没处理完, 把他从D改为标记为d
                        dBanDiff--;
                        arr[i] = 'd';
                    }
                }
            }
            flag = false;
            //已经全部被ban
            if(dBanTotal >= dNum){
                return "Radiant";
            }
            if(rBanTotal >= rNum){
                return "Dire";
            }
        }
    }

    @Override
    public String findClosestElements(){
        System.out.println(JSON.toJSONString(findClosestElements(new int[]{1,2,4,5}, 4, 3)));
        return "success";
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = maxEnvelopesBinarySearch(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int maxEnvelopesBinarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    @Override
    public String searchBST(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(JSON.toJSONString(searchBST(root, 2)));
        return "success";
    }

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val < val ? root.right : root.left;
        }
        return root;
    }

    @Override
    public String kthLargest(){
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println("add: " + kthLargest.add(3));
        System.out.println("add: " + kthLargest.add(5));
        System.out.println("add: " + kthLargest.add(10));
        System.out.println("add: " + kthLargest.add(9));
        System.out.println("add: " + kthLargest.add(4));
        return "success";
    }

    class KthLargest {
        PriorityQueue<Integer> priorityQueue;

        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            priorityQueue = new PriorityQueue<>(k);
            for(int i: nums) {
                add(i);
            }
        }

        public int add(int val) {
            if(priorityQueue.size() < k) {
                priorityQueue.offer(val);
            } else if(priorityQueue.peek() < val) {
                priorityQueue.poll();
                priorityQueue.offer(val);
            }
            return priorityQueue.peek();
        }
    }

    @Override
    public String maxProfit6(){
        System.out.println(JSON.toJSONString(maxProfit6(new int[]{2,1,4,4,2,3,2,5,1,2}, 1)));
        return "success";
    }

    public int maxProfit6(int[] prices, int fee) {
        int[] buyArr = new int[prices.length];
        buyArr[0] = -prices[0];
        int[] saleArr = new int[prices.length];
        saleArr[0] = 0;
        for(int i = 1; i < prices.length; i++){
            buyArr[i] = Math.max(buyArr[i - 1], saleArr[i - 1] - prices[i]);
            saleArr[i] = Math.max(saleArr[i - 1], buyArr[i - 1] + prices[i] - fee);
        }
        return saleArr[prices.length - 1];
    }

    @Override
    public String findLength(){
        System.out.println(JSON.toJSONString(findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7})));
        return "success";
    }

    int mod = 1000000009;

    public int findLength(int[] A, int[] B) {
        int left = 1, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public boolean check(int[] A, int[] B, int len) {
        int base = 113;
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + A[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<>();
        bucketA.add(hashA);
        long mult = qPow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            hashA = ((hashA - A[i - len] * mult % mod + mod) % mod * base + A[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            hashB = ((hashB - B[i - len] * mult % mod + mod) % mod * base + B[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    // 使用快速幂计算 x^n % mod 的值
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }

    @Override
    public String pivotIndex(){
        System.out.println(JSON.toJSONString(pivotIndex(new int[]{1, 7, 3, 6, 5, 6})));
        return "success";
    }

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int sum = 0, val = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n; i++) {
            if(2 * val + nums[i] == sum){
                return i;
            }
            val += nums[i];
        }
        return -1;
    }

    @Override
    public String asteroidCollision(){
        System.out.println(JSON.toJSONString(asteroidCollision(new int[]{8,-8})));
        return "success";
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int aster : asteroids) {
            boolean flag = true;
            while (flag && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                flag = stack.peek() < -aster;
                if (stack.peek() <= -aster) {
                    stack.pop();
                }
            }
            if (flag) {
                stack.push(aster);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    @Override
    public String isBipartite(){
        System.out.println(JSON.toJSONString(isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}})));
        return "success";
    }

    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] colorArr;
    private boolean valid = true;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        colorArr = new int[n];
        for (int i = 0; i < n && valid; i++) {
            if (colorArr[i] == UNCOLORED) {
                isBipartiteDfs(i, RED, graph);
            }
        }
        return valid;
    }

    public void isBipartiteDfs(int i, int color, int[][] graph) {
        colorArr[i] = color;
        int c = color == RED ? GREEN : RED;
        for (int neighbor : graph[i]) {
            if (colorArr[neighbor] == UNCOLORED) {
                isBipartiteDfs(neighbor, c, graph);
                if (!valid) {
                    return;
                }
            } else if (colorArr[neighbor] != c) {
                valid = false;
                return;
            }
        }
    }

    @Override
    public String findCheapestPrice(){
        System.out.println(JSON.toJSONString(findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 1)));
        return "success";
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int defaultVal = Integer.MAX_VALUE / 2;
        int[] arr = new int[n];
        Arrays.fill(arr, defaultVal);
        arr[src] = 0;
        int ans = defaultVal;
        for (int i = 1; i <= k + 1; i++) {
            int[] newArr = new int[n];
            Arrays.fill(newArr, defaultVal);
            for (int[] flight : flights) {
                newArr[flight[1]] = Math.min(newArr[flight[1]], arr[flight[0]] + flight[2]);
            }
            arr = newArr;
            ans = Math.min(ans, arr[dst]);
        }
        return ans == defaultVal ? -1 : ans;
    }

    @Override
    public String allPathsSourceTarget(){
        System.out.println(JSON.toJSONString(allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}})));
        return "success";
    }

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        list.clear();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        allPathsSourceTarget(graph, graph.length, 0, list);
        return this.list;
    }

    public void allPathsSourceTarget(int[][] graph, int n, int i, List<Integer> list) {
        if(i == n - 1){
            this.list.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < graph[i].length; j++) {
            list.add(graph[i][j]);
            allPathsSourceTarget(graph, n, graph[i][j], list);
            list.remove(list.size() - 1);
        }
    }

    @Override
    public String longestMountain(){
        System.out.println(JSON.toJSONString(longestMountain(new int[]{0,1,0,1})));
        return "success";
    }

    public int longestMountain(int[] arr) {
        int mountLen = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int p = i - 1, q = i + 1;
                while (p > -1 && arr[p] < arr[p + 1]) {
                    p--;
                }
                while (q < arr.length && arr[q] < arr[q - 1]) {
                    q++;
                }
                mountLen = Math.max(mountLen, q - p - 1);
            }
        }
        return mountLen;
    }

    @Override
    public String peakIndexInMountainArray(){
        System.out.println(JSON.toJSONString(peakIndexInMountainArray(new int[]{0,1,0})));
        return "success";
    }

    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 1;
        int mid = 0;
        while(left < right){
            mid = left + (right - left) / 2;
            //左右都小于mid，说明mid是山峰。
            if(arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                break;
            }
            //右边比左边高，说明山峰在右侧
            if(arr[mid + 1] > arr[mid]) {
                left = mid;
            } else if(arr[mid + 1] < arr[mid]) {
                //右边比左边低，山峰在左侧
                right = mid;
            }
        }
        return mid;
    }

    @Override
    public String superpalindromesInRange(){
        System.out.println(JSON.toJSONString(superpalindromesInRange("38455498359", "999999999999999999")));
        return "success";
    }

    public int superpalindromesInRange(String L, String R) {
        long lNum = (long)Math.sqrt(Long.parseLong(L));
        long rNum = (long)Math.sqrt(Long.parseLong(R));
        int count = 0;
        if(lNum <= 3 && rNum >= 3){
            count++;
        }
        long i = 0;
        while (true){
            i++;
            //由于这个数要求平方后是回文，这要求这个数不能在相乘时候有进制的情况，所以，这里面的位数不可能大于3，所以这是一个3进制的数
            long temp = changeRadix(i, 10, 3);
            if(temp > rNum) {
                break;
            }
            if(temp >= lNum){
                if(isPalindrome(temp) && isPalindrome(temp * temp)){
                    count++;
                }
            }
        }
        return count;
    }

    private long changeRadix(long in, int source, int to){
        long re = 0L;
        long t = 1;
        do {
            re += (in % to) * t;
            t *= source;
            in /= to;
        } while (in > 0);
        return re;
    }

    private boolean isPalindrome(long l){
        long temp = 0;
        long temp2 = l;
        while (temp2 > 0){
            temp += temp2 % 10;
            temp2 /= 10;
            if(temp2 > 0) {
                temp *= 10;
            }
        }
        return temp == l;
    }

    @Override
    public String recentCounter(){
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(JSON.toJSONString(recentCounter.ping(1)));
        System.out.println(JSON.toJSONString(recentCounter.ping(100)));
        System.out.println(JSON.toJSONString(recentCounter.ping(3001)));
        System.out.println(JSON.toJSONString(recentCounter.ping(3002)));
        return "success";
    }

    class RecentCounter {
        int[] calls = new int[10005];
        //代表最后待插入的位置、t-3000范围的起始位置
        int i, j;
        public RecentCounter() {
            i = j = 0;
        }

        public int ping(int t) {
            calls[j] = t;
            j++;
            while(calls[i] < t - 3000){
                i++;
            }
            return j - i;
        }
    }

    @Override
    public String movesToStamp(){
        System.out.println(JSON.toJSONString(movesToStamp("abc", "aabcaca")));
        return "success";
    }

    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] doneArr = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        List<MovesToStampNode> list = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            //核对正确的部分
            Set<Integer> made = new HashSet<>();
            //核对错误的部分
            Set<Integer> todo = new HashSet<>();
            for (int j = 0; j < m; j++) {
                if (target.charAt(i + j) == stamp.charAt(j)){
                    made.add(i + j);
                } else {
                    todo.add(i + j);
                }
            }

            list.add(new MovesToStampNode(made, todo));

            // 如果这段完全符合stamp
            if (todo.isEmpty()) {
                stack.push(i);
                for (int j = i; j < i + m; j++) {
                    if (doneArr[j]) {
                        continue;
                    }
                    //记录这段内容的下标
                    queue.add(j);
                    doneArr[j] = true;
                }
            }
        }

        //从结果倒推, 每次只修复一个
        while (!queue.isEmpty()) {
            int i = queue.poll();
            //j取值范围:[往前推m字段长度, 距离最后还有m字段长度]
            for (int j = Math.max(0, i - m + 1); j <= Math.min(n - m, i); j++) {
                //stamp盖在j这个位置时, stamp在i位置的值与target对应位置不相同
                if (!list.get(j).todo.contains(i)) {
                    continue;
                }
                list.get(j).todo.remove(i);
                //仅有一个不同
                if (list.get(j).todo.isEmpty()) {
                    stack.push(j);
                    for (int made : list.get(j).made) {
                        if (doneArr[made]) {
                            continue;
                        }
                        queue.add(made);
                        doneArr[made] = true;
                    }
                }
            }
        }

        for (boolean done: doneArr){
            if (done) {
                continue;
            }
            return new int[0];
        }

        //转义返回值格式
        int[] arr = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()){
            arr[index++] = stack.pop();
        }
        return arr;
    }

    public static class MovesToStampNode {
        //核对正确的部分
        Set<Integer> made;
        //核对错误的部分
        Set<Integer> todo;
        MovesToStampNode(Set<Integer> made, Set<Integer> todo) {
            this.made = made;
            this.todo = todo;
        }
    }

    @Override
    public String validMountainArray(){
        System.out.println(JSON.toJSONString(validMountainArray(new int[]{0,3,2,1})));
        return "success";
    }

    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3){
            return false;
        }
        int left = 0, right = arr.length - 1;
        while (left < arr.length - 2 && arr[left] < arr[left + 1]) {
            left++;
        }
        while (right > 1 && arr[right] < arr[right - 1]) {
            right--;
        }
        return left == right;
    }

    @Override
    public String largestComponentSize(){
        System.out.println(JSON.toJSONString(largestComponentSize(new int[]{2,3,6,7,4,12,21,39})));
        return "success";
    }

    public static int largestComponentSizeN = (int) 1e5 + 7;

    public static int[] largestComponentSizeIsPrime = new int[largestComponentSizeN];

    public static int[] largestComponentSizePrimes = new int[largestComponentSizeN];

    //并查集
    public static int[] largestComponentSizeParent = new int[largestComponentSizeN];

    int largestComponentSizeK = 0;

    private int largestComponentSize(int[] nums) {
        //欧拉筛，找出1-n的所有质数
        for (int i = 2; i < largestComponentSizeN; i++) {
            if (largestComponentSizeIsPrime[i] == 0) {
                largestComponentSizePrimes[largestComponentSizeK++] = i;
            }
            for (int j = 0; largestComponentSizePrimes[j] * i < largestComponentSizeN; j++) {
                largestComponentSizeIsPrime[largestComponentSizePrimes[j] * i] = 1;
                if (i % largestComponentSizePrimes[j] == 0) {
                    break;
                }
            }
        }
        //初始化并查集
        for (int i = 0; i < largestComponentSizeN; i++) {
            largestComponentSizeParent[i] = i;
        }
        //遍历nums中的每个数,和他们的质因数连接
        for (int num : nums) {
            int quotient = num;
            for (int j = 0; j < largestComponentSizeK && largestComponentSizePrimes[j] * largestComponentSizePrimes[j] <= quotient; j++) {
                if (quotient % largestComponentSizePrimes[j] == 0) {
                    //primes[i]是他的质因数
                    union(num, largestComponentSizePrimes[j]);
                    while (quotient % largestComponentSizePrimes[j] == 0) {
                        quotient /= largestComponentSizePrimes[j];
                    }
                }
            }
            //假如剩下了一个质因数，也和num连接，使得不同的质因数可以联合到一起
            //这种情况是因为 num是一个合数 由不同的质因数相乘组成 把他的质因数 连接起来
            if (quotient > 1) {
                union(quotient, num);
            }
        }
        int[] cnt = new int[largestComponentSizeN];
        int ans = 0;
        //是否属于某个根
        for (int num : nums) {
            ans = Math.max(ans, ++cnt[find(num)]);
        }
        return ans;
    }

    private void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY) {
            largestComponentSizeParent[parentX] = parentY;
        }
    }

    private int find(int x) {
        // 采用路径压缩
        return largestComponentSizeParent[x] == x ? x : (largestComponentSizeParent[x] = find(largestComponentSizeParent[x]));
    }

    @Override
    public String longestOnes(){
        System.out.println(JSON.toJSONString(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2)));
        return "success";
    }

    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        //上次因为0超过k而断掉的坐标
        int left = 0;
        //在索引left时 左侧0的个数
        int leftZeroCount = 0;
        //在索引i时 左侧0的个数
        int zeroCount = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 0){
                zeroCount++;
            }
            while (zeroCount - k > leftZeroCount) {
                if(nums[left] == 0){
                    leftZeroCount++;
                }
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    @Override
    public String gcdOfStrings(){
        System.out.println(JSON.toJSONString(gcdOfStrings("ABABAB", "ABAB")));
        return "success";
    }

    public String gcdOfStrings(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    public int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    @Override
    public String findInMountainArray(){
        MountainArray mountainArr = new MountainArray() {

            int[] arr = new int[]{1,5,1};

            @Override
            public int get(int index) {
                return arr[index];
            }

            @Override
            public int length() {
                return arr.length;
            }
        };
        System.out.println(JSON.toJSONString(findInMountainArray(5, mountainArr)));
        return "success";
    }

    interface MountainArray {
        public int get(int index);
        public int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArray) {
        int peek = peakIndexInMountainArray(mountainArray);
        int ans = binSearch1(mountainArray, 0, peek, target);
        return ans >= 0 ? ans : binSearch2(mountainArray, peek, mountainArray.length(), target);
    }

    /**
     * 左边升序区间查找
     */
    private int binSearch1(MountainArray mountainArray, int l, int rr, int t) {
        int r = rr;
        while(l < r) {
            int m = (l + r) >>> 1;
            if(mountainArray.get(m) < t){
                l = m + 1;
            } else{
                r = m;
            }
        }
        return l < rr && mountainArray.get(l) == t ? l : -1;
    }

    /**
     * 右边降序区间查找
     */
    private int binSearch2(MountainArray mountainArray, int l, int rr, int t) {
        int r = rr;
        while(l < r) {
            int m = (l + r) >>> 1;
            if(mountainArray.get(m) > t){
                l = m + 1;
            } else{
                r = m;
            }
        }
        return l < rr && mountainArray.get(l) == t ? l : -1;
    }

    private int peakIndexInMountainArray(MountainArray mountainArr) {
        int left = 1;
        int right = mountainArr.length() - 1;
        int mid = 0;
        while(left < right){
            mid = left + (right - left) / 2;
            //左右都小于mid，说明mid是山峰。
            if(mountainArr.get(mid - 1) < mountainArr.get(mid) && mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                break;
            }
            //右边比左边高，说明山峰在右侧
            if(mountainArr.get(mid + 1) > mountainArr.get(mid)) {
                left = mid;
            } else if(mountainArr.get(mid + 1) < mountainArr.get(mid)) {
                //右边比左边低，山峰在左侧
                right = mid;
            }
        }
        return mid;
    }

    @Override
    public String longestCommonSubsequence(){
        System.out.println(JSON.toJSONString(longestCommonSubsequence("bsbininm", "jmjkbkjkv")));
        return "success";
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    //该字符可以加入LCS
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else{
                    //该位置的字符不相等，至少有一个不能加入LCS，先选择当前局部最优解，即选择前面的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node random;
        public Node next;
        public List<Node> neighbors;

        public Node() {
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    @Override
    public String uniqueOccurrences(){
        System.out.println(JSON.toJSONString(uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0})));
        return "success";
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if(map.size() <= 1){
            return true;
        }
        int[] countArr = new int[arr.length + 1];
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(countArr[entry.getValue()] == 1){
                return false;
            }
            countArr[entry.getValue()] = 1;
        }
        return true;
    }

    @Override
    public String longestZigZag(){
        TreeNode treeNode1 = new TreeNode(1, null, new TreeNode(1, null, new TreeNode(1)));
        TreeNode treeNode2 = new TreeNode(1, treeNode1, new TreeNode(1));
        TreeNode root = new TreeNode(1, null, new TreeNode(1, new TreeNode(1), treeNode2));
        System.out.println(JSON.toJSONString(longestZigZag(root)));
        return "success";
    }

    int longestZigZag;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestZigZag = 0;
        longestZigZag(root, 0, 0);
        return longestZigZag;
    }

    private void longestZigZag(TreeNode root, int left, int right){
        if(root == null){
            return;
        }
        longestZigZag = Math.max(longestZigZag, Math.max(left, right));
        longestZigZag(root.left, right + 1, 0);
        longestZigZag(root.right, 0, left + 1);
    }

    @Override
    public String goodNodes(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(JSON.toJSONString(goodNodes(root)));
        return "success";
    }

    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    public int goodNodes(TreeNode root, int max) {
        if(root == null){
            return 0;
        }
        int left = goodNodes(root.left, max);
        int right = goodNodes(root.right, max);
        return left + right + (root.val >= max ? 1 : 0);
    }

    @Override
    public String maxVowels(){
        System.out.println(JSON.toJSONString(maxVowels("leetcode", 2)));
        return "success";
    }

    public int maxVowels(String s, int k) {
        int n = s.length(), count = 0;
        String str = "aeiouAEIOU";
        for (int i = 0; i < k; i++) {
            if(str.indexOf(s.charAt(i)) < 0){
                continue;
            }
            count++;
        }
        int max = count;
        for (int i = k; i < n; i++) {
            count = count + (str.indexOf(s.charAt(i)) < 0 ? 0 : 1) - (str.indexOf(s.charAt(i - k)) < 0 ? 0 : 1);
            max = Math.max(max, count);
        }
        return max;
    }

    @Override
    public String kidsWithCandies(){
        System.out.println(JSON.toJSONString(kidsWithCandies(new int[]{4,2,1,1,2}, 1)));
        return "success";
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length, max = candies[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, candies[i]);
        }
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(candies[i] + extraCandies >= max);
        }
        return list;
    }

    @Override
    public String xorOperation(){
        System.out.println(JSON.toJSONString(xorOperation(5, 0)));
        return "success";
    }

    public int xorOperation(int n, int start) {
        int pre = start, res = pre;
        for (int i = 1; i < n; i++) {
            pre = start + 2 * i;
            res ^= pre;
        }
        return res;
    }

    @Override
    public String kthFactor(){
        System.out.println(JSON.toJSONString(kthFactor(12, 3)));
        return "success";
    }

    public int kthFactor(int n, int k) {
        int count = 0, i;
        for (i = 1; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            count++;
            if (count == k) {
                return i;
            }
        }
        i--;
        if (i * i == n) {
            i--;
        }
        for (; i > 0; i--) {
            if (n % i == 0) {
                count++;
                if (count == k) {
                    return n / i;
                }
            }
        }
        return -1;
    }

    @Override
    public String longestSubarray(){
        System.out.println(JSON.toJSONString(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1})));
        return "success";
    }

    public int longestSubarray(int[] nums) {
        int res = 0, a = 0, b = 0;
        for (int num : nums) {
            if (num == 0) {
                b = a;
                a = 0;
            } else {
                a++;
                b++;
            }
            res = Math.max(res, b);
        }
        if (res == nums.length) {
            res--;
        }
        return res;
    }

    @Override
    public String numIdenticalPairs(){
        System.out.println(JSON.toJSONString(numIdenticalPairs(new int[]{1,2,3,1,1,3})));
        return "success";
    }

    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int n = map.getOrDefault(num, 0);
            map.put(num, n + 1);
            ans += n;
        }
        return ans;
    }

    @Override
    public String closeStrings(){
        System.out.println(JSON.toJSONString(closeStrings("cabbba", "aabbss")));
        return "success";
    }

    public boolean closeStrings(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((count1[i] > 0 && count2[i] == 0) || (count1[i] == 0 && count2[i] > 0)) {
                return false;
            }
        }
        Arrays.sort(count1);
        Arrays.sort(count2);
        return Arrays.equals(count1, count2);
    }

    @Override
    public String maxOperations(){
        System.out.println(JSON.toJSONString(maxOperations(new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4}, 2)));
        return "success";
    }

    public int maxOperations(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int c = map.getOrDefault(k - num, 0);
            if (c > 0) {
                map.put(k - num, c - 1);
                ans++;
            } else {
                map.merge(num, 1, Integer::sum);
            }
        }
        return ans;
    }

    @Override
    public String largestAltitude(){
        System.out.println(JSON.toJSONString(largestAltitude(new int[]{-5,1,5,0,-7})));
        return "success";
    }

    public int largestAltitude(int[] gain) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : gain) {
            sum = sum + num;
            max = Math.max(max, sum);
        }
        return Math.max(max, 0);
    }

    @Override
    public String waysToFillArray(){
        System.out.println(JSON.toJSONString(waysToFillArray(new int[][]{{2,6},{5,1},{73,660}})));
        return "success";
    }

    public int[] waysToFillArray(int[][] queries) {
        int mod = 1000000007;

        int[][] arr = new int[10013][14];
        // 预处理组合数
        arr[0][0] = 1;
        for (int i = 1; i < 10013; i++) {
            arr[i][0] = 1;
            for (int j = 1; j < 14; j++) {
                arr[i][j] = (arr[i - 1][j] + arr[i - 1][j - 1]) % mod;
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int n = q[0], k = q[1];
            long res = 1;
            for (int j = 2; j * j <= k; j++) {
                if (k % j == 0) {
                    // i 是 k 的质因子
                    int e = 1;
                    for (k /= j; k % j == 0; k /= j) {
                        // 统计有多少个质因子 i
                        e++;
                    }
                    res = res * arr[e + n - 1][e] % mod;
                }
            }
            if (k > 1) {
                // 还剩下一个质因子
                res = res * n % mod;
            }
            ans[i] = (int) res;
        }
        return ans;
    }

    @Override
    public String mergeAlternately(){
        System.out.println(JSON.toJSONString(mergeAlternately("abc", "pqr")));
        return "success";
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder ans = new StringBuilder();
        int m = word1.length(), n = word2.length(), i = 0, j = 0;
        while (i < m && j < n) {
            ans.append(word1.charAt(i++));
            ans.append(word2.charAt(j++));
        }
        ans.append(word1, i, m);
        ans.append(word2, j, n);
        return ans.toString();
    }

    @Override
    public String findGCD(){
        System.out.println(JSON.toJSONString(findGCD(new int[]{2,5,6,9,10})));
        return "success";
    }

    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        while(min != 0){
            int x = max;
            max = min;
            min = x % min;
        }
        return max;
    }

    @Override
    public String maxTaskAssign(){
        System.out.println(JSON.toJSONString(maxTaskAssign(new int[]{3,2,1}, new int[]{0,3,3}, 1, 1)));
        return "success";
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int l = 0, r = Math.min(tasks.length, workers.length);
        while(l < r){
            int mid = (l + r) >> 1;
            if(canFinish(mid, tasks, workers, pills, strength)){
                l = mid;
            } else{
                r = mid - 1;
            }
            if(l == r - 1){
                if(canFinish(r, tasks, workers, pills, strength)){
                    l = r;
                }
                break;
            }
        }
        return l;
    }

    boolean canFinish(int num, int[] tasks, int[] workers, int pills, int strength){
        Deque<Integer> deque = new ArrayDeque<>();
        int p = workers.length - 1;
        for(int i = num - 1; i >= 0; i--){
            while(p >= workers.length - num && workers[p] >= tasks[i] - strength){
                deque.add(workers[p]);
                p--;
            }
            if(!deque.isEmpty() && deque.getFirst() >= tasks[i]){
                deque.removeFirst();
            } else{
                if(pills == 0 || deque.isEmpty()){
                    return false;
                }
                deque.removeLast();
                pills--;
            }
        }
        return true;
    }

    @Override
    public String deleteMiddle(){
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(JSON.toJSONString(deleteMiddle(listNode1)));
        return "success";
    }

    public ListNode deleteMiddle(ListNode head) {
        if(head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    @Override
    public String pairSum(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2, listNode1);
        ListNode listNode3 = new ListNode(4, listNode2);
        ListNode head = new ListNode(5, listNode3);
        System.out.println(JSON.toJSONString(pairSum(head)));
        return "success";
    }

    public int pairSum(ListNode head) {
        ListNode prev = null, slow = head, fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            //将前半部分链表反转
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        int max = 0;
        while(slow != null){
            max = Math.max(max, slow.val + prev.val);
            slow = slow.next;
            prev = prev.next;
        }
        return max;
    }

    @Override
    public String findDifference(){
        System.out.println(JSON.toJSONString(findDifference(new int[]{1,2,3,3}, new int[]{2,4,6})));
        return "success";
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int n : nums2) {
            set2.add(n);
        }
        for (int n : nums1) {
            set1.add(n);
        }
        for (int n : nums2) {
            set1.remove(n);
        }
        for (int n : nums1) {
            set2.remove(n);
        }
        return Lists.newArrayList(Lists.newArrayList(set1), Lists.newArrayList(set2));
    }

    @Override
    public String sum(){
        System.out.println(JSON.toJSONString(sum(12, 5)));
        return "success";
    }

    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public String checkTree(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        System.out.println(JSON.toJSONString(checkTree(treeNode)));
        return "success";
    }

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

    @Override
    public String equalPairs(){
        System.out.println(JSON.toJSONString(equalPairs(new int[][]{{3,2,1},{1,7,6},{2,7,7}})));
        return "success";
    }

    public int equalPairs(int[][] grid) {
        int num = 0, n = grid.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (equal(row, col, n, grid)) {
                    num++;
                }
            }
        }
        return num;
    }

    public boolean equal(int row, int col, int n, int[][] grid) {
        for (int i = 0; i < n; i++) {
            if (grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String removeStars(){
        System.out.println(JSON.toJSONString(removeStars("leet**cod*e")));
        return "success";
    }

    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '*') {
                sb.append(c);
            } else {
                sb.setLength(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    @Override
    public String smallestEvenMultiple(){
        System.out.println(JSON.toJSONString(smallestEvenMultiple(5)));
        return "success";
    }

    public int smallestEvenMultiple(int n) {
        return n << (n & 1);
    }

    @Override
    public String convertTemperature(){
        System.out.println(JSON.toJSONString(convertTemperature(122.11)));
        return "success";
    }

    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }
}
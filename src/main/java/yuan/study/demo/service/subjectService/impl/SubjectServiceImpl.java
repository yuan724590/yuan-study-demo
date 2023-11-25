package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.ListNode;
import yuan.study.demo.service.subjectService.SubjectService;

import java.util.*;


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
        System.out.println(longestPalindrome("addc"));
    }

    public String longestPalindrome(String s) {
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
        if (str.length() == 0) return 0;
        if (!Character.isDigit(str.charAt(0))
                && str.charAt(0) != '-' && str.charAt(0) != '+')
            return 0;
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
                if(sum < target)
                    j++;
                else if(sum > target)
                    k--;
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
        if(digits == null || digits.length() == 0) return res;
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

    public  String countAndSay(int n) {
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
        if(sum > target)
            return;
        else if(sum == target){
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
        if (num1.equals("0") || num2.equals("0")) {
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
                if (cur.next[ch] == null)
                    cur.next[ch] = new TrieNode();
                cur = cur.next[ch];
            }
            // 加上一个标记，表示为一个单词
            cur.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0, len = word.length(), ch; i < len; i++) {
                ch = word.charAt(i) - 'a';
                if (cur.next[ch] == null)
                    return false;
                cur = cur.next[ch];
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0, len = prefix.length(), ch; i < len; i++) {
                ch = prefix.charAt(i) - 'a';
                if (cur.next[ch] == null)
                    // 若还没遍历完给定的前缀子串，则直接返回false
                    return false;
                cur = cur.next[ch];
            }
            return true;
        }
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
            if (findWordsThree.check(word)) findWordsTrie.insert(word);
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
            if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1) return;
            if (board[i][j] == '#') return;
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
                if (n >= p && !dict.contains(word.substring(p - 3, p))) return false;
            }
            return true;
        }
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
                if(dp[mid] < num)
                    low = mid + 1;
                else
                    hight = mid;
            }
            dp[low] = num;
            if(low == maxL)
                maxL++;
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
            if(temp > rNum)
                break;
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
            if(temp2 > 0)
                temp *= 10;
        }
        return temp == l;
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
}
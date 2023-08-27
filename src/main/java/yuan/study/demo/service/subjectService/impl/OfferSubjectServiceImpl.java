package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.ListNode;
import yuan.study.demo.service.subjectService.OfferSubjectService;

import java.util.*;

@Slf4j
@Service
public class OfferSubjectServiceImpl implements OfferSubjectService {

    @Override
    public String findRepeatNumber(){
        int result = findRepeatNumber1(new int[]{2, 3, 1, 0, 2, 5, 3});
        log.info("执行结果1:{}", result);
        result = findRepeatNumber2(new int[]{3,4,2,1,1,0});
        log.info("执行结果2:{}", result);
        return "success";
    }

    /**
     * 时间O(n)
     * 空间O(n)
     * 不修改原数据
     */
    public int findRepeatNumber1(int[] nums) {
        if(nums == null){
            return -1;
        }
        boolean[] arr = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(arr[nums[i]]){
                return nums[i];
            }
            arr[nums[i]] = true;
        }
        return -1;
    }

    /**
     * 时间O(n)
     * 空间O(1)
     * 修改原数据
     */
    public int findRepeatNumber2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int temp;
        for(int i = 0 ; i < nums.length; i++){
            //如果该数字没有不和他的索引相等
            while(nums[i] != i){
                //重复返回
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                //不重复交换
                temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return -1;
    }

    @Override
    public String findNumberIn2DArray(){
        int[][] ints = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        boolean flag = findNumberIn2DArray(ints, 5);
        log.info("执行结果:{}", flag);
        return "success";
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        //从矩阵的左下角开始, 从右上角也同理
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while(row < m && col >= 0) {
            if(matrix[row][col] > target) {
                col--;
            }else if(matrix[row][col] < target) {
                row++;
            }else {
                return true;
            }
        }
        return false;
    }

    @Override
    public String replaceSpace(){
        String result = replaceSpace("We are happy.");
        log.info("执行结果:{}", result);
        return "success";
    }

    public String replaceSpace(String s) {
        if(s == null){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(' ' == s.charAt(i)){
                stringBuilder.append("%20");
            }else{
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String reversePrint(){
        ListNode listNode3 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(3, listNode3);
        ListNode listNode1 = new ListNode(2, listNode2);
        int[] result = reversePrint(listNode1);
        log.info("执行结果:{}", result);
        return "success";
    }

    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        int[] arr = new int[len];

        node = head;
        for (int i = len - 1; i > -1; i--) {
            arr[i] = node.val;
            node = node.next;
        }

        return arr;
    }

    @Override
    public String buildTree(){
        TreeNode result = buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});

        buildTree1(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        log.info("执行结果:{}", result);
        return "success";
    }

    private int[] preOrder, inOrder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preOrder = preorder;
        this.inOrder = inorder;
        return dfs(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode dfs(int preS, int preE, int inS, int inE) {
        if (preS > preE) return null;
        TreeNode root = new TreeNode(preOrder[preS]);

        for (int i = inS; i <= inE; i++) {
            if (inOrder[i] == preOrder[preS]) {
                root.left = dfs(preS + 1, preS + i - inS, inS, i - 1);
                root.right = dfs(preS + i - inS + 1, preE, i + 1, inE);
                break;
            }
        }
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0)
            return null;
        int rootVal = preorder[0], rootIndex = 0;
        for (int i = 0; i < n; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree1(Arrays.copyOfRange(preorder, 1, 1 + rootIndex), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree1(Arrays.copyOfRange(preorder, 1 + rootIndex, n), Arrays.copyOfRange(inorder, rootIndex + 1, n));

        return root;
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
    public String twoStackQueue(){
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);
        cQueue.appendTail(4);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        return "success";
    }

    class CQueue {
        LinkedList<Integer> stack1;
        LinkedList<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }

        public void appendTail(int value) {
            stack1.add(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                if (stack1.isEmpty()){
                    return -1;
                }
                while (!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
                return stack2.pop();
            } else {
                return stack2.pop();
            }
        }
    }

    @Override
    public String fib(){
        System.out.println("计算结果为:" + fib(90));
        return "success";
    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0, second = 1, result = 0;
        while (--n > 0) {
            result = first + second;
            if (result >= 1000000007) {
                result -= 1000000007;
            }
            first = second;
            second = result;
        }
        return result;
    }

//    /**
//     * 时间复杂度为O(log n)的方式计算斐波那契数列
//     */
//    public int f3(int n) {
//        if (n < 0) {
//            return 0;
//        }
//        if (n == 0 || n == 1) {
//            return 1;
//        }
//        int[][] base = {{1,1},{1,0}};
//        int[][] res = matrixPower(base, n - 1);
//        return res[0][0] + res[1][0];
//    }
//
//    /**
//     * 通过快速幂实现计算矩阵m的p次方
//     */
//    public int[][] matrixPower(int[][] m, int p) {
//        int[][] res = new int[m.length][m[0].length];
//        //先把res设为单位矩阵(对角线为1) 相当于整数中的1
//        for (int i = 0; i < res.length; i++) {
//            res[i][i] = 1;
//        }
//        //m赋值给tmp
//        int[][] tmp = m;
//        // p >>= 1 代表p右移一位赋值给p 右移一位相当于除以2  左移一位相当于乘以2
//        for (; p != 0; p >>= 1) {
//            //判断如果p是奇数
//            if ((p & 1) == 1) {
//                //p是奇数的时候, 调用矩阵相乘的函数
//                res = muliMatrix(res, tmp);
//            }
//            tmp = muliMatrix(tmp, tmp);
//        }
//        return res;
//    }
//
//    /**
//     * 两个矩阵相乘
//     */
//    public int[][] muliMatrix(int[][] m1, int[][] m2){
//        int[][] res = new int[m1.length][m2[0].length];
//        for(int i = 0; i < m1.length; i++){
//            for(int j = 0; j < m2[0].length; j++){
//                for(int k = 0; k < m2.length; k++){
//                    res[i][j] += m1[i][k] * m2[k][j];
//                }
//            }
//        }
//        return res;
//    }

    @Override
    public String numWays(){
        System.out.println("计算结果为:" + numWays(4));
        return "success";
    }

    public int numWays(int n) {
        return getStep(n);
    }

    private int getStep(int n){
        if(n < 2)
            return 1;
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++){
            a = a + b;
            b = a - b;
            a = a >= 1000000007 ? (a - 1000000007) : a;
        }
        return a;
    }

    @Override
    public String minArray(){
        System.out.println("计算结果为:" + minArray(new int[]{3,3,1,3}));
        return "success";
    }

    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if(numbers[i - 1] > numbers[i]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    @Override
    public String exist(){
        boolean flag = exist(new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}}, "ABCESEEEFS");
        System.out.println("计算结果为:" + flag);
        return "success";
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        char[] chars = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从 (0, 0) 点开始进行 dfs 操作，不断地去找，
                // 如果以 (0, 0) 点没有对应的路径的话，那么就从 (0, 1) 点开始去找
                if (dfs(board, chars, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] chars, boolean[][] visited, int i, int j, int start) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || chars[start] != board[i][j] || visited[i][j]) {
            return false;
        }
        if (start == chars.length - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean ans = dfs(board, chars, visited, i + 1, j, start + 1)
                || dfs(board, chars, visited, i - 1, j, start + 1)
                || dfs(board, chars, visited, i, j + 1, start + 1)
                || dfs(board, chars, visited, i, j - 1, start + 1);
        visited[i][j] = false;
        return ans;
    }

    @Override
    public String movingCount(){
        System.out.println("计算结果为:" + movingCount(11,8,16));
        return "success";
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    private int dfs(int i, int j, int m, int n, int k, boolean visited[][]) {
        if (i < 0 || i >= m || j < 0 || j >= n || (i / 10 + i % 10 + j / 10 + j % 10) > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(i + 1, j, m, n, k, visited) + dfs(i - 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited) + dfs(i, j - 1, m, n, k, visited) + 1;
    }

    @Override
    public String cuttingRope(){
        System.out.println("计算结果为:" + cuttingRope(8));
        return "success";
    }

    public int cuttingRope(int n) {
        if (n == 1 || n == 2)
            return 1;
        if (n == 3)
            return 2;
        int sum = 1;
        while (n > 4){
            sum *= 3;
            n -= 3;
        }
        return sum * n;
    }

    @Override
    public String cuttingRope2(){
        System.out.println("计算结果为:" + cuttingRope2(1000010003));
        return "success";
    }

    public int cuttingRope2(int n) {
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        long res = 1;
        while(n > 4){
            res *= 3;
            res = res % 1000000007;
            n -= 3;
        }
        return (int)(res * n % 1000000007);
    }

    @Override
    public String hammingWeight(){
        System.out.println("计算结果为:" + hammingWeight(128));
        return "success";
    }

    public int hammingWeight(int n) {
//        return Integer.bitCount(n);

        int count = 0;
        while(n != 0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    @Override
    public String myPow(){
        System.out.println("计算结果为:" + myPow(0.1, 2));
        return "success";
    }

    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        if(n == -1) return 1 / x;
        double half = myPow(x, n / 2);
        double mod = myPow(x, n % 2);
        return half * half * mod;
    }

    @Override
    public String printNumbers(){
        System.out.println("计算结果为:" + JSON.toJSONString(printNumbers(2)));
        return "success";
    }

    public int[] printNumbers1(int n) {
        int value = (int)Math.pow(10, n);
        int[] arr = new int[value - 1];
        for(int i = 1; i < value; i++){
            arr[i - 1] = i;
        }
        return arr;
    }


    List<String> ans = new ArrayList<>();
    public List<String> printNumbers(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; ++i){//总位数
            for(char j = '1'; j <= '9'; ++j){
                sb.append(j);
                dfs(i, 1, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return ans;
    }

    private void dfs(int len, int idx, StringBuilder sb){
        if(idx == len){
            ans.add(sb.toString());
            return;
        }
        for(char j = '0'; j <='9'; ++j){
            sb.append(j);
            dfs(len, idx+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    @Override
    public String deleteNode(){
        ListNode listNode3 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(3, listNode3);
        ListNode listNode1 = new ListNode(2, listNode2);
        System.out.println("计算结果为:" + JSON.toJSONString(deleteNode(listNode1, 2)));
        return "success";
    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head == null){
            return null;
        }
        // 虚拟头节点，统一处理
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 辅助节点
        ListNode temp = dummy;
        while(temp.next != null){
            if(temp.next.val == val){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return dummy.next;

    }

    @Override
    public String isMatch(){
        System.out.println("计算结果为:" + JSON.toJSONString(isMatch("aaa", "ab*ac*a")));
        return "success";
    }

    public boolean isMatch(String s, String p) {
        if (p.equals(".*")) {
            return true;
        }

        // 用dp[i][j]表示p的前i个字符和s的前j个字符的匹配情况
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];

        // 初始化, 空串可以和空串匹配
        dp[0][0] = true;
        // p为空，s非空时，p和s不匹配，所以dp[0][j]都等于false

        for (int i = 1; i <= pLen; ++i) {
            char pChar = p.charAt(i - 1);
            for (int j = 0; j <= sLen; ++j) {
                if (j == 0) {
                    if (pChar == '*' && i > 1) {
                        dp[i][j] = dp[i - 2][j];
                    }
                } else if (s.charAt(j - 1) == pChar || pChar == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChar == '*' && i > 1) {
                    dp[i][j] = dp[i - 2][j];
                    if (s.charAt(j - 1) == p.charAt(i - 2) || p.charAt(i - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[pLen][sLen];
    }

    @Override
    public String isNumber(){
        System.out.println("计算结果为:" + JSON.toJSONString(isNumber("6+1")));
        return "success";
    }

    /**
     * ‘.’出现正确情况：只出现一次，且在e的前面
     * ‘e’出现正确情况：只出现一次，且出现前有数字
     * ‘+’‘-’出现正确情况：只能在开头和e后一位
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        //去掉首位空格
        s = s.trim();
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                //判定为数字，则标记numFlag
                numFlag = true;
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                //判定为.  需要没出现过.并且没出现过e
                dotFlag = true;
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                //判定为e，需要没出现过e，并且出过数字了
                eFlag = true;
                //为了避免123e这种请求，出现e之后就标志为false
                numFlag = false;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                //判定为+-符号，只能出现在第一位或者紧接e后面
            } else {
                //其他情况，都是非法的
                return false;
            }
        }
        return numFlag;
    }

    @Override
    public String exchange(){
        System.out.println("计算结果为:" + JSON.toJSONString(exchange(new int[]{1,2,3,4})));
        return "success";
    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 != 0) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    @Override
    public String getKthFromEnd(){
        ListNode listNode3 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(3, listNode2);
        System.out.println("计算结果为:" + JSON.toJSONString(getKthFromEnd(listNode1, 2)));
        return "success";
    }

    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode frontNode = head, behindNode = head;
        while (frontNode != null && k > 0) {
            frontNode = frontNode.next;
            k--;
        }

        while (frontNode != null) {
            frontNode = frontNode.next;
            behindNode = behindNode.next;
        }

        return behindNode;
    }

    @Override
    public String reverseList(){
        ListNode listNode3 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(3, listNode2);
        System.out.println("计算结果为:" + JSON.toJSONString(reverseList(listNode1)));
        return "success";
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next;
        for(;;){
            if(cur == null){
                break;
            }
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    @Override
    public String mergeTwoLists(){
        ListNode listNode3 = new ListNode(3, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        ListNode listNode33 = new ListNode(5, null);
        ListNode listNode22 = new ListNode(3, listNode33);
        ListNode listNode11 = new ListNode(1, listNode22);
        System.out.println("计算结果为:" + JSON.toJSONString(mergeTwoLists(listNode1, listNode11)));
        return "success";
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode headNode = head;
        while(list1 != null || list2 != null) {

            if (list1 == null) {
                while(list2 != null) {
                    head.next = list2;
                    list2 = list2.next;
                    head = head.next;
                }
                return headNode.next;
            } else if (list2 == null) {
                while(list1 != null) {
                    head.next = list1;
                    list1 = list1.next;
                    head = head.next;
                }
                return headNode.next;
            }
            if (list1.val > list2.val) {
                head.next = list2;
                list2 = list2.next;
                head = head.next;
            } else {
                head.next = list1;
                list1 = list1.next;
                head = head.next;
            }
        }
        return headNode.next;
    }

    @Override
    public String isSubStructure(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(3);

        TreeNode treeNodeB = new TreeNode(1);
        treeNodeB.left = new TreeNode(-4);
        System.out.println("计算结果为:" + JSON.toJSONString(isSubStructure(treeNode, treeNodeB)));
        return "success";
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null) {
            return false;
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }
    public boolean dfs(TreeNode A, TreeNode B){
        if(B == null) {
            return true;
        }
        if(A == null) {
            return false;
        }
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    @Override
    public String mirrorTree(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(3);
        mirrorTree(treeNode);
        System.out.println("计算结果为:" + JSON.toJSONString(treeNode));
        return "success";
    }

    public TreeNode mirrorTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode right = root.right;
        root.right = root.left;
        root.left = right;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    @Override
    public String isSymmetric(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
//        treeNode.right = new TreeNode(1);
//        treeNode.left.left = new TreeNode(-4);
//        treeNode.left.right = new TreeNode(3);
        System.out.println("计算结果为:" + JSON.toJSONString(isSymmetric(treeNode)));
        return "success";
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    @Override
    public String spiralOrder(){
        System.out.println("计算结果为:" + JSON.toJSONString(spiralOrder(new int[][]{{3}, {4}, {5}})));
        return "success";
    }

    public int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new int[0];
        }
        int col = matrix[0].length;
        int[] res = new int[row * col];
        int idx = 0, left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (true) {
            //从左往右走
            for (int i = left; i <= right; i++) {
                res[idx++] = matrix[top][i];
            }
            if (++top > bottom) {
                break;
            }
            //从上往下走
            for (int i = top; i <= bottom; i++) {
                res[idx++] = matrix[i][right];
            }
            if (--right < left) {
                break;
            }
            //从右往左走
            for (int i = right; i >= left; i--) {
                res[idx++] = matrix[bottom][i];
            }
            if (--bottom < top) {
                break;
            }
            //从下往上走
            for (int i = bottom; i >= top; i--) {
                res[idx++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }

    @Override
    public String minStack(){
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        minStack.push(4);
        minStack.pop();
        System.out.println("top计算结果为:" + minStack.top());
        System.out.println("min计算结果为:" + minStack.min());
        return "success";
    }

    class MinStack{
        private Node head;

        public MinStack() {

        }

        public void push(int x) {

            if (head == null)
                head = new Node(x, x, null);
            else
                head = new Node(x, Math.min(head.min, x), head);
        }

        public void pop() {

            head = head.next;
        }

        public int top() {

            return head.val;
        }

        public int min() {

            return head.min;
        }

        private class Node {

            int val;
            int min;
            Node next;

            public Node(int val, int min, Node next) {

                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }

    @Override
    public String validateStackSequences(){
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,5,3,2,1};
        System.out.println("validateStackSequences计算结果为:" + validateStackSequences(pushed, popped));
        return "success";
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int elem : pushed) {
            stack.push(elem);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == popped.length;
    }

    @Override
    public String levelOrder(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(3);
        System.out.println("levelOrder计算结果为:" + JSON.toJSONString(levelOrder(treeNode)));
        return "success";
    }

    private List<List<Integer>> levelOrderListList = new ArrayList<>();

    private int k = 0;

    public int[] levelOrder(TreeNode root) {
        dfs(root,0);
        int[] res = new int[k];
        int j = 0;
        for(int i = 0; i < levelOrderListList.size(); i++){
            for(int num: levelOrderListList.get(i)){
                res[j] = num;
                j++;
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int depth){
        if(root == null){
            return;
        }

        if(levelOrderListList.size() < depth + 1){
            levelOrderListList.add(new ArrayList<>());
        }
        levelOrderListList.get(depth).add(root.val);
        k++;
        dfs(root.left,depth + 1);
        dfs(root.right,depth + 1);
    }

    @Override
    public String levelOrderTwo(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(3);
        System.out.println("levelOrderTwo计算结果为:" + JSON.toJSONString(levelOrderTwo(treeNode)));
        return "success";
    }

    public List<List<Integer>> levelOrderTwo(TreeNode root) {
        dfs(root,0);
        return levelOrderListList;
    }

    @Override
    public String levelOrderThree(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(-4);
        treeNode.left.right = new TreeNode(3);
        System.out.println("levelOrderThree计算结果为:" + JSON.toJSONString(levelOrderThree(treeNode)));
        return "success";
    }

    public List<List<Integer>> levelOrderThree(TreeNode root) {
        dfs(root,0);
        for(int i = 1; i < levelOrderListList.size(); i += 2){
            Collections.reverse(levelOrderListList.get(i));
        }
        return levelOrderListList;
    }

    @Override
    public String verifyPostOrder(){
        int[] postOrder = new int[]{1,3,2,6,5};
        System.out.println("verifyPostOrder计算结果为:" + JSON.toJSONString(verifyPostorder(postOrder)));
        return "success";
    }

    /**
     * 二叉搜索树中根节点的值大于左子树中的任何一个节点的值，小于右子树中任何一个节点的值，子树也是
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length < 2){
            return true;
        }
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int left, int right){
        if (left >= right){
            // 当前区域不合法的时候直接返回true就好
            return true;
        }

        // 当前树的根节点的值
        int rootValue = postorder[right];

        int k = left;
        while (k < right && postorder[k] < rootValue){
            // 从当前区域找到第一个大于根节点的，说明后续区域数值都在右子树中
            k++;
        }

        for (int i = k; i < right; i++){
            // 进行判断后续的区域是否所有的值都是大于当前的根节点，如果出现小于的值就直接返回false
            if (postorder[i] < rootValue) {
                return false;
            }
        }

        // 当前树没问题就检查左右子树
        if (!verify(postorder, left, k - 1)) {
            // 检查左子树
            return false;
        }
        if (!verify(postorder, k, right - 1)) {
            // 检查右子树
            return false;
        }
        // 最终都没问题就返回true
        return true;
    }

    @Override
    public String pathSum(){
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.left = new TreeNode(5);
        treeNode.right.right.right = new TreeNode(1);
        System.out.println("pathSum计算结果为:" + JSON.toJSONString(pathSum(treeNode, 22)));
        return "success";
    }

    List<Integer> list = new ArrayList<>();
    List<List<Integer>> listList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if(root == null){
            return listList;
        }
        pathSumByList(root, target);
        return listList;
    }

    private void pathSumByList(TreeNode root, int target){
        list.add(root.val);
        if(target == root.val && root.left == null && root.right == null){
            listList.add(new ArrayList<>(list));
        }else{
            if(root.left != null){
                pathSum(root.left, target - root.val);
                list.remove(list.size() - 1);
            }
            if(root.right != null) {
                pathSum(root.right, target - root.val);
                list.remove(list.size() - 1);
            }
        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    @Override
    public String copyRandomList(){
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
        Node node = copyRandomList(node1);
        while(node != null){
            Integer random = node.random == null ? null : node.random.val;
            System.out.println("value:" + node.val + ", random.value:" + random);
            node = node.next;
        }
        return "success";
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 完成链表节点的复制
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.val);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = cur.next.next;
        }

        // 完成链表复制节点的随机指针复制
        cur = head;
        while (cur != null) {
            // 注意判断原来的节点有没有random指针
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 将链表一分为二
        Node copyHead = head.next;
        cur = head;
        Node curCopy = head.next;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            if (curCopy.next != null) {
                curCopy.next = curCopy.next.next;
                curCopy = curCopy.next;
            }
        }
        return copyHead;
    }

    @Override
    public String treeToDoublyList(){
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        System.out.println("treeToDoublyList计算结果为:" + JSON.toJSONString(treeToDoublyList(treeNode)));
        return "success";
    }

    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        // 边界值
        if(root == null) {
            return null;
        }
        dfs(root);

        // 头尾连接
        head.left = pre;
        pre.right = head;
        // 返回头节点
        return head;
    }

    private void dfs(TreeNode cur) {
        // 递归结束条件
        if(cur == null){
            return;
        }
        dfs(cur.left);

        if (pre == null){
            // 如果pre为空，就说明是第一个节点，头结点，然后用head保存头结点，用于之后的返回
            head = cur;
        } else {
            // 如果不为空，那就说明是中间的节点。并且pre保存的是上一个节点，
            // 让上一个节点的右指针指向当前节点
            pre.right = cur;
        }
        // 再让当前节点的左指针指向父节点，也就连成了双向链表
        cur.left = pre;
        // 保存当前节点，用于下层递归创建
        pre = cur;
        dfs(cur.right);
    }

    @Override
    public String serializeTree(){
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        System.out.println("serialize计算结果为:" + JSON.toJSONString(serialize(treeNode)));
        System.out.println("deserialize计算结果为:" + JSON.toJSONString(deserialize("1,2,3,null,null,4,5")));
        return "success";
    }

    public String serialize(TreeNode root) {
        if(root == null){
            return "null,";
        }
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < arr.length; i++){
            queue.offer(arr[i]);
        }
        return help(queue);
    }
    public TreeNode help(Queue<String> queue){
        String val = queue.poll();
        if("null".equals(val)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = help(queue);
        root.right = help(queue);
        return root;
    }

    @Override
    public String permutation(){
        System.out.println("permutation计算结果为:" + JSON.toJSONString(permutation("abc")));
        return "success";
    }

    List<String> permutationList = new ArrayList<>();

    public String[] permutation(String s) {
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        s = new String(chs);

        int len = s.length();
        boolean[] used = new boolean[len];
        StringBuilder path = new StringBuilder();
        dfs(s.toCharArray(), len, used, path);
        return permutationList.toArray(new String[0]);
    }

    private void dfs(char[] c, int len, boolean[] used, StringBuilder path){
        if(path.length() == len){
            permutationList.add(path.toString());
            return;
        }

        for(int i = 0; i < len; i++){
            if(i > 0 && !used[i-1] && c[i] == c[i-1]) continue;

            if(!used[i]){
                path.append(c[i]);
                used[i] = true;
                dfs(c, len, used, path);
                path.deleteCharAt(path.length() - 1);
                used[i] = false;
            }
        }
    }

    @Override
    public String majorityElement(){
        System.out.println("majorityElement计算结果为:" + JSON.toJSONString(majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2})));
        return "success";
    }

    /**
     * 时间O(n)，空间O(1)
     */
    public int majorityElement(int[] nums) {
        int res = 0, count = 0;
        for(int i = 0; i < nums.length; i++){
            if(count == 0){
                res = nums[i];
                count++;
            } else {
                count = res == nums[i] ? count + 1 : count - 1;
            }
        }
        return res;
    }

    @Override
    public String getLeastNumbers(){
        int[] array = getRandomArray();
        System.out.println("getLeastNumbers计算结果为:" + JSON.toJSONString(getLeastNumbers(array, new Random().nextInt(array.length - 1))));
        return "success";
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    /**
     * 获取随机数组
     */
    private int[] getRandomArray(){
        int[] dataArray = new int[10];
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            dataArray[i] = random.nextInt(100);
        }
        return dataArray;
    }

    @Override
    public String medianFinder(){
        MedianFinder medianFinder = new MedianFinder();
        for(int i = 0; i < 10; i++){
            medianFinder.addNum(new Random().nextInt(100));
        }
        medianFinder.findMedian();
        return "success";
    }

    class MedianFinder {
        //大顶
        PriorityQueue<Integer> left;
        //小顶
        PriorityQueue<Integer> right;

        public MedianFinder() {
            left = new PriorityQueue<>((n1, n2) -> n2 - n1);
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            left.add(num);
            right.add(left.poll());
            if(left.size() + 1 < right.size())
                left.add(right.poll());
        }

        public double findMedian() {
            if(right.size() > left.size()){
                return right.peek();
            }
            return (double)(left.peek() + right.peek()) / 2;
        }
    }

    @Override
    public String maxSubArray(){
        int maxSum = maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println("maxSubArray计算结果为:" + maxSum);
        return "success";
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = sum <= 0 ? num : sum + num;
            max = sum > max ? sum : max;
        }
        return max;
    }

    @Override
    public String countDigitOne(){
        System.out.println("countDigitOne计算结果为:" + countDigitOne(121));
        return "success";
    }

    public int countDigitOne(int n) {
        int count = 0;
        long a, b;
        for(long i = 1; i <= n; i *= 10){
            //当前位 + 高位组成的数
            a = n / i;
            //低位数字
            b = n % i;
            //a等于0或1时，高位不需加一；大于1时，需要加一
            //当前位上出现1的数量 + 当前位上为1时,出现1的次数
            count += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return count;
    }

    @Override
    public String findNthDigit(){
        System.out.println("findNthDigit计算结果为:" + findNthDigit(10));
        return "success";
    }

    public int findNthDigit(int n) {
        // n所在数字的位数
        int digit = 1;
        // 数字范围开始的第一个数
        long start = 1;
        // 占多少位
        long count = 9;
        while(n > count){
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    @Override
    public String minNumber(){
        System.out.println("minNumber计算结果为:" + minNumber(new int[]{121, 12}));
        return "success";
    }

    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
        //虽然更简洁, 但性能上相对较差
//        IntStream.of(nums).mapToObj(String::valueOf).sorted(((o1, o2) -> (o1 + o2).compareTo(o2 + o1))).collect(Collectors.joining());
    }

    @Override
    public String translateNum(){
        System.out.println("translateNum计算结果为:" + translateNum(12258));
        return "success";
    }

    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= str.length(); i++) {
            String tmpStr = str.substring(i - 2, i);
            if (tmpStr.compareTo("10") >= 0 && tmpStr.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    @Override
    public String maxValue(){
        System.out.println("maxValue计算结果为:" + maxValue(new int[][]{{1,3,1}, {1,5,1}, {4,2,1}}));
        return "success";
    }

    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //当前最大路径和 = 从左 / 从上过来的路径和中最大值 + 当前值
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[n];
    }

    @Override
    public String lengthOfLongestSubstring(){
        System.out.println("lengthOfLongestSubstring计算结果为:" + lengthOfLongestSubstring("abcabcbbz"));
        return "success";
    }

    public int lengthOfLongestSubstring(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!deque.contains(s.charAt(i))) {
                deque.addLast(s.charAt(i));
            } else {
                max = Math.max(max, deque.size());
                while(deque.peekFirst() != s.charAt(i)) {
                    deque.removeFirst();
                }
                deque.removeFirst();
                deque.addLast(s.charAt(i));
            }
        }
        return Math.max(max, deque.size());
    }

    @Override
    public String nthUglyNumber(){
        System.out.println("nthUglyNumber计算结果为:" + nthUglyNumber(100));
        return "success";
    }

    public int nthUglyNumber(int n) {
        if (n <= 0)
            return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int id2 = 0, id3 = 0, id5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id2] * 2, Math.min(dp[id3] * 3, dp[id5] * 5));
            // 这里不用else if的原因是有可能id2(3) * 2 == id3(2) * 3 这种情况两个指针都要后移
            if (dp[id2] * 2 == dp[i])
                id2 += 1;
            if (dp[id3] * 3 == dp[i])
                id3 += 1;
            if (dp[id5] * 5 == dp[i])
                id5 += 1;
        }
        return dp[n - 1];
    }

    @Override
    public String firstUniqChar(){
        System.out.println("firstUniqChar计算结果为:" + firstUniqChar("abaccdeff"));
        return "success";
    }

    public char firstUniqChar(String s) {
        if (s.equals(""))
            return ' ';
        int[] target = new int[26];
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1)
                return s.charAt(i);
        }
        return ' ';
    }

    @Override
    public String reversePairs(){
        System.out.println("reversePairs计算结果为:" + reversePairs(new int[]{7,5,6,4}));
        return "success";
    }

    private int cnt = 0;
    private int[] temp;

    public int reversePairs(int[] nums) {
        int n = nums.length;
        temp = new int[n];
        mergeSort(nums, 0, n - 1);
        return cnt;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // 复制一下，准备归并
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        // 修改原数组，归并 temp
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] > temp[j]) {
                arr[k] = temp[j++];
                // mid 前有多少个 temp[j] 比当前 temp[i] 小
                cnt += mid - i + 1;
            } else {
                arr[k] = temp[i++];
            }
            k++;
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }

    @Override
    public String getIntersectionNode(){
        ListNode listNode5 = new ListNode(4, null);
        ListNode listNode4 = new ListNode(2, listNode5);
        ListNode listNode3 = new ListNode(1, listNode4);
        ListNode listNode2 = new ListNode(9, listNode3);
        ListNode listNode1 = new ListNode(0, listNode2);

        ListNode listNode11 = new ListNode(3, listNode4);
        System.out.println("getIntersectionNode计算结果为:" + getIntersectionNode(listNode1, listNode11));
        return "success";
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode n1 = headA;
        ListNode n2 = headB;

        while(n1 != n2){
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }
        return n1;
    }

    @Override
    public String search(){
        System.out.println("getIntersectionNode计算结果为:" + search(new int[]{5,7,7,8,8,10}, 8));
        return "success";
    }

    public int search(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        int count = 0;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] >= target)
                right = mid;
            if(nums[mid] < target)
                left = mid + 1;
        }
        while(left < nums.length && nums[left++] == target)
            count++;
        return count;
    }

    @Override
    public String missingNumber(){
        System.out.println("missingNumber计算结果为:" + missingNumber(new int[]{0,1,2,3,4,5,6,7,9}));
        return "success";
    }

    public int missingNumber(int[] nums) {
        int len = nums.length;
        return recur(nums,0,len - 1);
    }

    private int recur(int[] nums, int first, int last){
        if(first>last){
            return first;
        }
        int mid = (first + last) / 2;
        if(nums[mid] == mid)
            return recur(nums, mid + 1, last);
        else
            return recur(nums, first, mid - 1);
    }

    @Override
    public String kthLargest(){
        TreeNode treeNode = new TreeNode(3);
//        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
//        treeNode.left.right = new TreeNode(2);
        System.out.println("kthLargest计算结果为:" + kthLargest(treeNode, 2));
        return "success";
    }

    public int kthLargest(TreeNode root, int k) {
        kthLargestK = k;
        dfs1(root);
        return kthLargest;
    }

    private int kthLargest;
    private int kthLargestK;

    private void dfs1(TreeNode root) {
        if(root.right != null){
            dfs1(root.right);
        }

        if(kthLargest == 0 && kthLargestK <= 1){
            kthLargest = root.val;
            return;
        }else{
            kthLargestK--;
        }

        if(root.left != null){
            dfs1(root.left);
        }
    }

    @Override
    public String maxDepth(){
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.right = new TreeNode(2);
        System.out.println("maxDepth计算结果为:" + maxDepth(treeNode));
        return "success";
    }

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

//    private int maxDepth = 0;
//
//    public int maxDepth(TreeNode root) {
//        if(root == null){
//            return maxDepth;
//        }
//        maxDepth1(root, 0);
//        return maxDepth;
//    }
//
//    public void maxDepth1(TreeNode root, int maxDepth) {
//        maxDepth++;
//        if(root.left != null){
//            maxDepth1(root.left, maxDepth);
//        }
//        if(root.right != null){
//            maxDepth1(root.right, maxDepth);
//        }
//        if(root.left == null && root.right == null && this.maxDepth < maxDepth){
//            this.maxDepth = maxDepth;
//        }
//    }

    @Override
    public String isBalanced(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(3);
        treeNode.left.left.left = new TreeNode(4);
        treeNode.left.left.right = new TreeNode(4);
        treeNode.left.right.left = new TreeNode(4);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right.right.left = new TreeNode(4);
        treeNode.left.left.left.left = new TreeNode(5);
        treeNode.left.left.left.right = new TreeNode(5);
        System.out.println("isBalanced计算结果为:" + isBalanced(treeNode));
        return "success";
    }

    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        high(root);
        return res;
    }

    public int high(TreeNode node) {
        if (node != null) {
            int leftHigh = high(node.left) + 1;
            int rightHigh = high(node.right) + 1;
            if (Math.abs(leftHigh - rightHigh) > 1) {
                res = false;
            }
            return Math.max(leftHigh, rightHigh);
        }
        return 0;
    }

    @Override
    public String singleNumbers(){
        System.out.println("singleNumbers计算结果为:" + JSONObject.toJSONString(singleNumbers(new int[]{4,2,4,6})));
        return "success";
    }

    public int[] singleNumbers(int[] nums) {
        // 用于记录 A B 的异或结果
        int x = 0;

        // 得到A^B的结果
        // 对于任何数x，都有x^x=0，x^0=x
        for (int val : nums) {
            x ^= val;
        }

        // x & (-x)本身的作用是得到最低位的1，
        int flag = x & (-x);

        // 而我们所需要的做到的是：利用这个1来进行分组，也就是做到将A和B区分开
        // 前面已经知道，x是我们需要的结果数A和B相异或的结果，也就是说，x的二进制串上的任何一个1，都能成为区分A和B的条件
        // 因此我们只需要得到x上的任意一个1，就可以做到将A和B区分开来

        // 用于记录A或B其中一者
        int res = 0;

        // 分组操作
        for (int val : nums) {
            // 根据二进制位上的那个“1”进行分组
            // 需要注意的是，分组的结果必然是相同的数在相同的组，且还有一个结果数
            // 因此每组的数再与res=0一路异或下去，最终会得到那个结果数A或B
            // 且由于异或运算具有自反性，因此只需得到其中一个数即可
            if ((flag & val) != 0) {
                res ^= val;
            }
        }
        // 利用先前的x进行异或运算得到另一个，即利用自反性
        return new int[] {res, x ^ res};
    }

    @Override
    public String singleNumber(){
        System.out.println("singleNumbers计算结果为:" + JSONObject.toJSONString(singleNumber(new int[]{4,2,4,6})));
        return "success";
    }

    public int singleNumber(int[] nums) {
        // 可以设计一种逻辑，使数字出现 3 次时，该逻辑的结果为 0（即只有 0，1，2 三种状态）
        // 其实就是一个 三进制
        // 一位二进制数只能存储 0 和 1 两种状态，所以我们需要用到两位二进制
        // 设两位二进制数的高位为 A，低位为 B。C 是输入变量
        // 表示的三种情况为 ： 0次：00(A=0,B=0), 1次：01(A=0,B=1), 2次：10(A=1,B=0)
        // 注：11(A=1,B=1) 为无效输入

        // 画出关于 A 的卡诺图（AB为11的结果是不重要的，用 x 表示）：
        //  AB\C |  0  |  1
        //  =================
        //    00 |  0  |  0
        //    01 |  0  |  1        ====> 得到 A = BC + AC'
        //    11 |  x  |  x
        //    10 |  1  |  0

        //  画出关于 B 的卡诺图
        //  AB\C |  0  |  1
        //  =================
        //    00 |  0  |  1
        //    01 |  1  |  0        ====> 得到 B = BC' + A'B'C
        //    11 |  x  |  x
        //    10 |  0  |  0

        // 很明显啊，我们需要的就是只出现一次的情况 01（A=0，B=1），即 B 的结果
        int A = 0, B = 0;
        for (int C : nums) {
            int tmp = A;
            A = (B & C) | (A & ~C);
            B = (B & ~C) | (~tmp & ~B & C);
        }
        return B;
    }

    @Override
    public String twoSum(){
        System.out.println("twoSum计算结果为:" + JSONObject.toJSONString(twoSum(new int[]{2,7,11,15}, 9)));
        return "success";
    }

    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length <= 1){
            return null;
        }
        int start = 0, end = nums.length - 1;
        while (start < end){
            if(nums[start] + nums[end] < target){
                start++;
            }else if(nums[start] + nums[end] > target){
                end--;
            }else{
                return new int[]{nums[start], nums[end]};
            }
        }
        return null;
    }

    @Override
    public String findContinuousSequence(){
        System.out.println("findContinuousSequence计算结果为:" + JSONObject.toJSONString(findContinuousSequence(15)));
        return "success";
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();

        //要有一个区间的概念，这里的区间是(1, 2, 3, ..., target - 1)
        //套滑动窗口模板，l是窗口左边界，r是窗口右边界，窗口中的值一定是连续值。
        //当窗口中数字和小于target时，r右移; 大于target时，l右移; 等于target时就获得了一个解
        for (int l = 1, r = 1, sum = 0; r < target; r++) {
            sum += r;
            while (sum > target) {
                sum -= l++;
            }
            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = l + i;
                }
                list.add(temp);
            }
        }

        int[][] res = new int[list.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    @Override
    public String reverseWords(){
        System.out.println("reverseWords计算结果为:" + JSONObject.toJSONString(reverseWords("the sky is blue")));
        return "success";
    }

    public String reverseWords(String s) {
        s = s.trim();
        int i = s.length() - 1, j = i;
        StringBuilder stringBuilder = new StringBuilder();
        while( i >= 0){
            while(i >= 0 && s.charAt(i) != ' '){
                i--;
            }
            stringBuilder.append(s,i+1,j+1).append(" ");
            while(i >= 0 && s.charAt(i) == ' '){
                i--;
            }
            j = i;
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String reverseLeftWords() {
        System.out.println("reverseWords计算结果为:" + JSONObject.toJSONString(reverseLeftWords("abcdefg", 2)));
        return "success";
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    @Override
    public String maxQueue(){
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        return "success";
    }

    static class MaxQueue {
        static class Node{
            int value;
            Node next = null;

            Node(int value){
                this.value = value;
            }
        }

        int max; // 当前队列中的最大值
        int maxCount; // 应对 max 有多个的情况
        Node head = null, tail = null; // 头尾节点

        public MaxQueue() {
        }

        public int max_value() {
            if(head == null) return -1;
            return max;
        }

        public void push_back(int value) {
            // 入队时统计最大值和最大值出现的次数
            if(value > max){
                max = value;
                maxCount = 1;
            }else if(value == max){
                maxCount++;
            }
            // 入队
            if(head == null) head = tail = new Node(value);
            else tail = tail.next = new Node(value);
        }

        public int pop_front() {
            if(head == null) return -1;
            // 出队
            int value = head.value;
            head = head.next;

            if(value == max && --maxCount == 0){
                // 全部 max 值已出队，需要重新计算 max 和 maxCount
                // 重新计算 max 和 maxCount 的时间复杂度是 O(n)
                // 但是队列中的数据都是随机的，则可认为当前出队元素是最大值的概率为 1/n
                // 则 均摊时间 = O(1) * (n-1)/n + O(n) * 1/n
                //            = O(1) * (n-1)/n + n*O(1) * 1/n
                //            = (2*n-1)/n * O(1)
                //            = (2 - 1/n) * O(1)
                // (2 - 1/n) < 2, 故均摊时间复杂度为 O(1)

                max = -1;
                for (Node node = head; node != null; node = node.next) {
                    if(node.value > max){
                        max = node.value;
                        maxCount = 1;
                    }
                    else if(node.value == max) maxCount++;
                }
            }
            return value;
        }
    }

    @Override
    public String dicesProbability(){
        System.out.println("dicesProbability计算结果为:" + JSONObject.toJSONString(dicesProbability(3)));
        return "success";
    }

    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    @Override
    public String isStraight(){
        System.out.println("isStraight计算结果为:" + JSONObject.toJSONString(isStraight(new int[]{0,0,1,2,5})));
        return "success";
    }

    public boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums);
        for(int i = 0; i < 4; i++) {
            if(nums[i] == 0) {
                joker++;
            } else if(nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }
}















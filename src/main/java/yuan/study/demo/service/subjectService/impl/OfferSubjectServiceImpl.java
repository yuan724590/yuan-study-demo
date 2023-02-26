package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
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
        for(int i = 1;i <= n; ++i){//总位数
            for(char j = '1'; j <='9'; ++j){
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

    private void mirrorTree(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode right = root.right;
        root.right = root.left;
        root.left = right;
        mirrorTree(root.left);
        mirrorTree(root.right);
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
}
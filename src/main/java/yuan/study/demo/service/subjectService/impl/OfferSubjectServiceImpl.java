package yuan.study.demo.service.subjectService.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.ListNode;
import yuan.study.demo.service.subjectService.OfferSubjectService;

import java.util.Arrays;
import java.util.LinkedList;

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
}
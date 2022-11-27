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
}
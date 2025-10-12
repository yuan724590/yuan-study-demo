package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.subjectService.InterviewService;

import java.util.*;


@Slf4j
@Service
public class InterviewServiceImpl implements InterviewService {

    @Override
    public String isUnique(){
        System.out.println(JSON.toJSONString(isUnique("abc")));
        return "success";
    }

    public static boolean isUnique(String astr) {
        int aa = 0;
        int cc = 1;
        for (int i = 0; i < astr.length(); i++) {
            //通过向左移动的位数来记录字母的序号
            int bb = cc << (astr.charAt(i) - 'a');
            if ((aa & bb) != 0) {
                return false;
            }
            aa |= bb;
        }
        return true;
    }

    @Override
    public String checkPermutation(){
        System.out.println(JSON.toJSONString(CheckPermutation("abc", "bca")));
        return "success";
    }

    public boolean CheckPermutation(String s1, String s2) {
        int n = s1.length(), m = s2.length(), diff = 0;
        if (n != m) {
            return false;
        }
        int[] arr = new int[256];
        for (int i = 0; i < n; i++) {
            if (++arr[s1.charAt(i)] == 1) {
                diff++;
            }
            if (--arr[s2.charAt(i)] == 0) {
                diff--;
            }
        }
        return diff == 0;
    }

    @Override
    public String replaceSpaces(){
        System.out.println(JSON.toJSONString(replaceSpaces("Mr John Smith    ", 13)));
        return "success";
    }

    public String replaceSpaces(String S, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++){
            char c = S.charAt(i);
            if(' ' == c){
                stringBuilder.append("%20");
            }else{
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String canPermutePalindrome(){
        System.out.println(JSON.toJSONString(canPermutePalindrome("AaBb//a")));
        return "success";
    }

    public boolean canPermutePalindrome(String s) {
        int[] arr = new int[128];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)]++;
        }
        boolean single = false;
        for (int i : arr) {
            if(i % 2 == 0){
                continue;
            }
            if(!single){
                single = true;
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public String oneEditAway(){
        System.out.println(JSON.toJSONString(oneEditAway("ab", "bc")));
        return "success";
    }

    public boolean oneEditAway(String a, String b) {
        int n = a.length(), m = b.length();
        if (Math.abs(n - m) > 1) {
            return false;
        }
        if (n > m) {
            return oneEditAway(b, a);
        }
        int i = 0, j = 0, cnt = 0;
        while (i < n && j < m && cnt <= 1) {
            char c1 = a.charAt(i), c2 = b.charAt(j);
            if (c1 == c2) {
                i++;
                j++;
            } else {
                if (n == m) {
                    i++;
                }
                j++;
                cnt++;
            }
        }
        return cnt <= 1;
    }

    @Override
    public String compressString(){
        System.out.println(JSON.toJSONString(compressString("aabcccccaaa")));
        return "success";
    }

    public String compressString(String s) {
        int n = s.length(), num = 1;
        if(n == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if(c == s.charAt(i - 1)){
                num++;
            }else{
                sb.append(num);
                sb.append(c);
                num = 1;
            }
        }
        sb.append(num);
        return sb.length() >= n ? s : sb.toString();
    }

    @Override
    public String isFlipedString(){
        System.out.println(JSON.toJSONString(isFlipedString("waterbottle", "erbottlewat")));
        return "success";
    }

    public boolean isFlipedString(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        String s = s2 + s2;
        return s.contains(s1);
    }

    @Override
    public String removeDuplicateNodes(){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(2);
        listNode.next.next.next.next.next = new ListNode(1);
        System.out.println(JSON.toJSONString(removeDuplicateNodes(listNode)));
        return "success";
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<Integer> occurred = new HashSet<>();
        occurred.add(head.val);
        ListNode copy = head;
        while (copy.next != null) {
            if (occurred.add(copy.next.val)) {
                copy = copy.next;
            } else {
                copy.next = copy.next.next;
            }
        }
        return head;
    }

    @Override
    public String kthToLast(){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(JSON.toJSONString(kthToLast(listNode, 2)));
        return "success";
    }

    public int kthToLast(ListNode head, int k) {
        ListNode listNode = head;
        for (int i = 1; i < k; i++) {
            if(listNode == null){
                return -1;
            }
            listNode = listNode.next;
        }
        while(listNode.next != null){
            listNode = listNode.next;
            head = head.next;
        }
        return head.val;
    }

    @Override
    public String deleteNode(){
        ListNode listNode = new ListNode(4);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(1);
        listNode.next.next.next = new ListNode(9);
        deleteNode(listNode);
        System.out.println(JSON.toJSONString(listNode));
        return "success";
    }

    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

    @Override
    public String addTwoNumbers(){
        ListNode listNode = new ListNode(7);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(6);
        ListNode listNode1 = new ListNode(5);
        listNode1.next = new ListNode(9);
        listNode1.next.next = new ListNode(2);
        System.out.println(JSON.toJSONString(addTwoNumbers(listNode, listNode1)));
        return "success";
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode copy = listNode;
        int a = 0;
        while(l1 != null && l2 != null){
            int v = l1.val + l2.val + a;
            listNode.next = new ListNode(v % 10);
            a = v / 10;
            l1 = l1.next;
            l2 = l2.next;
            listNode = listNode.next;
        }
        while(l1 != null){
            int v = l1.val + a;
            listNode.next = new ListNode(v % 10);
            a = v / 10;
            l1 = l1.next;
            listNode = listNode.next;
        }
        while(l2 != null){
            int v = l2.val + a;
            listNode.next = new ListNode(v % 10);
            a = v / 10;
            l2 = l2.next;
            listNode = listNode.next;
        }
        if(a != 0){
            listNode.next = new ListNode(a);
        }
        return copy.next;
    }

    @Override
    public String isPalindrome(){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(2);
        listNode.next.next.next.next = new ListNode(1);
        System.out.println(JSON.toJSONString(isPalindrome(listNode)));
        return "success";
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }
        //快慢指针寻找中间节点
        ListNode midNode = findMidNode(head);
        ListNode secondHalfHead = reverseLinked(midNode.next);
        ListNode curr1 = head;
        ListNode curr2 = secondHalfHead;

        boolean palindrome = true;
        while(palindrome && curr2 != null){
            if(curr1.val != curr2.val) palindrome = false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return palindrome;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode reverseLinked(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        while(cur != null){
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }

    /**
     * 快慢指针寻找中间节点
     * @param head
     * @return
     */
    private ListNode findMidNode(ListNode head){
        ListNode fast = head;
        ListNode low = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            low = low.next;
        }
        return low;
    }

    @Override
    public String tripleInOne(){
        TripleInOne tripleInOne = new TripleInOne(1);
        tripleInOne.push(0, 1);
        tripleInOne.push(0, 2);
        tripleInOne.push(0, 3);
        System.out.println(tripleInOne.pop(0));
        System.out.println(tripleInOne.peek(0));
        System.out.println(tripleInOne.isEmpty(0));
        return "success";
    }

    class TripleInOne {
        int n = 3;
        int[] data;
        int[] locations;
        int size;
        public TripleInOne(int stackSize) {
            size = stackSize;
            data = new int[size * n];
            locations = new int[n];
            for (int i = 0; i < n; i++) {
                locations[i] = i * size;
            }
        }

        public void push(int stackNum, int value) {
            int idx = locations[stackNum];
            if (idx < (stackNum + 1) * size) {
                data[idx] = value;
                locations[stackNum]++;
            }
        }

        public int pop(int stackNum) {
            int idx = locations[stackNum];
            if (idx > stackNum * size) {
                locations[stackNum]--;
                return data[idx - 1];
            } else {
                return -1;
            }
        }

        public int peek(int stackNum) {
            int idx = locations[stackNum];
            if (idx > stackNum * size) {
                return data[idx - 1];
            } else {
                return -1;
            }
        }

        public boolean isEmpty(int stackNum) {
            return locations[stackNum] == stackNum * size;
        }
    }

    @Override
    public String stackOfPlates(){
        StackOfPlates stackOfPlates = new StackOfPlates(2);
        stackOfPlates.push(1);
        stackOfPlates.push(2);
        stackOfPlates.push(3);
        System.out.println(stackOfPlates.popAt(0));
        System.out.println(stackOfPlates.pop());
        System.out.println(stackOfPlates.pop());
        return "success";
    }

    class StackOfPlates {

        private List<Stack<Integer>> stackList;
        private int cap;

        public StackOfPlates(int cap) {
            stackList = new ArrayList<>();
            this.cap = cap;
        }

        public void push(int val) {
            if (cap <= 0) {
                return;
            }

            if (stackList.isEmpty() || stackList.get(stackList.size() - 1).size() == cap) {
                Stack<Integer> stack = new Stack<>();
                stack.push(val);
                stackList.add(stack);
                return;
            }

            stackList.get(stackList.size() - 1).push(val);
        }

        public int pop() {
            return popAt(stackList.size() - 1);
        }

        public int popAt(int index) {
            if (index < 0 || index >= stackList.size()) {
                return -1;
            }

            Stack<Integer> stack = stackList.get(index);
            if (stack.isEmpty()) {
                return -1;
            }

            int res = stack.pop();

            if (stack.isEmpty()) {
                stackList.remove(index);
            }

            return res;
        }
    }

    @Override
    public String sortedStack(){
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(42);
        sortedStack.push(8);
        sortedStack.push(29);
        sortedStack.push(25);
        sortedStack.pop();
        sortedStack.pop();
        sortedStack.push(52);
        sortedStack.push(63);
        sortedStack.pop();
        sortedStack.push(47);
        sortedStack.pop();
        sortedStack.push(45);
        sortedStack.push(52);
        sortedStack.pop();
        sortedStack.pop();
        sortedStack.push(17);
        sortedStack.pop();
        sortedStack.push(6);
        sortedStack.push(30);
        sortedStack.push(51);
        sortedStack.push(46);
        sortedStack.push(2);
        sortedStack.push(56);
        sortedStack.push(39);
        sortedStack.push(38);
        return "success";
    }

    class SortedStack {

        Stack<Integer> stack;
        Stack<Integer> stack2;

        public SortedStack() {
            stack = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int val) {
            if(stack.isEmpty() || stack.peek() > val){
                stack.push(val);
                return;
            }
            while(!stack.isEmpty() && stack.peek() < val){
                stack2.push(stack.pop());
            }
            stack.push(val);
            while(!stack2.isEmpty()){
                stack.push(stack2.pop());
            }
        }

        public void pop() {
            if(!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int peek() {
            if(stack.isEmpty()) {
                return -1;
            }
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    @Override
    public String animalShelf(){
        AnimalShelf animalShelf = new AnimalShelf();
        animalShelf.enqueue(new int[]{0, 1});
        animalShelf.enqueue(new int[]{1, 0});
        System.out.println(JSONObject.toJSONString(animalShelf.dequeueCat()));
        System.out.println(JSONObject.toJSONString(animalShelf.dequeueDog()));
        System.out.println(JSONObject.toJSONString(animalShelf.dequeueAny()));
        return "";
    }

    static class AnimalShelf {

        LinkedList<int[]> queueCat;
        LinkedList<int[]> queueDog;

        public AnimalShelf() {
            queueCat = new LinkedList<>();
            queueDog = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            if (animal[1] == 0) {
                queueCat.addLast(animal);
            } else if (animal[1] == 1) {
                queueDog.addLast(animal);
            }
        }

        public int[] dequeueAny() {
            // 取出cat的队首，判空则直接返回
            int[] headCat;
            if (!queueCat.isEmpty()) {
                headCat = queueCat.getFirst();
            } else if (!queueDog.isEmpty()) {
                return queueDog.removeFirst();
            } else {
                return new int[]{-1,-1};
            }
            // 取出dog的队首，判空则直接返回
            int[] headDog;
            if (!queueDog.isEmpty()) {
                headDog = queueDog.getFirst();
            } else {
                return queueCat.removeFirst();
            }
            // 比较后返回
            if (headCat[0]<=headDog[0]) {
                return queueCat.removeFirst();
            } else {
                return queueDog.removeFirst();
            }
        }

        public int[] dequeueDog() {
            if (!queueDog.isEmpty()) {
                return queueDog.removeFirst();
            } else {
                return new int[]{-1,-1};
            }
        }

        public int[] dequeueCat() {
            if (!queueCat.isEmpty()) {
                return queueCat.removeFirst();
            } else {
                return new int[]{-1,-1};
            }
        }
    }

    @Override
    public String findWhetherExistsPath(){
        System.out.println(JSON.toJSONString(findWhetherExistsPath(4, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 0}, {3, 2}}, 0, 3)));
        return "success";
    }

    private boolean[] findWhetherExistsPathVisited = null;
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        findWhetherExistsPathVisited = new boolean[graph.length];
        return findWhetherExistsPath(graph, start, target);
    }

    private boolean findWhetherExistsPath(int[][] graph, int start, int target) {
        for (int i = 0; i < graph.length; ++i) {
            if (findWhetherExistsPathVisited[i]) {
                continue;
            }
            //如果当前节点的起点等于start，终点等于target，直接返回true
            if (graph[i][0] == start && graph[i][1] == target) {
                return true;
            }
            findWhetherExistsPathVisited[i] = true;
            //如果当前指向终点 && 有路径可以指向当前来源
            if (graph[i][1] == target && findWhetherExistsPath(graph, start, graph[i][0])) {
                return true;
            }
            findWhetherExistsPathVisited[i] = false;
        }
        return false;
    }

    @Override
    public String listOfDepth(){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2, new TreeNode(4, new TreeNode(8), null), new TreeNode(5));
        treeNode.right = new TreeNode(3, null, new TreeNode(7));
        System.out.println(JSON.toJSONString(listOfDepth(treeNode)));
        return "success";
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        Map<Integer, ListNode> map = new HashMap<>();
        listOfDepth(tree, map, 0);
        return map.values().toArray(new ListNode[0]);
    }

    private void listOfDepth(TreeNode tree, Map<Integer, ListNode> map, int deep){
        if(tree == null){
            return;
        }
        listOfDepth(tree.left, map, deep + 1);

        if(map.containsKey(deep)){
            ListNode listNode = map.get(deep);
            while(listNode.next != null){
                listNode = listNode.next;
            }
            listNode.next = new ListNode(tree.val);
        }else{
            map.put(deep, new ListNode(tree.val));
        }

        listOfDepth(tree.right, map, deep + 1);
    }

    @Override
    public String inorderSuccessor(){
//        TreeNode root = new TreeNode(5, null, new TreeNode(6));
//        root.left = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4));
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(JSON.toJSONString(inorderSuccessor(root, new TreeNode(1))));
        return "success";
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode ans = inorderSuccessor(root.left, p);
        return ans == null ? root : ans;
    }

    @Override
    public String lowestCommonAncestor(){
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4)));
        treeNode.right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        System.out.println(JSON.toJSONString(lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(1))));
        return "success";
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    @Override
    public String BSTSequences(){
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.left.right.left = new TreeNode(2);
        System.out.println(JSON.toJSONString(BSTSequences(treeNode)));
        return "success";
    }

    private List<List<Integer>> BSTSequencesAns;

    public List<List<Integer>> BSTSequences(TreeNode root) {
        BSTSequencesAns = new ArrayList<>();
        List<Integer> pathList = new ArrayList<>();
        // 如果 root==null 返回 [[]]
        if (root == null) {
            BSTSequencesAns.add(pathList);
            return BSTSequencesAns;
        }
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 开始进行回溯
        BSTSequencesBfs(queue, pathList);
        return BSTSequencesAns;
    }

    /**
     * 回溯法+广度优先遍历
     */
    private void BSTSequencesBfs(List<TreeNode> queue, List<Integer> pathList) {
        // queue 为空说明遍历完了，可以返回了
        if (queue.isEmpty()) {
            BSTSequencesAns.add(new ArrayList<>(pathList));
            return;
        }
        // 将 queue 拷贝一份，用于稍后回溯
        List<TreeNode> copy = new ArrayList<>(queue);
        // 对 queue 进行循环，每循环考虑 “是否 「将当前 cur 节点从 queue 中取出并将其左右子
        // 节点加入 queue ，然后将 cur.val 加入到 path 末尾」 ” 的情况进行回溯
        for (int i = 0; i < queue.size(); i++) {
            TreeNode cur = queue.get(i);
            pathList.add(cur.val);
            queue.remove(i);
            // 将左右子节点加入队列
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            BSTSequencesBfs(queue, pathList);
            // 恢复 path 和 queue ，进行回溯
            pathList.remove(pathList.size() - 1);
            queue = new ArrayList<>(copy);
        }
    }

    @Override
    public String checkSubTree(){
        TreeNode treeNode1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode treeNode2 = new TreeNode(2);
        checkSubTree(treeNode1, treeNode2);
        System.out.println(JSON.toJSONString(checkSubTree(treeNode1, treeNode2)));
        return "success";
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        return checkSubTreeEqual(t1, t2) || (t1 != null && (checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2)));
    }

    private boolean checkSubTreeEqual(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null){
            return true;
        }else if(t1 == null || t2 == null){
            return false;
        }
        return t1.val == t2.val && checkSubTreeEqual(t1.left, t2.left) && checkSubTreeEqual(t1.right, t2.right);
    }

    @Override
    public String insertBits(){
        System.out.println(JSON.toJSONString(insertBits(1024, 19, 2, 6)));
        return "success";
    }

    public int insertBits(int N, int M, int i, int j) {
        int mask = ((1 << (j - i + 1)) - 1) << i;
        mask =~ mask;
        N &= mask;
        M = M << i;
        return M | N;
    }

    @Override
    public String printBin(){
        System.out.println(JSON.toJSONString(printBin(0.625)));
        return "success";
    }

    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < 6; i++) {
            num = num * 2;
            if(num < 1){
                sb.append("0");
            }else{
                sb.append("1");
                if(--num == 0){
                    return sb.toString();
                }
            }
        }
        return "ERROR";
    }

    public String reverseBits(){
        System.out.println(JSON.toJSONString(reverseBits(-1)));
        return "success";
    }

    public int reverseBits(int num) {
        int max = 0;
        int reverse = 0;
        int cur = 0;
        for(int i = 0; i < 32; i++){
            if((num & 1) == 1){
                cur++;
                reverse++;
            }else{
                reverse = cur + 1;
                cur = 0;
            }
            if(reverse > max) {
                max = reverse;
            }
            num >>= 1;
        }
        return max;
    }

    public String findClosedNumbers(){
        System.out.println(JSON.toJSONString(findClosedNumbers(34)));
        return "success";
    }

    public int[] findClosedNumbers(int num) {
        int[] res =  new int[2];
        if (num <= 0) {
            res[0] = -1;
            res[1] = -1;
            return res;
        }
        res[0] = getNext(num);
        res[1] = getPrev(num);
        return res;
    }

    /**
     * 取得后一个较大的数
     * @param num
     * @return
     */
    private int getNext(int num) {
        // c0 二进制中最后一个1之后0的数量
        // c1 二进制中最后一个1往左连续1的数量
        int n = num, c0 = 0, c1 = 0;
        while ((n & 1) == 0 && n != 0) {
            c0++;
            n >>= 1;
        }
        while ((n & 1) == 1) {
            c1++;
            n >>= 1;
        }

        // 现在已经是1111..000... 没有更大的数字
        int p = c0 + c1;
        if (p == 31) {
            return -1;
        }

        // 在连续1之前拼1
        num |= (1 << p);
        // 将p右方的所有位清零
        num &= -(1 << p);
        // 在右方插入 (c1 - 1) 个1
        num |= (1 << (c1 - 1)) - 1;
        return num;
    }

    /**
     * 取得前一个较小的数
     */
    private int getPrev(int num) {
        // c1 二进制中最后一个0之后1的数量
        // c0 二进制中最后一个0往左连续0的数量
        int n = num, c0 = 0, c1 = 0;
        while ((n & 1) == 1) {
            c1++;
            n >>= 1;
        }

        if (n == 0) {
            return -1;
        }

        while ((n & 1) == 0 && n != 0) {
            c0++;
            n >>= 1;
        }

        int p = c0 + c1;
        // 将位0到位p清零
        num &= ((~0) << (p + 1));
        // 在右方插入 (c1 + 1) 个1
        int mask = (1 << (c1 + 1)) - 1;
        // 将连续的1放到最左侧
        num |= mask << (c0 - 1);
        return num;
    }

    @Override
    public String convertInteger(){
        System.out.println(JSON.toJSONString(convertInteger(826966453, -729934991)));
        return "success";
    }

    public int convertInteger(int A, int B) {
        int temp = A ^ B;
        int count = 0;
        while (temp != 0) {
            // 去掉二进制表示的最右边的1
            temp &= (temp - 1);
            count++;
        }
        return count;
    }

    @Override
    public String exchangeBits(){
        System.out.println(JSON.toJSONString(exchangeBits(3)));
        return "success";
    }

    /**
     * 0x55555555 = 0b0101_0101_0101_0101_0101_0101_0101_0101
     * 0xaaaaaaaa = 0b1010_1010_1010_1010_1010_1010_1010_1010
     */
    public int exchangeBits(int num) {
        //奇数
        int odd = num & 0x55555555;
        //偶数
        int even = num & 0xaaaaaaaa;
        odd = odd << 1;
        even = even >>> 1;
        return odd | even;
    }

    @Override
    public String drawLine(){
        System.out.println(JSON.toJSONString(drawLine(3, 96, 0, 95, 0)));
        return "success";
    }

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int low = (y * w + x1) / 32;
        int high = (y * w + x2) / 32;
        for (int i = low; i <= high; i++) {
            ans[i] = -1;
        }
        ans[low] = ans[low] >>> x1 % 32;
        ans[high] = ans[high] & Integer.MIN_VALUE >> x2 % 32;
        return ans;
    }

    @Override
    public String waysToStep(){
        System.out.println(JSON.toJSONString(waysToStep(3)));
        return "success";
    }

    public int waysToStep(int n) {
        if(n < 3){
            return n;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007;
        }
        return ((int) (dp[n]));
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

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class ListNode {

        int val;

        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
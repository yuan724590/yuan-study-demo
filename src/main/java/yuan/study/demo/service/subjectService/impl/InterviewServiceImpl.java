package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.ListNode;
import yuan.study.demo.service.subjectService.InterviewService;

import java.util.HashSet;
import java.util.Set;


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










































}
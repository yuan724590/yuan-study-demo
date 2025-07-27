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













































}
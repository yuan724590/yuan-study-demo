package yuan.study.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.SubjectService;

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
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
        System.out.println(findMedianSortedArrays1(new int[]{1,2}, new int[]{3,4}));
    }
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[]nums3 = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums3, 0, nums1.length);
        System.arraycopy(nums2, 0, nums3, nums1.length, nums2.length);
        Arrays.sort(nums3);
        return nums3.length % 2 == 0 ? (double)(nums3[nums3.length / 2 - 1] + nums3[nums3.length / 2]) / 2 : nums3[nums3.length / 2];
    }
    /**
     * 高级算法
     */
    private double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            //保证nums1是较长的数组
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        int i, j, maxLeft, minRight;
        while (iMin <= iMax) {
            i = (iMin + iMax) / 2;
            j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]){
                iMin = i + 1; // i is too small
            } else if (i > iMin && nums1[i-1] > nums2[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ( (m + n) % 2 == 1 ) {
                    return maxLeft;
                }
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

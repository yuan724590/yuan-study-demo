package yuan.study.demo.service.subjectService.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.subjectService.InterviewService;


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















































}
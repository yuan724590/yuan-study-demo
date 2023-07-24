package yuan.study.demo.service.subjectService.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.subjectService.NowCoderHJService;
import java.io.*;

@Slf4j
@Service
public class NowCoderHJServiceImpl implements NowCoderHJService {

    @Override
    public void randomNumber() throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = bf.readLine()) != null) {
            boolean[] stu = new boolean[1001];
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(str);
            for(int i = 0; i < n; i++){
                stu[Integer.parseInt(bf.readLine())] = true;
            }
            for(int i = 0; i < 1001; i++){
                if(stu[i]){
                    sb.append(i).append("\n");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }

    @Override
    public void decimalConversion() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(Long.parseLong(line.substring(2) , 16));
        }
    }

    @Override
    public void classificationStatistics() throws Exception{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int a = 0, b = 0, c = 0, d = 0, e = 0, err = 0, pri = 0;
        while((str = buffer.readLine())!=null){
            int index = str.indexOf('~');
            long num1 = strToInt(str.substring(0, index));
            long num2 = strToInt(str.substring(index + 1));
            long t = num1 >> 24;
            if(t == 0 || t == 127) {
                continue;
            }
            if(num2 <= 0 || num2 >= 0XFFFFFFFFL || (((num2 ^ 0XFFFFFFFFL) + 1) | num2) != num2){
                err++;
                continue;
            }
            if(t <= 0){
                err++;
            } else if(t <= 126){
                a++;
                if(t == 10) {
                    pri++;
                }
            }else if(t <= 191){
                b++;
                if(num1>>20==0xAC1){
                    pri++;
                }
            }else if(t <= 223){
                c++;
                if(num1 >>16 == 0xC0A8){
                    pri++;
                }
            }else if(t <= 239){
                d++;
            }
            else if(t <= 255){
                e++;
            }
        }
        System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+err+" "+pri);
    }

    private static long strToInt(String str){
        char[] cs = str.toCharArray();
        long res = 0, tmp = 0, flag = 0;
        for(char c : cs){
            if(c == '.'){
                res = res << 8 | tmp;
                tmp = 0;
                flag++;
            }
            else if(c >= '0' && c <= '9'){
                tmp = tmp * 10 + c - '0';
                flag = 0;
            }else{
                return -1;
            }
            if(flag >= 2) {
                return -1;
            }

        }
        res = res << 8 | tmp;
        return res;
    }

    @Override
    public void sodaBottle() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine())!= null){
            int bottleCount = Integer.parseInt(str);
            if(bottleCount == 0){
                return;
            }
            int result = 0;
            while (true){
                int count = bottleCount / 3;
                result += count;
                bottleCount = bottleCount % 3 + count;
                if(bottleCount < 3){
                    break;
                }
            }
            if(bottleCount == 2){
                result += 1;
            }
            System.out.println(result);
        }
    }
}















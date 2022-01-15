package yuan.study.demo.service.designPattern.strategy;

public class SHA1Strategy implements Strategy{

    public void encrypt() {
        System.out.println("执行SHA1加密");
    }
}
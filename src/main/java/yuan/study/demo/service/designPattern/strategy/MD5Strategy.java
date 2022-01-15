package yuan.study.demo.service.designPattern.strategy;

public class MD5Strategy implements Strategy{

    public void encrypt() {
        System.out.println("执行MD5加密");
    }
}
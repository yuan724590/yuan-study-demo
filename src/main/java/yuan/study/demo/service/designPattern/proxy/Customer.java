package yuan.study.demo.service.designPattern.proxy;


public class Customer implements BuyCar {

    @Override
    public void buyCar(int cash) {
        System.out.println("买一辆法拉利花费了" + cash + "元");
    }
}
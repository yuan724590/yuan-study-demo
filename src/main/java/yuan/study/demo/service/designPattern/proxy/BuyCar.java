package yuan.study.demo.service.designPattern.proxy;

public interface BuyCar {

    /**
     * 买车
     */
    void buyCar(int cash);

    /**
     * 买一百辆车
     */
    String buyAHundredCars(int cash);
}
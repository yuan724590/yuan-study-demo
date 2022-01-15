package yuan.study.demo.service.designPattern.decorator;

public interface Car {

    /**
     * 车可以跑
     */
    void run();

    /**
     * 车可以停止
     */
    void stop();

    /**
     * 车的最大速度
     */
    int getMaxSpeed();
}
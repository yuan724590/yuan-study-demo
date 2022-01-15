package yuan.study.demo.service.designPattern.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarWrapper implements Car {

    private Car car;

    @Override
    public void run() {
        System.out.println("车子被增强了, 狂奔" + getMaxSpeed() + "KM/H！");
    }

    @Override
    public void stop() {

        System.out.println("车子速度太快了, 停不住了");
    }

    @Override
    public int getMaxSpeed() {
        return car.getMaxSpeed() * 10;
    }
}
package yuan.study.demo.service.designPattern.decorator;

public class Benz implements Car {

    @Override
    public void run() {

        System.out.println("奔驰狂奔" + getMaxSpeed() + "KM/H！");
    }

    @Override
    public void stop() {

        System.out.println("刹车....停住");
    }

    @Override
    public int getMaxSpeed() {

        return 200;
    }
}
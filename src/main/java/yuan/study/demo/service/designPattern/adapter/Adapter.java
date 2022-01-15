package yuan.study.demo.service.designPattern.adapter;

public class Adapter extends Adaptee implements Target{

    @Override
    public void request() {
        super.doSomething();
    }
}
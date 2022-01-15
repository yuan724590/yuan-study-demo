package yuan.study.demo.service.designPattern.adapter;

public class TargetImpl implements Target{

    @Override
    public void request() {
        System.out.println("目标接口做了一些事情");
    }
}

package yuan.study.demo.service.designPattern.mediator;

public class Leader extends Colleague {

    public Leader(Mediator mediator) {

        super(mediator);
    }

    @Override
    public void notify(String message) {

        System.out.println("领导收到同事信息：" + message);
    }
}
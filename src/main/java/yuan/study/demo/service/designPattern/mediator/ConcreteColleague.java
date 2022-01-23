package yuan.study.demo.service.designPattern.mediator;

public class ConcreteColleague extends Colleague {

    public ConcreteColleague(Mediator mediator) {

        super(mediator);
    }
    
    @Override
    public void notify(String message) {

        System.out.println("同事收到领导信息：" + message);
    }
}
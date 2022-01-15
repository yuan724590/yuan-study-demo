package yuan.study.demo.service.designPattern.mediator;


import lombok.Setter;

@Setter
public class ConcreteMediator extends Mediator {

    /**
     * 同事
     */
    private ConcreteColleague colleague;

    /**
     * 领导
     */
    private Leader leader;


    @Override
    public void send(String message, Colleague colleague) {
        if (colleague == this.colleague) {
            leader.notify(message);
        } else if (colleague == leader){
            this.colleague.notify(message);
        } else {
            System.out.println("Error!");
        }
    }
}
package yuan.study.demo.service.designPattern.state;

public class StartState implements State {

    public void doAction(StateContext stateContext) {
        System.out.println("Player is in start state");
        stateContext.setState(this);
    }
}
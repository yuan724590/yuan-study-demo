package yuan.study.demo.service.designPattern.state;

public class StopState implements State {

    public void doAction(StateContext stateContext) {
        System.out.println("Player is in stop state");
        stateContext.setState(this);
    }
}
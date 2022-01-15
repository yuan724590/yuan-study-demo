package yuan.study.demo.service.designPattern.state;

public interface State {

    /**
    * 执行动作
    */
    void doAction(StateContext stateContext);
} 
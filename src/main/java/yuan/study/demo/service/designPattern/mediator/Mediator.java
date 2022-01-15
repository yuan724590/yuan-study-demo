package yuan.study.demo.service.designPattern.mediator;

public abstract class Mediator {

    /**
     * 发送消息
     */
    public abstract void send(String message, Colleague colleague);
}
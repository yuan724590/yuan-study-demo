package yuan.study.demo.service.designPattern.mediator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Colleague {

    private Mediator mediator;

    /**
     * 发送消息
     */
    public void send(String message) {

        mediator.send(message, this);
    }

    /**
     * 进行通知
     */
    public abstract void notify(String message);
}
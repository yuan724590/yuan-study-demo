package yuan.study.demo.service.designPattern.command;

public class OrderTakeout implements Command {

    private Receiver receiver;

    public OrderTakeout(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //触发命令
        receiver.order();
    }
}

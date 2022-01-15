package yuan.study.demo.service.designPattern.command;

public class DeliverTakeout implements Command {

    private Receiver receiver;

    public DeliverTakeout( Receiver receiver) {

        this.receiver = receiver;
    }

    public void execute() {

        receiver.deliver( );
    }
}

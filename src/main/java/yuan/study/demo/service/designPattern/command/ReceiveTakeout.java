package yuan.study.demo.service.designPattern.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReceiveTakeout implements Command {

    private Receiver receiver;

    @Override
    public void execute() {

        receiver.receiver();
    }
}

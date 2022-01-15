package yuan.study.demo.service.designPattern.command;

import java.util.ArrayList;
import java.util.List;

public class Invoker {

    private List<Command> commands = new ArrayList<>();

    public void setCommand(Command command){

        commands.add( command);
    }

    public void call() {
        commands.forEach(Command::execute);
        commands.clear();
    }
}

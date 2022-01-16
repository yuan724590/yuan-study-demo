package yuan.study.demo.service.designPattern.interpreter;

public class XmlDomInterpreter extends Interpreter {
  
    @Override
    public void interpret(Context context) {

        System.out.println("xml dom Interpreter:" + context.getData());
    }
} 
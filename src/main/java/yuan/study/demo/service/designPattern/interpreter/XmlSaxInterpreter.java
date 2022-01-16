package yuan.study.demo.service.designPattern.interpreter;

public class XmlSaxInterpreter extends Interpreter {
  
    @Override
    public void interpret(Context context) {

        System.out.println("xml sax Interpreter:" + context.getData());
    }

}
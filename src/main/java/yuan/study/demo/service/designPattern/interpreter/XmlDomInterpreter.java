package yuan.study.demo.service.designPattern.interpreter;

public class XmlDomInterpreter implements Interpreter {
  
  @Override
  public void interpret(Context context) {

    System.out.println("xml dom Interpreter:" + context.getData());
  } 
  
} 
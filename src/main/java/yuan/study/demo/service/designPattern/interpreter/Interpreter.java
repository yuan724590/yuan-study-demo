package yuan.study.demo.service.designPattern.interpreter;

/**
 * 声明一个抽象的解释操作
 */
public abstract class Interpreter {

    /**
     * 可以有个返回的类型，定义解释出的数据对象
     */
    public abstract void interpret(Context context);
}
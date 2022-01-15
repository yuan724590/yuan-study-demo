package yuan.study.demo.service.designPattern.template;

public abstract class AbstractTemplate {

    /**
     * 模板方法
     */
    public void templateMethod(){
        System.out.println("打开冰箱门");
        //基本方法的声明（由子类实现）
        hookMethod();
        abstractMethod();
        //基本方法（已经实现）
        concreteMethod();
    }

    /**
     * 基本方法的声明（由子类实现）
     */
    protected abstract void abstractMethod();

    protected void hookMethod(){

    }

    /**
     * 基本方法（已经实现）
     */
    private void concreteMethod(){
        System.out.println("关上冰箱门");
    }
}
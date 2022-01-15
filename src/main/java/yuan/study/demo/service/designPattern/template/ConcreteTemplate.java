
package yuan.study.demo.service.designPattern.template;

public class ConcreteTemplate extends AbstractTemplate{

    @Override
    public void abstractMethod() {

        System.out.println("把大象放到冰箱里");
    }

    @Override
    public void hookMethod() {

        System.out.println("把长颈鹿从冰箱里拿出来");
    }
}
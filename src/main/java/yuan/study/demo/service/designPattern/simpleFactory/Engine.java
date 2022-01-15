package yuan.study.demo.service.designPattern.simpleFactory;

public class Engine implements CarFactory {

   @Override
   public void ability() {

       System.out.println("发动机制造好了");
   }
}
package yuan.study.demo.service.designPattern.simpleFactory;

public class Tyre implements CarFactory{

   @Override
   public void ability() {

       System.out.println("轮胎已经生产好了");
   }
}
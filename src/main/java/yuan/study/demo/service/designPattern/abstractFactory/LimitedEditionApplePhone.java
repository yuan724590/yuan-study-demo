package yuan.study.demo.service.designPattern.abstractFactory;


public class LimitedEditionApplePhone implements Apple {

   @Override
   public void productPhone() {
      System.out.println("生产一个限量版苹果手机");
   }
}
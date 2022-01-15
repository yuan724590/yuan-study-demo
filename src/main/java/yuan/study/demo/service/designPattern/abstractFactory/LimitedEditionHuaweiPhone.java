package yuan.study.demo.service.designPattern.abstractFactory;


public class LimitedEditionHuaweiPhone implements Huawei {

   @Override
   public void productPhone() {
      System.out.println("生产一个限量版华为手机");
   }
}
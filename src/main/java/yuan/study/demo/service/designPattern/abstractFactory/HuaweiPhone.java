package yuan.study.demo.service.designPattern.abstractFactory;


public class HuaweiPhone implements Huawei {

   @Override
   public void productPhone() {
      System.out.println("生产一个华为手机");
   }
}
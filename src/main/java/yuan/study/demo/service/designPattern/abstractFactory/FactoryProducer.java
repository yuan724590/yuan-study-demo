package yuan.study.demo.service.designPattern.abstractFactory;


import yuan.study.demo.common.Constants;

public class FactoryProducer {

   public static AbstractFactory getFactory(String brand){
      switch (brand){
         case Constants.HUAWEI:
            return new HuaweiFactory();
         case Constants.APPLE:
            return new AppleFactory();
         default:
            return null;
      }
   }
}
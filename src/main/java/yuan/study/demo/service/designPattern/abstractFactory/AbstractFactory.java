package yuan.study.demo.service.designPattern.abstractFactory;


public abstract class AbstractFactory {

   /**
    * 获取华为的产品
    */
   public abstract Huawei getHuawei(String type);

   /**
    * 获取苹果的产品
    */
   public abstract Apple getApple(String type);
}
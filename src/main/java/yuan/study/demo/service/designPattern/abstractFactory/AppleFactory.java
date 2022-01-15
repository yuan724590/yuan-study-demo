package yuan.study.demo.service.designPattern.abstractFactory;

import org.springframework.util.StringUtils;
import yuan.study.demo.common.Constants;

public class AppleFactory extends AbstractFactory {


   @Override
   public Huawei getHuawei(String type) {
      return null;
   }

   @Override
   public Apple getApple(String type) {
      if(StringUtils.isEmpty(type)){
         return null;
      }
      switch (type){
         case Constants.ORDINARY:
            return new ApplePhone();
         case Constants.LIMITED_EDITION:
            return new LimitedEditionApplePhone();
         default:
            return null;
      }
   }
}
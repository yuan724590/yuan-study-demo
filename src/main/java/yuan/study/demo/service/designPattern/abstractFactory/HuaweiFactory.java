package yuan.study.demo.service.designPattern.abstractFactory;

import org.springframework.util.StringUtils;
import yuan.study.demo.common.Constants;

public class HuaweiFactory extends AbstractFactory {


   @Override
   public Huawei getHuawei(String type) {
      if(StringUtils.isEmpty(type)){
         return null;
      }
      switch (type){
         case Constants.ORDINARY:
            return new HuaweiPhone();
         case Constants.LIMITED_EDITION:
            return new LimitedEditionHuaweiPhone();
         default:
            return null;
      }
   }

   @Override
   public Apple getApple(String model) {
      return null;
   }
}
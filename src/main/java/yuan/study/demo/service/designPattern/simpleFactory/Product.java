package yuan.study.demo.service.designPattern.simpleFactory;

import yuan.study.demo.common.Constants;

public class Product {

   public CarFactory product(String skill) {

       CarFactory carFactory = null;

       switch (skill) {
           // 制造轮胎
           case Constants.TYRE:
               carFactory = new Tyre();
               break;
           // 制造发动机
           case Constants.ENGINE:
               carFactory = new Engine();
               break;
       }

       return carFactory;
   }
}
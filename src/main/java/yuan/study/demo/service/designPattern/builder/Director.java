package yuan.study.demo.service.designPattern.builder;

public class Director {

    public String build(Builder builder){
        //指挥抽象类盖房子
        //1. 买材料
        builder.buyMaterials();
        //2. 盖房子
        builder.buildHouse();
        return "给一套房";
    }
}
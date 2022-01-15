package yuan.study.demo.service.designPattern.builder;

public class Worker extends Builder {

    @Override
    void buyMaterials() {
        System.out.println("我去建材市场买点材料");
    }

    @Override
    void buildHouse() {
        System.out.println("叫上两百个兄弟跟我一起盖房子");
    }
}
package yuan.study.demo.service.designPattern.builder;

public class AnotherWorker extends Builder {

    @Override
    void buyMaterials() {
        System.out.println("先喝杯奶茶");
    }

    @Override
    void buildHouse() {
        System.out.println("打电话叫建筑公司帮我盖房子");
    }
}
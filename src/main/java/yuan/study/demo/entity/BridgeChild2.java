package yuan.study.demo.entity;


public class BridgeChild2 extends BridgeParent2<String> {

    @Override
    public void hello(String str) {
        System.out.println("i am BridgeChild2");
    }
}

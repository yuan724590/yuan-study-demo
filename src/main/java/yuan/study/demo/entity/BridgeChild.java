package yuan.study.demo.entity;


public class BridgeChild extends BridgeParent{

    @Override
    public Integer sayHello(String msg){
        System.out.println("hello, i am BridgeChild " + msg);
        return 0;
    }
}

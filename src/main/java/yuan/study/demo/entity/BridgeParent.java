package yuan.study.demo.entity;


public abstract class BridgeParent {

    public Number sayHello(String msg){
        System.out.println("hello, i am BridgeParent " + msg);
        return 0;
    }
}

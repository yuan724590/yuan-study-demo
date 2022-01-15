package yuan.study.demo.service.designPattern.chainOfResponsibility;

public class Handler2 extends AbstractHandler{

    @Override
    void doHandler(HandlerChainContext chainContext, Object args) {
        args = args.toString() + "...handler2的小尾巴...." ;
        System.out.println("Handler2的实例，参数:" + args);
        //继续执行下一个
        chainContext.runNext(args);
    }
}
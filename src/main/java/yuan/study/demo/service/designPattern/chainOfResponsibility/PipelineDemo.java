package yuan.study.demo.service.designPattern.chainOfResponsibility;

public class PipelineDemo {

    /**
     *  初始化的时候造一个head，作为责任链的开始，但是并没有具体的处理
     **/
    private HandlerChainContext head = new HandlerChainContext(new AbstractHandler(){
        @Override
        void doHandler(HandlerChainContext chainContext, Object args) {
            chainContext.runNext(args);
        }
    });

    public void requestProcess(Object args){
        this.head.handler(args);
    }

    public void addByLast(AbstractHandler handler){
        HandlerChainContext context = head;
        while (context.getNextHandler() != null){
            context = context.getNextHandler();
        }
        context.setNextHandler(new HandlerChainContext(handler));
    }
}
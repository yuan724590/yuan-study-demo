package yuan.study.demo.service.designPattern.chainOfResponsibility;

abstract class AbstractHandler{

    /**
     * 处理器
     */
    abstract void doHandler(HandlerChainContext chainContext, Object args);
}

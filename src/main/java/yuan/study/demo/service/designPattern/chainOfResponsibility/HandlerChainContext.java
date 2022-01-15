package yuan.study.demo.service.designPattern.chainOfResponsibility;

import lombok.Data;

@Data
class HandlerChainContext{

    /**
     * 下一个节点
     */
    private HandlerChainContext nextHandler;

    private AbstractHandler handler;

    HandlerChainContext(AbstractHandler handler){
        this.handler = handler;
    }

    void handler(Object args){
        this.handler.doHandler(this, args);
    }

    /**
     *  继续执行下一个
     */
    void runNext(Object args){
        if(this.nextHandler != null){
            this.nextHandler.handler(args);
        }
    }
}
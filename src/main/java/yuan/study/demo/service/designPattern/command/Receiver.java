package yuan.study.demo.service.designPattern.command;

public class Receiver {

    /**
     * 下订单
     */
    void order() {

        System.out.println("下单成功，请及时送货上门");
    }

    /**
     * 送货
     */
    void deliver() {

        System.out.println("正在送货路上，路上堵车, 会迟到10分钟");
    }

    /**
     * 接收外卖
     */
    void receiver(){

        System.out.println("已收到订单, 时间有点久，不太满意");
    }
}
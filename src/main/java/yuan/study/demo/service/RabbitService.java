package yuan.study.demo.service;


public interface RabbitService {

    /**
     * 死信队列
     */
    String deadLetter(String msg);

    /**
     * 延迟队列
     */
    String delay(String value);
}

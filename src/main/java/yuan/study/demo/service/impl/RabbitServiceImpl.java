package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import yuan.study.demo.configuration.RabbitConfiguration;
import yuan.study.demo.service.RabbitService;
import javax.annotation.Resource;


@Slf4j
@Service
public class RabbitServiceImpl implements RabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public String deadLetter(String msg) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.BUSINESS_QUEUE_EXCHANGE, "", msg);
        return "success";
    }

    @Override
    public String delay(String msg){
        msg = "当前时间为:" + System.currentTimeMillis() + ", 需要发送的内容:" + msg;
        rabbitTemplate.convertAndSend(RabbitConfiguration.BUSINESS_QUEUE_EXCHANGE, "", msg);
        return "success";
    }
}

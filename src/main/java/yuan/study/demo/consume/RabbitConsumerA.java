package yuan.study.demo.consume;

import com.google.common.base.Throwables;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import yuan.study.demo.configuration.RabbitConfiguration;

@Slf4j
@Component
public class RabbitConsumerA {

    @RabbitListener(queues = RabbitConfiguration.BUSINESS_QUEUE_A)
    public void receiveA(Message msg, Channel channel) {
        String content = new String(msg.getBody());
        log.info("队列{}收到消息:{}", RabbitConfiguration.BUSINESS_QUEUE_A, content);
        boolean ack = true;
        try{
            if(content.contains("deadLetter")){
                throw new RuntimeException("dead letter exception");
            }
        }catch (Exception e){
            ack = false;
            log.error("{}收到消息后异常 content:{}, e:{}",
                    RabbitConfiguration.BUSINESS_QUEUE_A, content, Throwables.getStackTraceAsString(e));
        }
        try {
            if(ack){
                channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
            }else{
                channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);
            }
        } catch (Exception e) {
            log.error("{}收到消息后 basicAck异常 content:{}, e:{}",
                    RabbitConfiguration.BUSINESS_QUEUE_A, content, Throwables.getStackTraceAsString(e));
        }
    }
}

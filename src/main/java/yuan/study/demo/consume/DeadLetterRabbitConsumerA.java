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
public class DeadLetterRabbitConsumerA {

    @RabbitListener(queues = RabbitConfiguration.DEAD_LETTER_QUEUE_A)
    public void receiveA(Message msg, Channel channel) {
        String content = new String(msg.getBody());
        log.info("队列{}, 当前时间为:{}, 收到消息:{}", RabbitConfiguration.DEAD_LETTER_QUEUE_A, System.currentTimeMillis(), content);
        try {
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("{}收到消息后 basicAck异常 content:{}, e:{}",
                    RabbitConfiguration.DEAD_LETTER_QUEUE_A, content, Throwables.getStackTraceAsString(e));
        }
    }
}

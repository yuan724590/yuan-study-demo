package yuan.study.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfiguration {

    public static final String BUSINESS_QUEUE_EXCHANGE = "yuan.study.demo.exchange";
    public static final String DEAD_LETTER_QUEUE_EXCHANGE = "yuan.study.demo.dead.letter.exchange";
    public static final String BUSINESS_QUEUE_A = "yuan.study.demo.business.a";

    public static final String DEAD_LETTER_QUEUE_A = "yuan.study.demo.dead.a";
    public static final String DEAD_LETTER_QUEUE_A_KEY = "yuan.study.demo.dead.a.key";

    public static final String BUSINESS_QUEUE_B = "yuan.study.demo.business.b";

    public static final String DEAD_LETTER_QUEUE_B = "yuan.study.demo.dead.b";
    public static final String DEAD_LETTER_QUEUE_B_KEY = "yuan.study.demo.dead.b.key";

    /**
     * 声明死信队列的交换机
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_QUEUE_EXCHANGE);
    }

    /**
     * 声明死信队列A
     */
    @Bean
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUE_A);
    }

    /**
     * 声明队列和交换机之间的绑定关系
     */
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_A_KEY);
    }

    /**
     * 声明业务队列的交换机
     */
    @Bean
    public FanoutExchange businessExchange() {
        return new FanoutExchange(BUSINESS_QUEUE_EXCHANGE);
    }

    /**
     * 声明业务队列A
     */
    @Bean
    public Queue businessQueueA() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_LETTER_QUEUE_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_A_KEY);
        return QueueBuilder.durable(BUSINESS_QUEUE_A).withArguments(map).build();
    }

    /**
     * 声明队列A的绑定关系
     */
    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    /**
     * 声明死信队列B
     */
    @Bean
    public Queue deadLetterQueueB() {
        return new Queue(DEAD_LETTER_QUEUE_B);
    }

    /**
     * 声明队列和交换机之间的绑定关系
     */
    @Bean
    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_B_KEY);
    }

    /**
     * 声明业务队列B
     */
    @Bean
    public Queue businessQueueB() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_LETTER_QUEUE_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_B_KEY);
        map.put("x-message-ttl", 5000);
        return QueueBuilder.durable(BUSINESS_QUEUE_B).withArguments(map).build();
    }

    /**
     * 声明队列B的绑定关系
     */
    @Bean
    public Binding businessBindingB(@Qualifier("businessQueueB") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
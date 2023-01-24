package yuan.study.demo.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Book;

@Slf4j
@Service
public class BookRegisterListener {

    @EventListener
    public void sendMsg(BookRegisterEvent bookRegisterEvent) {
        Book book = (Book) bookRegisterEvent.getSource();
        log.info("第一次调用, 发送消息，书:{}", JSON.toJSONString(book));
    }

    @EventListener
    public void sendMsgAgain(BookRegisterEvent bookRegisterEvent) {
        Book book = (Book) bookRegisterEvent.getSource();
        log.info("第二次调用, 再次发送消息，书:{}", JSON.toJSONString(book));
    }

    @EventListener
    @Async
    public void sendMsgAsync(BookRegisterEvent bookRegisterEvent) {
        Book book = (Book) bookRegisterEvent.getSource();
        log.info("异步调用, 发送消息，书:{}", JSON.toJSONString(book));
    }
}
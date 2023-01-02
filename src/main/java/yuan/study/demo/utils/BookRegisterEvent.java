package yuan.study.demo.utils;

import org.springframework.context.ApplicationEvent;
import yuan.study.demo.entity.Book;

public class BookRegisterEvent extends ApplicationEvent {

    public BookRegisterEvent(Book book) {

        super(book);
    }

}
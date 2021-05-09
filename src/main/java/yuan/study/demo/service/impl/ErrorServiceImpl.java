package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Book;
import yuan.study.demo.service.ErrorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ErrorServiceImpl implements ErrorService {


    @Override
    public String stackOverflowError() {
        stackOverflowError();
        return "success";
    }

    @Override
    public String stackOverflowError1() {
        Book book;
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            book = new Book();
        }
        //相对于上面这种, 对导致栈占用更多的内存
        for(int i = 0; i < 5; i++){
            Book book0 = new Book();
            //每次循环多声明点变量, 1w起步吧
        }

        return "success";
    }

    @Override
    public String heapSpaceOOM(){
        //需要配置更小的堆以实现更快的OOM
        //-Xmx15m -Xms15m 太小会起不来
        //也有可能会导致OOM GC overhead limit exceeded
        List<Book> bookList = new ArrayList<>();
        for(;;){
            bookList.add(new Book());
        }
    }
}

package yuan.study.demo.service.impl;

//import jdk.internal.misc.Unsafe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Book;
import yuan.study.demo.service.ErrorService;

import java.util.*;


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
        while(true){
            bookList.add(new Book());
        }
    }

    @Override
    public String stackLeakByThread(){
        while (true) {
            Thread thread = new Thread(this::dontStop);
            thread.start();
        }
    }

    private void dontStop(){
        while (true) {
        }
    }

    @Override
    public String runtimeConstantPoolOOM(){
        // 仅JDK6及其以下生效 启动时配置 -XX:PermSize=6M -XX:MaxPermSize=6M, 限制永久代的大小
        // JDK7及其以上 启动时配置 -Xmx14M 限制堆大小
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while(true){
            set.add(String.valueOf(i++).intern());
        }
    }

    @Override
    public String directMemoryOOM(){
        try {
            //需要使用jdk11, 打开import jdk.internal.misc.Unsafe;
            //并配置 -Xmx20M -XX:MaxDirectMemorySize=10M --add-opens java.base/jdk.internal.misc=ALL-UNNAMED
//            Field unsafeField = Unsafe.class.getDeclaredFields()[0];
//            unsafeField.setAccessible(true);
//            Unsafe unsafe = (Unsafe) unsafeField.get(null);
//            while (true) {
//                unsafe.allocateMemory(1024*1024);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}

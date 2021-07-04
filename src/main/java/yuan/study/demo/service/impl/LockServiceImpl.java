package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Students;
import yuan.study.demo.service.DemoService;
import yuan.study.demo.service.LockService;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
@Service
public class LockServiceImpl implements LockService {

    @Override
    public String reentrantLock() {
        //示范ReentrantLock的公平性
        locks();
        //ReentrantLock tryLocks方法, 拿到锁会返回true, 否则返回false, 不再等待
        tryLocks();
        return null;
    }

    /**
     * ReentrantLock tryLocks方法, 拿到锁会返回true, 否则返回false, 不再等待
     */
    private void tryLocks(){
        Lock lock = new ReentrantLock();
        List<Future> futureList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            int finalI = i;
            Future future = CompletableFuture.runAsync(() -> {
                try {
                    if(lock.tryLock(1, TimeUnit.SECONDS)){
                        Thread.sleep(2000);
                        System.out.println("tryLocks执行顺序为" + finalI);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            futureList.add(future);
        }
        for(Future future : futureList){
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 示范ReentrantLock的公平性
     */
    private void locks(){
        Lock lock = new ReentrantLock();
        List<Future> futureList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            int finalI = i;
            Future future = CompletableFuture.runAsync(() -> {
                try {
                    lock.lock();
                    //这里会依序打印, 因为ReentrantLock是公平锁, 等待越久越先执行
                    System.out.println("执行顺序为" + finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            futureList.add(future);
        }
        for(Future future : futureList){
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

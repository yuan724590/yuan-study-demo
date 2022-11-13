package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.LockService;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Slf4j
@Service
public class LockServiceImpl implements LockService {

    @Override
    public String reentrantLock() {
        //示范ReentrantLock的公平性
        locks(false);
        locks(true);
        //ReentrantLock tryLocks方法, 拿到锁会返回true, 否则返回false, 不再等待
        tryLocks();
        return "success";
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
                boolean flag = false;
                try {
                    flag = lock.tryLock(1, TimeUnit.SECONDS);
                    if(flag){
                        Thread.sleep(200);
                        System.out.println("tryLocks执行顺序为" + finalI);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(flag){
                        lock.unlock();
                    }
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
     * ReentrantLock的公平性, 默认是非公平锁, 入参为true时则为公平锁
     */
    private void locks(boolean flag){
        Lock lock = new ReentrantLock(flag);
        List<Future> futureList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            int finalI = i;
            Future future = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(finalI * 5);
                    lock.lock();
                    //如果ReentrantLock是公平锁, 那么等待越久越先执行, 顺序应为 0,1,2,3,4, 否则为非公平锁
                    log.info("flag:{}, locks 执行顺序为:{}", flag, finalI);
                    Thread.sleep(finalI * 10);
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

    private Map<String,String> cacheData = new HashMap<>();

    @Override
    public String reentrantReadWriteLock() {
        String key = "reentrantReadWriteLock-key";
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        //获取读锁
        lock.readLock().lock();
        try{
            //如果缓存有效, 直接使用data
            String data = cacheData.get(key);
            if(StringUtils.isNotEmpty(data)){
                return data;
            }
        }finally {
            //释放读锁
            lock.readLock().unlock();
        }

        //获取写锁
        lock.writeLock().lock();
        try{
            //如果缓存无效，更新cache;
            String data = "";
            cacheData.put(key,data);
            return data;
        }finally {
            //释放写锁
            lock.writeLock().unlock();
        }
    }

    @Override
    public String conditionSynchronized(){
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            int finalI = i;
            boolean condition = new Random().nextBoolean();
            threadList.add(new Thread(() -> start(condition, "thread" + finalI)));
        }
        for(Thread thread : threadList){
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized(this){
            this.notifyAll();
        }
        return "success";
    }

    private synchronized void start(boolean condition, String threadName){
        log.info("执行线程:{}, thread:{}", threadName, Thread.currentThread().getName());
        if(condition){
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        log.info("执行线程:{}, 结束", threadName);
    }
}

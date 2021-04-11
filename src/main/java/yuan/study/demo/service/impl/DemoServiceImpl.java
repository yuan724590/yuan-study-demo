package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yuan.study.demo.dto.DemoDTO;
import yuan.study.demo.service.DemoService;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public String threadLocal(String value) {
        //初始化
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        //设置值, 其中key为线程的hash,value为设置的值
        threadLocal.set(value);
        //直接get就行,仅在本线程中有效
        String result = threadLocal.get();
        //不使用时及时清除,防止造成内存问题
        threadLocal.remove();
        return result;
    }

    @Override
    public String subList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        ArrayList arrayList = (ArrayList) list.subList(0, 1);
        return "success";
    }

    @Override
    public String entrySet(){
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i = 0; i < 1000_0000; i++){
            map.put(i, true);
        }
        int a = 0;
        long t1 = System.currentTimeMillis();
        for(Integer key : map.keySet()){
            a = key++;
        }
        long t2 = System.currentTimeMillis() - t1;
        //三次结果分别为 508ms 379ms 340ms
        System.out.println("keySet耗时为:" + t2);

        long t11 = System.currentTimeMillis();
        for(Map.Entry<Integer, Boolean> entry : map.entrySet()){
            a = entry.getKey() + 1;
        }
        long t22 = System.currentTimeMillis() - t11;
        //三次结果分别为 135ms 128ms 126ms
        System.out.println("entrySet耗时为:" + t22);

        long t111 = System.currentTimeMillis();
        map.forEach((key, value) -> {
            int b = key + 1;
        });
        long t222 = System.currentTimeMillis() - t111;
        //三次结果分别为 85ms 75ms 82ms
        System.out.println("foreach耗时为:" + t222);
        return "success";
    }

    @Override
    public String mapCompare(){
        //线程安全的Map
        Map<Integer, Boolean> hashtableMap = new Hashtable<>();
        Future future1 = syncPut(hashtableMap);
        Future future2 = syncPut(hashtableMap);
        futureGet(future1);
        futureGet(future2);
        System.out.println(hashtableMap);

        //相当于线程不安全的Hashtable, 也有更多的实现方法
        Map<Integer, Boolean> map = new HashMap<>();
        Future future3 = syncPut(map);
        Future future4 = syncPut(map);
        futureGet(future3);
        futureGet(future4);
        System.out.println(map);

        return "success";
    }

    private void futureGet(Future future){
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Future syncPut(Map<Integer, Boolean> map){
        Future future = cachedThreadPool.submit(() -> {
            for(int i = 0; i < 10_0000; i++){
                map.put(atomicInteger.incrementAndGet(), true);
            }
        });
        return future;
    }
}

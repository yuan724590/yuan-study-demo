package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.ListDemoService;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Service
public class ListDemoServiceImpl implements ListDemoService {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Override
    public String subList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        //这里会抛异常
        ArrayList arrayList = (ArrayList) list.subList(0, 1);
        return "success";
    }

    public String arrayList(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 5000_0000; i++){
            list.add(i);
        }
//        for(Integer obj : list){
//            //会报ConcurrentModificationException
//            list.remove(obj);
//            list.add(1);
//        }
        Iterator<Integer> iterable = list.iterator();
        while(iterable.hasNext()){
            System.out.println(iterable.next());
        }
        Iterator<Integer> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }
        Spliterator spliterator = list.spliterator();
        Spliterator s1 = spliterator.trySplit();
        Spliterator s2 = spliterator.trySplit();

        System.out.println("===============spliterator================");
        spliterator.forEachRemaining((i) -> System.out.print(i+" "));
        System.out.println();
        System.out.println("===============s1================");
        s1.forEachRemaining((i) -> System.out.print(i+" "));
        System.out.println();
        System.out.println("===============s2================");
        s2.forEachRemaining((i) -> System.out.print(i+" "));
        return "success";
    }

    @Override
    public String syncList(){
        //解决arrayList并发问题
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list1 = new CopyOnWriteArrayList<>();
        return "success";
    }
}

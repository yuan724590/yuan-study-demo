package yuan.study.demo.service.impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yuan.study.demo.dto.DemoDTO;
import yuan.study.demo.entity.Students;
import yuan.study.demo.service.DemoService;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

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
    public String syncGetTime(){
        try {
            List<Future> futureList = new ArrayList<>();
            for(int i = 0; i < 500; i++){
                futureList.add(cachedThreadPool.submit(() -> checkSimpleDateTime()));
            }
            for(int i = 0; i < futureList.size(); i++){
                futureList.get(i).get(1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 校验SimpleDateTime的时间戳
     */
    private void checkSimpleDateTime(){
        String dateString = sdf.format(new Date());
        try {

            Date parseDate = sdf.parse(dateString);
            String dateString2 = sdf.format(parseDate);
            if(!dateString.equals(dateString2)){
                System.out.println("SimpleDateFormat非线程安全");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String localDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        //获取日期
        int day = localDateTime.getDayOfMonth();
        System.out.println(day);
        //时间延后一天
        day = localDateTime.plusDays(1).getDayOfMonth();
        System.out.println(day);
        //时间提前一天
        day = localDateTime.minusDays(1).getDayOfMonth();
        System.out.println(day);
        //比较时间
        LocalDateTime localDateTime1 = LocalDateTime.of(2018, 1, 1, 1, 1, 1);
        boolean isAfter = localDateTime1.isAfter(localDateTime);
        System.out.println(isAfter);
        boolean isBefore = localDateTime1.isBefore(localDateTime);
        System.out.println(isAfter);
        //格式化时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formatTime = dateTimeFormatter.format(localDateTime);
        System.out.println(formatTime);
        return "success";
    }

    @Override
    public String createObject(){
        //第一种 通过new语句进行创建对象
        Students students = new Students();
        students.setId(1);

        //第二种 通过反射进行创建对象,需要实现Cloneable
        try {
            //路径同import路径
            Class clazz = Class.forName("yuan.study.demo.entity.Students");
            //使用newInstance创建对象, 使用于无参
            Students students1 = (Students) clazz.newInstance();
            students1.setId(2);

            //使用Constructor创建对象, 使用于无参 + 有参
            Class[] classes = new Class[]{int.class, String.class};
            Students students2 = Students.class.getConstructor(classes).newInstance(1, "2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //第三种 通过clone创建对象
        Students students3 = students.clone();

        //第四种 通过序列化创建对象,需要实现Serializable
        createBySerialize();
        return "success";
    }

    /**
     * 通过序列化创建对象,需要实现Serializable
     */
    private void createBySerialize(){
        Students students4 = new Students();
        //准备一个文件用于存储该对象的信息
        File file = new File("src/main/resources/Students.obj");
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream(file));
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            //序列化对象，写入到磁盘中
            oos.writeObject(students4);
            //反序列化对象
            Students Students = (Students)ois.readObject();
            Students.setId(1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(ois != null){
                try {
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String accessible(){
        try {
            Students students2 = new Students(1, "2");
            for (Field f : Students.class.getDeclaredFields()) {
                //类中的成员变量为private, 开启权限
                f.setAccessible(true);
                //获取当前对象中当前Field的value
                System.out.println(f.get(students2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String getDeclaredConstructor(){
        try {
            //getDeclaredConstructor 获取所有的构造器, 包括private
            Constructor<?>[] studentsConstructor = Students.class.getDeclaredConstructors();
            Constructor<?> privateConstructor = null;
            for(int i = 0; i < studentsConstructor.length; i++){
                Constructor<?> constructor = studentsConstructor[i];
                if(Modifier.PRIVATE == constructor.getModifiers()){
                    privateConstructor = constructor;
                    break;
                }
            }
            privateConstructor.setAccessible(true);
            Students students = (Students) privateConstructor.newInstance(1);
            System.out.println(students.getId());

            //getConstructor 只可以获取到public的构造器, 所以这里会报错
            students = Students.class.getConstructor(int.class).newInstance(1);
            System.out.println(students.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String getMemory(){
        long start = Runtime.getRuntime().freeMemory();
        Object obj = new Object();
        //查看jvm空闲内存大小
        long end = Runtime.getRuntime().freeMemory();
        //获取map使用的内存大小
        double useMemory = start - end;
        //打印map使用的内存大小
        System.out.println("使用掉的内存："+ useMemory + "B");
        obj = null;
        return "success";
    }

    @Override
    public String grammar(){
        int i = 10;
        i = i++;
        System.out.println(i);//结果是10
        i = ++i;
        System.out.println(i);//结果是11
        return "success";
    }
}

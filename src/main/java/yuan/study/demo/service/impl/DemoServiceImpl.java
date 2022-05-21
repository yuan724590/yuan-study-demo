package yuan.study.demo.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import yuan.study.demo.configuration.ElasticsearchConfiguration;
import yuan.study.demo.entity.GoodsInfo;
import yuan.study.demo.entity.GoodsInfoCopy;
import yuan.study.demo.entity.Students;
import yuan.study.demo.service.DemoService;
import yuan.study.demo.utils.Base64Util;
import yuan.study.demo.utils.CopierUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


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
    public String countDownLatch(){
        try{
            ExecutorService service = Executors.newFixedThreadPool(3);
            final CountDownLatch latch = new CountDownLatch(3);
            for (int i = 0; i < 3; i++) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                            Thread.sleep(1000);
                            System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
                            latch.countDown();//当前线程调用此方法，则计数减一
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                service.execute(runnable);
            }
            System.out.println("主线程" + Thread.currentThread().getName() + "等待子线程执行完成...");
            //阻塞当前线程，直到计数器的值为0
            latch.await();
            System.out.println("主线程" + Thread.currentThread().getName() + "开始执行...");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String reentrantLockAndCondition(){
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        List<String> list = new ArrayList<>();
        // 实现线程A
        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5)
                    condition.signal();
            }
            lock.unlock();
        });
        // 实现线程B
        Thread threadB = new Thread(() -> {
            lock.lock();
            if (list.size() != 5) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程B收到通知，开始执行自己的业务...");
            lock.unlock();
        });
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
        return "success";
    }

    @Override
    public String lockSupport() {
        List<String> list = new ArrayList<>();
        // 实现线程B
        final Thread threadB = new Thread(() -> {
            if (list.size() != 5) {
                LockSupport.park();
            }
            System.out.println("线程B收到通知，开始执行自己的业务...");
        });
        // 实现线程A
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5){
                    LockSupport.unpark(threadB);
                }
            }
        });
        threadA.start();
        threadB.start();
        return "success";
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
    public String getHardwareInformation(){
        long start = Runtime.getRuntime().freeMemory();
        Object obj = new Object();
        //查看jvm空闲内存大小
        long end = Runtime.getRuntime().freeMemory();
        //获取map使用的内存大小
        double useMemory = start - end;
        //打印map使用的内存大小
        System.out.println("使用掉的内存："+ useMemory + "B");
        obj = null;
        //获取当前CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
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

    @Override
    public String stringIntern(){
        String str0 = new String("计算机软件吧");
        String str00 = new String("计算机软件吧");
        System.out.println(str0 == str00); //JDK8 false

        str0 = "计算机软";
        str00 = "计算机软";
        System.out.println(str0 == str00); //JDK8 true

        str0 = "计算机软" + "哈哈";
        str00 = "计算机软" + "哈哈";
        System.out.println(str0 == str00); //JDK8 true

        final String str01 = new String("计算机挺好");
        final String str02 = new String("计算机挺好");
        System.out.println(str01 == str02); //JDK8 false

        final String str03 = "计算";
        final String str04 = "机挺好的";
        String str05 = str03 + str04;
        String str06 = "计算机挺好的";
        System.out.println(str05 == str06); //JDK8 true

        String str07 = "计算";
        String str08 = str07 + "01";
        String str09 = "计算01";
        System.out.println(str07 == str08); //JDK8 false

        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str11 = new StringBuilder("计算机").append("软件").toString();
        String intern1 = str1.intern();
        String str12 = "计算机软件";
        System.out.println(str1 == str11);  //JDK6 false JDK8 false
        System.out.println(str1 == intern1);//JDK6 false JDK8 true
        System.out.println(str1 == str12);  //JDK6 true JDK8 true
        // JDK6 intern方法会把首次遇到的字符串实例复制到永久代的字符串常量池存储, 返回的也是永久代里面
        // 这个字符串实例的引用, StringBuilder创建的字符串对象实例在java堆上, 所以不相等

        // JDK8 intern()和StringBuilder创建的字符串对象实例在java堆上, 返回的引用地址相同, 也相等
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);//JDK6 false JDK8 false
        //如果是已经创建过的字符串, 如"java", 则是不相等, 因为并不满足intern()的首次遇到原则, 则在JDK6 / 8 都不相等
        return "success";
    }

    @Override
    public String doubleDemo(){
        double a1 = 0.111111111111111111111111111111111111111111111;
        double a2 = 0.111111111111111;
        //55.111111111111114
        double a3 = 55.11111111111111115;
        //17个展示位 0.11111111111111112
        double a4 = 0.111111111111111115;
        return "";
    }

    private static volatile int race = 0;

    private static void increase() {
        for(int i = 0; i < 10000; i++){
            race ++;
        }
    }

    @Override
    public String volatileDemo() {
        CompletableFuture future = CompletableFuture.allOf(
                CompletableFuture.runAsync(DemoServiceImpl::increase),
                CompletableFuture.runAsync(DemoServiceImpl::increase),
                CompletableFuture.runAsync(DemoServiceImpl::increase)
        );
        future.join();
        //25102
        //进行值覆盖时会造成问题
        System.out.println(race);
        return "success";
    }

    @Override
    public String attributeCopy() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(1);
        goodsInfo.setGoodsBrand("商品品牌");
        goodsInfo.setGoodsType((byte) 1);
        goodsInfo.setGoodsName("商品名");
        goodsInfo.setGoodsPrice(new BigDecimal(1));
        goodsInfo.setHistoricalLowestPrice(new BigDecimal(1));
        goodsInfo.setImageUrl("www.baidu.com");
        goodsInfo.setImageHeight(1);
        goodsInfo.setImageWidth(1);
        goodsInfo.setCreateTime(1);
        goodsInfo.setUpdateTime(1);
        long t1 = System.currentTimeMillis();
        for(int i = 0; i < 10_0000; i++){
            CopierUtil.copyProperties(goodsInfo, GoodsInfoCopy.class);
        }
        t1 = System.currentTimeMillis() - t1;
        //94ms
        System.out.println("CopierUtil工具耗时为:" + t1);

        long t2 = System.currentTimeMillis();
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        for(int i = 0; i < 10_0000; i++){
            beanMapper.map(goodsInfo, GoodsInfoCopy.class);
        }
        t2 = System.currentTimeMillis() - t2;
        //2秒, 2310ms
        System.out.println("DozerBeanMapper工具耗时为:" + t2);

        long t3 = System.currentTimeMillis();
        try {
            for(int i = 0; i < 10_0000; i++) {
                BeanUtils.copyProperties(goodsInfo, GoodsInfoCopy.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        t3 = System.currentTimeMillis() - t3;
        //8分钟, 484930ms
        System.out.println("BeanUtils工具耗时为:" + t3);
        return "success";
    }

    @Override
    public String esAliKnn(){
//        insert();
        String base64Str = imgToBase64("https://pic.to8to.com/adcms/20220429/7c75383f6bbbd346f91196ffbf7f4efd.jpg");
        float[] floatArray = convertBase64ToArray(base64Str);
        for(int i = 0; i < floatArray.length; i++){
            try{
                BigDecimal bigDecimal = new BigDecimal(floatArray[i]);
                System.out.println(bigDecimal.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString());
            }catch (Exception e){

            }
        }
        return "success";
    }

    public float[] convertBase64ToArray(String base64Str) {
        byte[] decode = Base64.getDecoder().decode(base64Str.replace("\r\n", "").getBytes());
        FloatBuffer floatBuffer = ByteBuffer.wrap(decode).asFloatBuffer();
        float[] dims = new float[floatBuffer.capacity()];
        floatBuffer.get(dims);
        return dims;
    }

    public String convertArrayToBase64(float[] array) {
        final int capacity = Float.BYTES * array.length;
        final ByteBuffer bb = ByteBuffer.allocate(capacity);
        for (float v : array) {
            bb.putFloat(v);
        }
        bb.rewind();
        final ByteBuffer encodedBB = Base64.getEncoder().encode(bb);

        return new String(encodedBB.array());
    }

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public void insert(){
        try {
            IndexRequest indexRequest = new IndexRequest("test", "default");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "风景1");
            String base64Str = imgToBase64("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2019-06-17%2F5d07582a6b690.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654352851&t=69800081e2315432024489c001d78ce5");
            jsonObject.put("embedding_vector", base64Str);
            indexRequest.source(jsonObject, XContentType.JSON);
            indexRequest.id("1");
            restHighLevelClient.index(indexRequest);

            indexRequest = new IndexRequest("test", "default");
            jsonObject = new JSONObject();
            jsonObject.put("name", "风景2");
            base64Str = imgToBase64("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F10%2F20141010165320_kEUVj.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654352851&t=8bbfce58bbc45194841d92c81217e5cf");
            jsonObject.put("embedding_vector", base64Str);
            indexRequest.source(jsonObject, XContentType.JSON);
            indexRequest.id("2");
            restHighLevelClient.index(indexRequest);

            indexRequest = new IndexRequest("test", "default");
            jsonObject = new JSONObject();
            jsonObject.put("name", "风景3");
            base64Str = imgToBase64("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff7ee82e2354854624b89737fdb82365036eaf18a32bc3-rqjJB2_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654352851&t=d40270d82e217ebe9b70d5313d12a4f6");
            jsonObject.put("embedding_vector", base64Str);
            indexRequest.source(jsonObject, XContentType.JSON);
            indexRequest.id("3");
            restHighLevelClient.index(indexRequest);
        } catch (Exception e) {
            log.error("请求es进行新增失败, e:{}",  e.getStackTrace());
        }
    }

    public static String imgToBase64(String path){
        byte[] data = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            in = connection.getInputStream();
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while((len =in.read(b)) != -1){
                out.write(b,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(in != null ){
                    in.close();
                }
            }catch (IOException e){
                e.getStackTrace();
            }
        }
        System.out.println("转换后的图片大小："+out.toByteArray().length/1024);
        BASE64Encoder base = new BASE64Encoder();
        return base.encode(out.toByteArray());
    }
}

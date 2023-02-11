package yuan.study.demo.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dozer.DozerBeanMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import yuan.study.demo.entity.*;
import yuan.study.demo.service.DemoService;
import yuan.study.demo.utils.CopierUtil;
import org.springframework.data.geo.Point;
import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
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

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;

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
    public String cyclicBarrier(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("汇总1 ...");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("汇总2 ...");
        });
        for(int i = 0;i < 3;i ++) {
            new Thread(() -> {
                try {
                    Thread.sleep((long)(Math.random() * 2000));

                    int randomInt = new Random().nextInt(500);
                    System.out.println("hello " + randomInt);

                    cyclicBarrier.await();

                    System.out.println("world " + randomInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return "success";
    }

    @Override
    public String semaphore(){
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println(Thread.currentThread().getName() + " start...");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
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
            log.error("请求es进行新增失败, e:{}", e.getStackTrace());
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
        System.out.println("转换后的图片大小：" + out.toByteArray().length / 1024);
        BASE64Encoder base = new BASE64Encoder();
        return base.encode(out.toByteArray());
    }

    @Override
    public String sortingAlgorithm(){
        //冒泡排序
        bubbleSort();
        //选择排序
        selectionSort();
        //快速排序
        quickSort();
        //双轴快排
        dualPivotQuicksort();
        //三轴快排
        triplePivotQuicksort();
        //插入排序
        insertionSort();
        //双列插入排序
        pairInsertionSortByJDK();
        pairInsertionSort();
        //归并排序
        mergeSort();
        //堆排序
        heapSort();
        //希尔排序
        shellSort();
        //桶排序
        bucketSort();
        //计数排序
        countSort();
        //基数排序
        radixSort();
        return "success";
    }

    /**
     * 基数排序
     */
    private void radixSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("基数排序的结果为:{}", dataArray);
            return;
        }
        radixSort(dataArray);
        log.info("基数排序的结果为:{}", dataArray);
    }

    private int[] radixSort(int[] dataArray) {
        int mod = 10;
        int dev = 1;
        //获取最高位数
        int maxDigit = getMaxDigit(dataArray);
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < dataArray.length; j++) {
                int bucket = ((dataArray[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], dataArray[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    dataArray[pos++] = value;
                }
            }
        }
        return dataArray;
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        if (maxValue == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = maxValue; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    /**
     * 自动扩容，并保存数据
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * 计数排序
     */
    public void countSort() {
        int[] array = new int[]{5, 6, 11, 11, 12, 10, 7, 8, 9};
        // 找出数组A中的最大值、最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : array) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 初始化计数数组count
        // 长度为最大值减最小值加1
        int[] count = new int[max - min + 1];
        // 对计数数组各元素赋值
        for (int num : array) {
            // 数组中的元素要减去最小值，再作为新索引
            count[num - min]++;
        }
        // 创建结果数组
        int[] resArray = new int[array.length];
        // 创建结果数组的起始索引
        int index = 0;
        // 遍历计数数组，将计数数组的索引填充到结果数组中
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                // 再将减去的最小值补上
                resArray[index++] = i + min;
                count[i]--;
            }
        }
        log.info("计数排序的结果为:{}", resArray);
    }

    /**
     * 桶排序
     */
    private void bucketSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("桶排序的结果为:{}", dataArray);
            return;
        }
        bucketSort(dataArray);
        log.info("桶排序的结果为:{}", dataArray);
    }

    private void bucketSort(int[] dataArray) {
        // 找到最大值最小值
        int max = dataArray[0], min = dataArray[0];
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i] > max){
                max = dataArray[i];
            }
            if (dataArray[i] < min){
                min = dataArray[i];
            }
        }
        //获取桶的数量
        int[] bucketArray = new int[max - min + 1];
        for(int i = 0; i < dataArray.length; i++){
            bucketArray[dataArray[i] - min]++;
        }
        //遍历桶数组
        int index = 0;
        for(int i = 0; i < bucketArray.length; i++){
            for(int j = 0; j < bucketArray[i]; j++){
                dataArray[index++] = i;
            }
        }
    }

    /**
     * 双列插入排序(通用实现)
     */
    private void pairInsertionSort() {
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("双列插入排序(通用实现)排序的结果为:{}", dataArray);
            return;
        }
        int i, start = 0, end = 0, k, length = dataArray.length;
        int[] temp = new int[length];
        temp[0] = dataArray[0];
        for (i = 1; i < length; i ++){
            if (dataArray[i] < temp[start]){
                // 待插入元素比最小的元素小
                start = (start - 1 + length) % length;
                temp[start] = dataArray[i];
            } else if (dataArray[i] > temp[end]){
                // 待插入元素比最大元素大
                end = (end + 1 + length) % length;
                temp[end] = dataArray[i];
            } else {
                // 插入元素比最小大，比最大小
                k = (end + 1 + length) % length;
                //当插入值比当前值小时，需要移动当前值的位置
                while (temp[((k - 1) + length) % length] > dataArray[i]) {
                    temp[(k + length) % length] = temp[(k - 1 + length) % length];
                    k = (k - 1 + length) % length;
                }
                //插入该值
                temp[(k + length) % length] = dataArray[i];
                //因为最大值的位置改变，所以需要实时更新结尾的位置
                end = (end + 1 + length) % length;
            }
        }
        log.info("双列插入排序排序的结果为:{}", temp);
    }

    /**
     * 双列插入排序(JDK实现)
     */
    private void pairInsertionSortByJDK(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("双列插入排序排序的结果为:{}", dataArray);
            return;
        }
        int left = 0, right = dataArray.length - 1;
        //跳过了序列开头已有序的部分
        do {
            if (left >= right) {
                return;
            }
        } while (dataArray[++left] >= dataArray[left - 1]);

        for (int k = left; ++left <= right; k = ++left) {
            int a1 = dataArray[k], a2 = dataArray[left];

            if (a1 < a2) {
                a2 = a1;
                a1 = dataArray[left];
            }
            while (k > 0 && a1 < dataArray[--k]) {
                dataArray[k + 2] = dataArray[k];
            }
            dataArray[++k + 1] = a1;

            while (k > 0 && a2 < dataArray[--k]) {
                dataArray[k + 1] = dataArray[k];
            }
            dataArray[k + 1] = a2;
        }
        int last = dataArray[right];

        while (right > 0 && last < dataArray[--right]) {
            dataArray[right + 1] = dataArray[right];
        }
        dataArray[right + 1] = last;
        log.info("双列插入排序排序的结果为:{}", dataArray);
    }

    /**
     * 希尔排序
     */
    public void shellSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("希尔排序的结果为:{}", dataArray);
            return;
        }
        //增量长度
        double gap = dataArray.length;
        int dk, sentinel, k;
        while(true){
            //逐渐减小增量长度
            gap = (int)Math.ceil(gap / 2);
            //确定增量长度
            dk = (int)gap;
            for(int i = 0; i < dk; i++){
                //用增量将序列分割，分别进行直接插入排序。随着增量变小为1，最后整体进行直接插入排序
                for(int j = i + dk; j < dataArray.length; j = j + dk){
                    sentinel = dataArray[j];
                    k = j - dk;
                    while(k >= 0 && sentinel < dataArray[k]){
                        dataArray[k + dk] = dataArray[k];
                        k = k - dk;
                    }
                    dataArray[k + dk] = sentinel;
                }
            }
            //当dk为1的时候，整体进行直接插入排序
            if(dk == 1){
                break;
            }
        }
        log.info("希尔排序的结果为:{}", dataArray);
    }

    /**
     * 堆排序
     */
    private void heapSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("堆排序的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        //构建堆
        buildHeap(dataArray);
        int data;
        for(int i = dataArray.length - 1; i >= 0; i--){
            data = dataArray[0];
            dataArray[0] = dataArray[i];
            dataArray[i] = data;
            adjustHeap(dataArray, 0, i);
        }
        log.info("堆排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 构建堆
     */
    private void buildHeap(int[] dataArray){
        int parentNode = dataArray.length / 2 - 1;
        for(int i = parentNode; i >= 0; i--){
            //调整堆
            adjustHeap(dataArray, i, dataArray.length);
        }
    }

    /**
     * 调整堆
     */
    private void adjustHeap(int[] dataArray, int i, int len){
        if(i >= len) {
            return;
        }
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;
        if(c1 < len && dataArray[c1] > dataArray[max]){
            max = c1;
        }
        if(c2 < len && dataArray[c2] > dataArray[max]){
            max = c2;
        }
        if(max != i){
            int data = dataArray[max];
            dataArray[max] = dataArray[i];
            dataArray[i] = data;
            adjustHeap(dataArray, max, len);
        }
    }

    /**
     * 归并排序
     */
    private void mergeSort() {
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("归并排序的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        int[] temp = new int[dataArray.length];
        int[] data = mergeSort(dataArray, 0, dataArray.length - 1);
        log.info("归并排序的结果为:{}, 花费时间为:{}ms", data, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 归并排序
     */
    private int[] mergeSort(int[] data, int left, int right) {
        if(left < right){
            //找出中间索引
            int center = (left + right) / 2;
            //对左边数组进行递归
            mergeSort(data, left, center);
            //对右边数组进行递归
            mergeSort(data, center + 1, right);
            //合并
            return merge(data, left, center, right);
        }
        return new int[0];
    }

    /**
     * 合并
     */
    private int[] merge(int[] data, int left, int center, int right) {
        int [] tempArray = new int[data.length];
        int mid = center + 1, third = left, tmp = left;
        while(left <= center && mid <= right){
            //从两个数组中取出最小的放入中间数组
            if(data[left] <= data[mid]){
                tempArray[third++] = data[left++];
            }else{
                tempArray[third++] = data[mid++];
            }
        }
        //剩余部分依次放入中间数组
        while(mid <= right){
            tempArray[third++] = data[mid++];
        }
        while(left <= center){
            tempArray[third++] = data[left++];
        }
        //将中间数组中的内容复制回原数组
        while(tmp <= right){
            data[tmp] = tempArray[tmp++];
        }
        return data;
    }

    /**
     * 插入排序
     */
    private void insertionSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("插入排序的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        int temp;
        for (int i = 1; i < dataArray.length; i++) {
            for(int j = i; j > 0; j--){
                if(dataArray[j] < dataArray[j - 1]){
                    temp = dataArray[j];
                    dataArray[j] = dataArray[j - 1];
                    dataArray[j - 1] = temp;
                }else {
                    break;
                }
            }
        }
        log.info("插入排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 快速排序
     */
    public void quickSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        long startTimestamp = System.currentTimeMillis();
        //快速排序实现方法
        quickSortRealization(dataArray, 0, dataArray.length - 1);
        log.info("快速排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 快速排序实现方法
     */
    private void quickSortRealization(int[] dataArray, int lowIndex, int highIndex){
        if(lowIndex < highIndex){
            int part = partition(dataArray, lowIndex, highIndex);
            //递归调用, 处理其左序列
            quickSortRealization(dataArray, lowIndex,part - 1);
            //递归调用, 处理其右序列
            quickSortRealization(dataArray, part + 1, highIndex);
        }
    }

    /**
     * 划分方法
     */
    private int partition(int[] dataArray, int lowIndex, int highIndex){
        //使用 dataArray[low]作为枢轴元素
        int pivot = dataArray[lowIndex];
        //从两端交替向内扫描
        while(lowIndex < highIndex){
            while(lowIndex < highIndex && dataArray[highIndex] >= pivot) {
                highIndex--;
            }
            //将比 pivot 小的元素移向低端
            dataArray[lowIndex] = dataArray[highIndex];
            while(lowIndex < highIndex && dataArray[lowIndex] <= pivot){
                lowIndex++;
            }
            //将比 pivot 大的元素移向高端
            dataArray[highIndex] = dataArray[lowIndex];
        }
        //设置枢轴
        dataArray[lowIndex] = pivot;
        //返回枢轴元素位置
        return lowIndex;
    }

    /**
     * 双轴快排
     */
    private void dualPivotQuicksort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        long startTimestamp = System.currentTimeMillis();
        //快速排序实现方法
        dualPivotQuicksort(dataArray, 0, dataArray.length - 1);
        log.info("双轴快速排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    private void dualPivotQuicksort(int[] dataArray, int lowIndex, int highIndex){
        if(lowIndex < highIndex){
            int part = dualPivotPartition(dataArray, lowIndex, highIndex);
            //递归调用, 处理其左序列
            quickSortRealization(dataArray, lowIndex,part - 1);
            //递归调用, 处理其右序列
            quickSortRealization(dataArray, part + 1, highIndex);
        }
    }

    /**
     * 划分方法
     */
    private int dualPivotPartition(int[] dataArray, int left, int right){
        int data = dataArray[left];
        int lt = left + 1;
        int rt = right;
        while(true){
            while(lt <= rt && dataArray[lt] < data){
                lt++;
            }
            while(lt <= rt && dataArray[rt] > data){
                rt--;
            }
            if(lt > rt){
                break;
            }
            // 交换
            swap(dataArray, lt, rt);
            lt++;
            rt--;
        }
        // 交换
        swap(dataArray, left, rt);
        return rt;
    }

    /**
     * 三轴快排
     */
    private void triplePivotQuicksort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("三轴快排的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        dataArray = triplePivotQuicksort(dataArray, 0, dataArray.length - 1);
        log.info("三轴快排的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 三轴快排
     */
    public int[] triplePivotQuicksort(int[] arr, int left, int right){
        if (left >= right){
            return null;
        }

        int data = arr[left];
        int i = left + 1;
        int lt = left;
        int rt = right;

        while (i <= rt){
            if (arr[i] < data){
                swap(arr, lt, i);
                lt++;
                i++;
            }else if (arr[i] == data){
                i++;
            }else if (arr[i] > data){
                swap(arr, rt, i);
                rt--;
            }
        }

        triplePivotQuicksort(arr, left, lt - 1);
        triplePivotQuicksort(arr, rt + 1, right);
        return arr;
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序
     */
    public void selectionSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if (dataArray.length <= 1){
            log.info("选择排序的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        int temp, index;
        for (int i = 0; i < dataArray.length - 1; i++){
            temp = 0;
            //选择当前值为最小值索引
            index = i;
            for (int j = i + 1; j < dataArray.length; j++){
                if (dataArray[index] > dataArray[j]){
                    //修改最小值索引
                    index = j;
                }
            }

            temp = dataArray[index];
            dataArray[index] = dataArray[i];
            dataArray[i] = temp;
        }
        log.info("选择排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 冒泡排序
     */
    private void bubbleSort(){
        //获取随机数组
        int[] dataArray = getRandomArray();
        if(dataArray.length > 1){
            log.info("冒泡排序的结果为:{}", dataArray);
            return;
        }
        long startTimestamp = System.currentTimeMillis();
        int data;
        for(int i = 0; i < dataArray.length - 1; i++){
            for(int j = 0; j < dataArray.length - i - 1; j++){
                if(dataArray[j] > dataArray[j + 1]){
                    data = dataArray[j];
                    dataArray[j] = dataArray[j + 1];
                    dataArray[j + 1] = data;
                }
            }
        }
        log.info("冒泡排序的结果为:{}, 花费时间为:{}ms", dataArray, System.currentTimeMillis() - startTimestamp);
    }

    /**
     * 获取随机数组
     */
    private int[] getRandomArray(){
        int[] dataArray = new int[10];
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            dataArray[i] = random.nextInt(100);
        }
        return dataArray;
    }

    @Override
    public String bloomFilter(){
        int total = 100_0000;
        //默认的误判率为0.03D, 这里是0.0002, 误判率越低, 匹配精度越高, 但是存储空间越大
        BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total, 0.0002);
        //初始化total条数据到过滤器中
        for(int i = 0; i < total; i++){
            bf.put("" + i);
        }
        //判断值是否在存在过滤器中
        int count = 0;
        for(int i = 0; i < total + 1_0000; i++){
            if(bf.mightContain("" + i)){
                count++;
            }
        }
        System.out.println("已匹配数量: " + count);
        return "success";
    }

    @Override
    public String testRedis(){

        redisLimiter();

        redisLock();

        redisBit();

        redisHyperLogLog();

        redisGeo();

        String queue = "yuan-study-demo:blockingDeque";
        delayedQueue("设置value值", queue);
        getDelayQueue(queue);
        return "success";
    }

    private void redisLimiter(){
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("yuan-study-demo:redisLimiter");
        //分布式模式(也可换为单机模式), 每10s生成一个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 1, 10, RateIntervalUnit.SECONDS);
        if(rateLimiter.tryAcquire(1)){
            System.out.println("我拿到了一个令牌, 所以能执行");
        }else{
            System.out.println("令牌无了");
        }
        if(rateLimiter.tryAcquire(1)){
            System.out.println("我又拿到了一个令牌, 所以能执行");
        }else{
            System.out.println("令牌又无了");
        }
    }

    private void redisLock(){
        RLock lock = redissonClient.getLock("yuan.study.demo:redisLock");
        boolean flag = false;
        try {
            flag = lock.tryLock(2, 2, TimeUnit.SECONDS);
            if(flag){
                System.out.println("我拿到锁了!! 嘿嘿嘿");
            }
        } catch (Exception e) {
            log.error("redisLock 异常, e:{}", Throwables.getStackTraceAsString(e));
        }finally {
            if(flag){
                lock.unlock();
            }
        }
    }

    public <T> void getDelayQueue(String queueCode){
        try{
            RBlockingDeque blockingDeque = redissonClient.getBlockingDeque(queueCode);
            while (true){
                T value = (T) blockingDeque.take();
                log.info("getDelayQueue收到消息, 入参:{}, 结果:{}", queueCode, JSON.toJSONString(value));
            }
        }catch (Exception e){
            log.error("getDelayQueue执行异常, 入参:{}, e:{}", queueCode, Throwables.getStackTraceAsString(e));
        }
    }

    private void delayedQueue(String value, String queue){
        RBlockingDeque blockingDeque = redissonClient.getBlockingDeque(queue);
        RDelayedQueue delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        blockingDeque.expire(3, TimeUnit.SECONDS);
        delayedQueue.expire(3, TimeUnit.SECONDS);
        if(delayedQueue.size() > 1){
            log.error("队列已满, 丢弃数据, size={}", delayedQueue.size());
            return;
        }
        log.info("设置数据, size={}, value:{}", delayedQueue.size(), value);
        delayedQueue.offer(value, 2, TimeUnit.SECONDS);
    }

    private void redisBit(){
        String key = "yuan-study-demo:bit";
        redisTemplate.opsForValue().setBit(key, 1, true);
        redisTemplate.opsForValue().setBit(key, 3, true);
        redisTemplate.opsForValue().setBit(key, 5, true);
        System.out.println(redisTemplate.opsForValue().getBit(key, 1));
        System.out.println(redisTemplate.opsForValue().getBit(key, 2));
        System.out.println(redisTemplate.opsForValue().getBit(key, 3));
    }

    private void redisHyperLogLog(){
        String key = "yuan-study-demo:hyperLogLog";
        Random random = new Random();
        for(int i = 0; i < 1000; i++){
            redisTemplate.opsForHyperLogLog().add(key, random.nextInt(100));
        }
        System.out.println("HyperLogLog的长度为:" + redisTemplate.opsForHyperLogLog().size(key));

        String keyV2 = "yuan-study-demo:hyperLogLogV2";
        for(int i = 0; i < 1000; i++){
            redisTemplate.opsForHyperLogLog().add(keyV2, random.nextInt(200));
        }
        System.out.println("HyperLogLogV2的长度为:" + redisTemplate.opsForHyperLogLog().size(keyV2));
        String unionKey = "yuan-study-demo:hyperLogLog:union";
        redisTemplate.opsForHyperLogLog().union(unionKey, key, keyV2);
        System.out.println("HyperLogLog中union的长度为:" + redisTemplate.opsForHyperLogLog().size(unionKey));
    }

    private void redisGeo(){
        String key = "yuan-study-demo:geo";
        //添加成员
        Students student1 = new Students();
        student1.setId(1);
        student1.setName("王小明");
        redisTemplate.opsForGeo().add(key, new Point(114.085947,22.547), student1);

        Students student2 = new Students();
        student2.setId(2);
        student2.setName("李小狼");
        redisTemplate.opsForGeo().add(key, new Point(121.43333,34.50000), student2);

        Students student3 = new Students();
        student3.setId(3);
        student3.setName("张三");
        redisTemplate.opsForGeo().add(key, new Point(116.397128, 39.916527), student3);

        Distance distance = redisTemplate.opsForGeo().distance(key, student1, student2, Metrics.MILES);
        System.out.println("两个成员的距离:" + JSON.toJSONString(distance));

        List<Point> pointList = redisTemplate.opsForGeo().position(key, student1);
        System.out.println("student1的坐标为:" + JSON.toJSONString(pointList));

        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = redisTemplate.opsForGeo().radius(key, student1, new Distance(100, Metrics.KILOMETERS));
        System.out.println("100公里内同学的的坐标为:" + JSON.toJSONString(geoResults.getContent()));
    }

    @Override
    public String bridgeMethod(){
        try {
            //会获取的两个方法 - 协变返回值类型
            BridgeChild child = (BridgeChild)Class.forName("yuan.study.demo.entity.BridgeChild").newInstance();
            Method[] methods = child.getClass().getDeclaredMethods();
            for(Method method : methods){
                method.invoke(child, "啦啦啦");
            }

            //会获取的两个方法 - 类型擦除
            BridgeChild2 bridgeChild2 = (BridgeChild2)Class.forName("yuan.study.demo.entity.BridgeChild2").newInstance();
            methods = bridgeChild2.getClass().getDeclaredMethods();
            for(Method method : methods){
                method.invoke(bridgeChild2, "啦啦啦");
            }

            //获取到非桥接的方法
            Method method = BridgeMethodResolver.findBridgedMethod(bridgeChild2.getClass().getMethod("hello", String.class));
            method.invoke(bridgeChild2, "啦啦啦");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String classLoader(){
        try{
           File file = new File("E:\\JAVA\\demo");
           URI uri = file.toURI();
           URL url = uri.toURL();
           URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
           Class cla = urlClassLoader.loadClass("Test");
           cla.getDeclaredMethod("say").invoke(cla);

           MyClassLoader myClassLoader = new MyClassLoader("E:\\JAVA\\demo");
           Class<?> clazz = myClassLoader.loadClass("Test");
           //调用静态方法
           clazz.getDeclaredMethod("say").invoke(clazz);

           Object o = clazz.newInstance();
           Method print = clazz.getDeclaredMethod("print", String.class);
           print.invoke(o, "调用方法");

           System.out.println(clazz.getClassLoader());
           System.out.println(clazz.getClassLoader().getParent());
           System.out.println(clazz.getClassLoader().getParent().getParent());
           System.out.println(clazz.getClassLoader().getParent().getParent().getParent());
        }catch (Exception e){
            log.error("classLoader异常, e:{}", Throwables.getStackTraceAsString(e));
        }
        return "success";
    }

    @Override
    public String bitSet(){
        BitSet bitSet = new BitSet();
        bitSet.set(5, true);
        bitSet.set(6, true);
        bitSet.set(7, true);
        bitSet.clear(6);
        BitSet bitSet1 = bitSet.get(1,3);

        BitMap bitMap = new BitMap(1000);
        bitMap.setBit(100);
        bitMap.setBit(400);
        System.out.println(bitMap.getBit(100));
        bitMap.deleteBit(100);
        System.out.println(bitMap.getBit(100));
        return "success";
    }

    @Override
    public String readAndWriteExcel(){
        try {
            Workbook workbook = new HSSFWorkbook();
            FileOutputStream out = new FileOutputStream("E:\\迅雷下载\\这里\\demo.xlsx");
            Sheet sheet = workbook.createSheet("第一个sheet");
            Sheet sheet2 = workbook.createSheet("第二个sheet");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("A1");
            row.createCell(1).setCellValue("B1");
            row.createCell(2).setCellValue("C1");
            Row row2 = sheet.createRow(1);
            row2.createCell(0).setCellValue("A2");
            row2.createCell(1).setCellValue("B2");
            row2.createCell(2).setCellValue("C2");
            workbook.write(out);

            Workbook book = new HSSFWorkbook(new FileInputStream("E:\\迅雷下载\\这里\\demo.xlsx"));
            sheet = book.getSheetAt(0);
            for(int i = 0; i < sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                log.info("行号:{}, 值为:{},{},{}", i, row.getCell(0), row.getCell(1), row.getCell(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}

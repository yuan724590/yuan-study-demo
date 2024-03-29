package yuan.study.demo.service;


public interface DemoService {

    /**
     * threadLocal使用的demo
     */
    String threadLocal(String value);

    /**
     * countDownLatch使用的demo
     */
    String countDownLatch();

    /**
     * cyclicBarrier使用的demo
     */
    String cyclicBarrier();

    /**
     * semaphore使用的demo
     */
    String semaphore();

    /**
     * ReentrantLock+Condition使用的demo
     */
    String reentrantLockAndCondition();

    /**
     * lockSupport使用的demo
     */
    String lockSupport();

    /**
     * SimpleDateFormat同步获取时间
     */
    String syncGetTime();

    /**
     * localDateTime的使用
     */
    String localDateTime();

    /**
     * 创建对象
     */
    String createObject();

    /**
     * 属性可见
     */
    String accessible();

    /**
     * 两种构造器的区别
     */
    String getDeclaredConstructor();

    /**
     * 获取硬件信息
     */
    String getHardwareInformation();

    /**
     * 基本语法测试
     */
    String grammar();

    /**
     * string的intern方法测试
     */
    String stringIntern();

    /**
     * double测试
     */
    String doubleDemo();

    /**
     * volatile测试
     */
    String volatileDemo();

    /**
     * 属性拷贝工具的性能测试
     */
    String attributeCopy();

    /**
     * es图片检索工具测试
     */
    String esAliKnn();

    /**
     * 排序算法
     */
    String sortingAlgorithm();

    /**
     * 布隆过滤器
     */
    String bloomFilter();

    /**
     * 不常用redis的功能
     */
    String testRedis();

    /**
     * 桥接方法
     */
    String bridgeMethod();

    /**
     * 类加载器
     */
    String classLoader();

    String bitSet();

    /**
     * 读写excel
     */
    String readAndWriteExcel();

    /**
     * 使用springCache
     */
    String springCache();
}

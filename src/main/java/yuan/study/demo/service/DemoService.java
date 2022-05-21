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
}

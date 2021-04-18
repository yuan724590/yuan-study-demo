package yuan.study.demo.service;


public interface DemoService {

    /**
     * threadLocal使用的demo
     */
    String threadLocal(String value);

    /**
     * subList相关测试
     */
    String subList();

    /**
     * entrySet, keySet, foreach性能相关测试
     */
    String entrySet();

    /**
     * 几种map对比
     */
    String mapCompare();

    /**
     * 去重
     */
    String duplicateRemoval();

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
     * 查看内存消耗
     */
    String getMemory();
}

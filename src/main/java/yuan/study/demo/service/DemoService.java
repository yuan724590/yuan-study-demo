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
}

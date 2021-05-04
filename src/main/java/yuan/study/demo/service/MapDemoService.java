package yuan.study.demo.service;

/**
 * @author yuan
 * @Date 2021/4/18 20:10
 */
public interface MapDemoService {

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

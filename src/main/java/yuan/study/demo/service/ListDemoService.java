package yuan.study.demo.service;

/**
 * @author yuan
 * @Date 2021/4/18 20:10
 */
public interface ListDemoService {

    /**
     * subList相关测试
     */
    String subList();

    /**
     * arrayList相关测试
     */
    String arrayList();

    /**
     * 线程安全的list
     */
    String syncList();

    /**
     * 测试list的最大长度
     */
    String testMaxListSize();
}

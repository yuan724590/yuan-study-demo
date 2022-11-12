package yuan.study.demo.service;


public interface LockService {

    /**
     * 可重入锁的使用
     */
    String reentrantLock();

    /**
     * synchronized锁增加条件使用
     */
    String conditionSynchronized();
}

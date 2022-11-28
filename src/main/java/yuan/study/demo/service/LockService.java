package yuan.study.demo.service;


public interface LockService {

    /**
     * 可重入锁的使用
     */
    String reentrantLock();

    /**
     * 可重入读写锁的使用
     */
    String reentrantReadWriteLock();

    /**
     * synchronized锁增加条件使用
     */
    String conditionSynchronized();

    /**
     * stampedLock
     */
    String stampedLock();

    /**
     * lockSupport
     */
    String lockSupport();
}

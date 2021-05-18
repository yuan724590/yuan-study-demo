package yuan.study.demo.service;


public interface ErrorService {

    /**
     * threadLocal使用的demo
     */
    String stackOverflowError();

    /**
     * 栈溢出问题复现1
     */
    String stackOverflowError1();

    /**
     * 堆溢出问题复现
     */
    String heapSpaceOOM();

    /**
     * 通过线程造成泄露
     */
    String stackLeakByThread();

    /**
     * 运行时常量池OOM
     */
    String runtimeConstantPoolOOM();

    /**
     * 直接内存OOM
     */
    String directMemoryOOM();
}

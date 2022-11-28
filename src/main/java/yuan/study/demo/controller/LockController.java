package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.LockService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class LockController {

    @Resource
    private LockService lockService;

    /**
     * 可重入锁的使用
     */
    @GetMapping(value = "/reentrantLock")
    public String reentrantLock() {
        return lockService.reentrantLock();
    }

    /**
     * 可重入读写锁的使用
     */
    @GetMapping(value = "/reentrantReadWriteLock")
    public String reentrantReadWriteLock() {
        return lockService.reentrantReadWriteLock();
    }

    /**
     * synchronized锁增加条件使用
     */
    @GetMapping(value = "/condition/synchronized")
    public String conditionSynchronized() {
        return lockService.conditionSynchronized();
    }

    /**
     * stampedLock
     */
    @GetMapping(value = "/stampedLock")
    public String stampedLock() {
        return lockService.stampedLock();
    }

    /**
     * lockSupport
     */
    @GetMapping(value = "/lockSupport")
    public String lockSupport() {
        return lockService.lockSupport();
    }
}

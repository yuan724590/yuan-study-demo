package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.LockService;

import javax.annotation.Resource;

/**
 * @author yuan
 * @Date 2021/4/22 1:22
 */
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
     * synchronized锁增加条件使用
     */
    @GetMapping(value = "/condition/synchronized")
    public String conditionSynchronized() {
        return lockService.conditionSynchronized();
    }
}

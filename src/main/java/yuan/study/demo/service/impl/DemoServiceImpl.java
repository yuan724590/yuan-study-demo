package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.service.DemoService;


@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String threadLocal(String value) {
        //初始化
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        //设置值, 其中key为线程的hash,value为设置的值
        threadLocal.set(value);
        //直接get就行,仅在本线程中有效
        String result = threadLocal.get();
        //不使用时及时清除,防止造成内存问题
        threadLocal.remove();
        return result;
    }
}

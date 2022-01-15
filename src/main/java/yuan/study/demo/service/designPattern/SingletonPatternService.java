package yuan.study.demo.service.designPattern;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Instance;

@Service
@Component
public class SingletonPatternService {

    private volatile static Instance instance;

    /**
     * 双重校验锁
     */
    public static Instance getInstanceByDoubleLock() {
        if (instance == null) {
            synchronized (Instance.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }


    private static Instance lazyManInstance;

    /**
     * 懒汉模式
     */
    public static synchronized Instance getInstanceByLazyMan(){
        if(lazyManInstance == null){
            return new Instance();
        }
        return lazyManInstance;
    }


    private static Instance hungryManInstance = new Instance();

    /**
     * 饿汉模式
     */
    public static synchronized Instance getInstanceByHungryMan(){
        return hungryManInstance;
    }

    /**
     * 禁止外部实例化
     */
    private SingletonPatternService(){}
}

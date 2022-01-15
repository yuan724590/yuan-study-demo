package yuan.study.demo.service.designPattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;


public class CglibUtils {

    public static Object getProxy(Class cls){
        
        //创建CGLIB核心的类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(cls);
        //设置回调函数
        enhancer.setCallback((MethodInterceptor) (obj, method, args, methodProxy) -> {
            System.out.println("记录开始.......");
            Object object = methodProxy.invokeSuper(obj, args);
            System.out.println("记录结束.......");
            return object;
        });
        //生成代理对象
        return enhancer.create();
    }
}
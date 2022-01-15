package yuan.study.demo.service.designPattern.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class BuyCarHandler implements InvocationHandler {

    private Customer customer;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买车之前, 逛逛车店");
        //当代理对象调用真实对象的方法时，会自动跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(customer, args);
        System.out.println("买车之后, 逛逛保险");
        return result;
    }
}
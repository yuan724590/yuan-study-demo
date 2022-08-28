package yuan.study.demo.entity;


import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception e){
        System.out.printf("\n 开始执行afterReturning, e:%s \n", e);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e){
        //两种方式同时使用时, 只会使用本方法, 上个方法会因为key重复造成被覆盖
        //除非上个方法的参数与本方法的第四个参数不同, 如改为:public void afterThrowing(RuntimeException e){
        System.out.printf("\n 开始执行afterThrowing, method:%s, args:%s target:%s e:%s \n", method, Arrays.asList(args), target, e);
    }
}

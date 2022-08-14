package yuan.study.demo.service.aop.impl;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import yuan.study.demo.configuration.TestConfiguration;
import yuan.study.demo.service.aop.SpringService;
import yuan.study.demo.configuration.AspectJConfiguration;
import yuan.study.demo.service.aop.ServiceInterceptor;
import yuan.study.demo.service.designPattern.proxy.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Service
public class SpringServiceImpl implements SpringService {

    @Override
    public String dynamicProxy(){
        //常用的动态代理, 见yuan.study.demo.service.impl.DesignPatternServiceImpl.proxy

        BuyCar buyCar = (BuyCar)Proxy.newProxyInstance(BuyCar.class.getClassLoader(), new Class[]{BuyCar.class}, new InvocationHandler(){
           @Override
           public Object invoke(Object proxy, Method method, Object[] args){
               BuyCar buyCar = new BuyCarProxy(new Customer());
               return buyCar.buyAHundredCars((Integer) args[0]);
           }
        });
        System.out.println(buyCar.buyAHundredCars(10000));

        //==========
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> targetClass = classLoader.loadClass("yuan.study.demo.service.designPattern.proxy.BuyCar");
            Method targetMethod = ReflectionUtils.findMethod(targetClass, "buyAHundredCars", int.class);
            System.out.println(targetMethod);
        } catch (Exception e) {
            log.error("请求异常, e:{}", Throwables.getStackTraceAsString(e));
        }

        //==========
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> targetClass = classLoader.loadClass("yuan.study.demo.service.designPattern.proxy.BuyCar");
            ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
                @Override
                public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                    System.out.println("计算得到的方法为:" + method);
                }
            }, new ReflectionUtils.MethodFilter() {
                @Override
                public boolean matches(Method method) {
                    return method.getParameterTypes().length == 1 && int.class.equals(method.getParameterTypes()[0])
                            && String.class.equals(method.getReturnType()) && method.getExceptionTypes().length == 0;
                }
            });
        } catch (Exception e) {
            log.error("请求异常, e:{}", Throwables.getStackTraceAsString(e));
        }

        //==============
        // 需要增加@Aspect 和 @EnableAspectJAutoProxy注解
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestConfiguration.class);
        context.refresh();
        TestConfiguration demoService = context.getBean(TestConfiguration.class);
        demoService.testDemo();
        context.close();

        //==============
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("classpath:/spring-aop-context.xml");
        BuyCar customer = xmlContext.getBean("buyCarProxyFactoryBean", BuyCar.class);
        customer.buyCar(11111);
        xmlContext.close();

        //==============
        buyCar = new Customer();
        ProxyFactory proxyFactory = new ProxyFactory(buyCar);
        proxyFactory.addAdvice(new ServiceInterceptor());
        buyCar = (BuyCar) proxyFactory.getProxy();
        buyCar.buyAHundredCars(1111);
        return "success";
    }

    @Override
    public String aop(){
        //正常的注解驱动, 见yuan.cam.b.aspect.testAspect

        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("classpath:/spring-aop-context.xml");
        xmlContext.refresh();
        BuyCar customer = xmlContext.getBean("buyCarProxyFactoryBean", BuyCar.class);
        customer.buyCar(11111);
        xmlContext.close();

        //==============
        Map<String, String> map = new HashMap<>();
        AspectJProxyFactory factory = new AspectJProxyFactory(map);
        factory.addAspect(AspectJConfiguration.class);
        factory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.printf("\n 开始执行before, 获取的方式为:%s, 参数:%s \n", method.getName(), JSON.toJSONString(args));
            }
        });
        factory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.printf("\n 开始执行afterReturning, 获取的方式为:%s, 参数:%s \n", method.getName(), JSON.toJSONString(args));
            }
        });
        map.put("1", "2");
        Map<String, String> proxy = factory.getProxy();
        proxy.put("1", "3");
        System.out.println(proxy.get("1"));

        //==============
        xmlContext = new ClassPathXmlApplicationContext("classpath:/spring-aop-auto-proxy-context.xml");
        xmlContext.refresh();
        customer = xmlContext.getBean("customer", BuyCar.class);
        customer.buyCar(11111);
        xmlContext.close();
        return "success";
    }

    public void before(){
        System.out.println("执行下before");
    }

    public Object around(ProceedingJoinPoint point) {
        System.out.println("执行下around, method:" + point.getSignature());
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void after(){
        System.out.println("执行下after");
    }

    public void afterReturning(){
        System.out.println("执行下afterReturning");
    }

    public void afterThrowing(){
        System.out.println("执行下afterThrowing");
    }
}

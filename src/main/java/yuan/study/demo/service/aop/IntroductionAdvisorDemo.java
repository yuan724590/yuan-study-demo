package yuan.study.demo.service.aop;

import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import yuan.study.demo.service.designPattern.proxy.BuyCar;

import java.lang.reflect.Method;

public class IntroductionAdvisorDemo implements BuyCar, Comparable<IntroductionAdvisorDemo> {

    public static void main(String[] args) {
        IntroductionAdvisorDemo introductionAdvisorDemo = new IntroductionAdvisorDemo();
        //第一次使用构造器的时候, 会将目标对象所有实现的接口全部添加到interface中, 所以使用该构造器会使得IntroductionInfo失效
        //ProxyFactory proxyFactory = new ProxyFactory(introductionAdvisorDemo);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(introductionAdvisorDemo);
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before:" + method);
            }
        }, new IntroductionInfo() {
            @Override
            public Class<?>[] getInterfaces() {
                //限制类型
                return new Class[]{BuyCar.class, Comparable.class};
            }
        }));
        Object proxy = proxyFactory.getProxy();
        BuyCar buyCar = (BuyCar) proxy;
        buyCar.buyCar(10);

        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);
    }

    @Override
    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }

    @Override
    public void buyCar(int cash) {
        System.out.println("正在执行buyCar, cash:" + cash);
    }

    @Override
    public String buyAHundredCars(int cash) {
        return null;
    }
}

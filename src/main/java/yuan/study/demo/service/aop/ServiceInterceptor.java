package yuan.study.demo.service.aop;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("ServiceInterceptor 正在执行, invocation:" + JSON.toJSONString(invocation));
        return invocation.proceed();
    }
}

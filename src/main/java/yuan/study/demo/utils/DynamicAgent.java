package yuan.study.demo.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yuan
 * @Date 2021/10/11 23:19
 */
public class DynamicAgent {

    static class UserServiceImpl {
        public void addUser() {
            System.out.println("增加一个用户。。。");
        }

        public void editUser() {
            System.out.println("编辑一个用户。。。");
        }
    }

    static class UserServiceCglib implements MethodInterceptor {

        private Object target;

        public Object getInstance(Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.target.getClass());
            // 设置回调方法
            enhancer.setCallback(this);
            // 创建代理对象
            return enhancer.create();
        }

        /**
         * 实现MethodInterceptor接口中重写的方法
         * 回调方法
         */
        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("事务开始。。。");
            Object result = proxy.invokeSuper(object, args);
            System.out.println("事务结束。。。");
            return result;
        }
    }

    interface UserManageService{
        void addUser(String id, String password);
        void delUser(String id);
    }

    static class UserManageImpl implements UserManageService{

        @Override
        public void addUser(String id, String password) {
            System.out.println("调用了addUser方法,id:" + id + ", password:" + password);
        }

        @Override
        public void delUser(String id) {
            System.out.println("调用了delUser方法,id:" + id);
        }
    }

    static class JDKProxy implements InvocationHandler{

        private Object object;

        public Object newProxy(Object object){
            this.object = object;
            return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("调用了invoke方法");
            return method.invoke(this.object, args);
        }
    }

//    public static void main(String[] args) {
//        UserServiceCglib cglib = new UserServiceCglib();
//        UserServiceImpl bookFacedImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
//        bookFacedImpl.addUser();
//
//        JDKProxy jdkProxy = new JDKProxy();
//        UserManageService userManageService = (UserManageService) jdkProxy.newProxy(new UserManageImpl());
//        userManageService.addUser("1", "aa");
//    }

}

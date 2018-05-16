package com.yp.demo.TestDynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestProxy implements InvocationHandler {

    private Object realObject;

    public TestProxy(Object realObject){
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello world");
        Arrays.asList(args).forEach(arg -> System.out.println(arg));
        return method.invoke(realObject, args);
    }

    public static void main(String[] args) {
        ActionImpl realObject = new ActionImpl();
        Action proxy = (Action) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Action.class}, new TestProxy(realObject));
        proxy.doSomething("yp","10");
    }
}

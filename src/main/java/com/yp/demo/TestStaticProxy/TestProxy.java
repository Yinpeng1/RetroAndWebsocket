package com.yp.demo.TestStaticProxy;

public class TestProxy implements Action{
    private Action action;

    public TestProxy(Action action){
        this.action = action;
    }

    @Override
    public void doSomething() {
        System.out.println("=============");
        action.doSomething();
    }

    public static void main(String[] args) {
//        TestProxy testProxy = new TestProxy(new ActionImpl());
//        testProxy.doSomething();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis()+1);
        System.out.println(String.valueOf("_"+(System.currentTimeMillis()+1)+"="));
    }
}

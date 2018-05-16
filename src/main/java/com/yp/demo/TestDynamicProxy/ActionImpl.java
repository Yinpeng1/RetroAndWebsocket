package com.yp.demo.TestDynamicProxy;


public class ActionImpl implements Action {
    @Override
    public void doSomething(String name, String age) {
        System.out.println("你好"+name+"======"+age);
    }
}

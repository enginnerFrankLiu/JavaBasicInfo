package com.company;

import java.lang.reflect.Method;

public class ExceptionInfo {

    /**
     * 考虑，是否因为某个异常的抛出，决定我们是否要继续处理接下来的任务
     * 还是终止整个application呢?
     * 是否异常有可能恢复，或者尝试去恢复呢
     *
     * 利用java反射机制，可以在运行期间调用对象的任何方法；
     * 在运行期间调用任何对象的方法；
     */
    public void infoJ(){

        //可以在运行期间调用对象的任何方法;
        Society so=new Society();
        try {
            Method method=so.getClass().getDeclaredMethod("main");
            method.invoke(so);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

}

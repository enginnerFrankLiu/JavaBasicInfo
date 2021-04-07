package com.company;
import com.company.model.Son;
import sun.reflect.generics.scope.MethodScope;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ExceptionInfo {

    /**
     * 考虑，是否因为某个异常的抛出，决定我们是否要继续处理接下来的任务
     * 还是终止整个application呢?
     * 是否异常有可能恢复，或者尝试去恢复呢
     * <p>
     * 利用java反射机制，可以在运行期间调用对象的任何方法；
     * 在运行期间调用任何对象的方法；
     *
     * 那么由此可以获取的类，只有两个类，我真的是醉.
     * https://zhuanlan.zhihu.com/p/21423208.
     */
    public void infoJ() {

        //可以在运行期间调用对象的任何方法;
        Society so = new Society();
        try {
            Method method = so.getClass().getDeclaredMethod("main");
            method.invoke(so);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getSuperClassInfo() {
        Son son = new Son();
        Class cla = son.getClass().getSuperclass();
        while (cla != null) {
            System.out.println(cla.getName());
            cla= cla.getSuperclass();
        }
    }

    /**
     * get a lot information about class.
     * 写这些垃圾代码，卧槽
     */
    public void getInterface(){
        Son son=new Son();
        Class [] interfaceInfos=son.getClass().getInterfaces();
        for (Class interfaceInfo : interfaceInfos) {
            System.out.println(interfaceInfo.getName());
        }
    }

    /**
     * get all method info of something.
     */
    public void getAllMethodOfClass(){
        Son son=new Son();
        Method [] methods=son.getClass().getDeclaredMethods();
        for (Method method : methods) {
             System.out.println(method.getName());
        }
    }

    /**
     * 如果方法有参数，你就要指定参数.
     */
    public void getDeclareMethodInfo(){

        Class cla=new Son().getClass();
        try {
            Method  method=cla.getDeclaredMethod("getMoney",int.class,int.class,String.class);
            method.setAccessible(true);

            //返回值
            System.out.println("The type of method return:");
            Class returnType=method.getReturnType();
            System.out.println(returnType.getName());

            //参数类型信息
            System.out.println("get parameters type of method:");
            Class [] paramsTypeInfo=method.getParameterTypes();
            for (Class aClass : paramsTypeInfo) {
                System.out.println(aClass.getName());
            }

            //参数信息
            System.out.println("get parameters of method:");
            Parameter [] paramsInfo= method.getParameters();
            for (Parameter parameter : paramsInfo) {
                System.out.println(parameter.getName());
            }





        }catch (Exception exception){
            exception.printStackTrace();
        }


    }


}

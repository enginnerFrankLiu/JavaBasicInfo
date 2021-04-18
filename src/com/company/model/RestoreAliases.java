package com.company.model;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestoreAliases {
    private static Map<String, String> defaultAliases = new HashMap<String, String>();

    static {
        defaultAliases.put("Duke", "duke@sap.com");
        defaultAliases.put("Marsh", "marsh@sap.com");
    }

    public void exe() {
        try {
            Constructor contor = EmailAliases.class.getDeclaredConstructor(HashMap.class);
            contor.setAccessible(true);
            //用指定参数去构造类对象 利用反射.
            EmailAliases emailAliases = (EmailAliases) contor.newInstance(defaultAliases);
            emailAliases.printKeys();

        } catch (Exception exception) {

            System.out.println(exception);
        }
    }

    public void reflectInfo() {

        try {
            String name="marsh.liu";
            Class cla = Class.forName("com.company.model.Computer");
            Constructor contor=cla.getDeclaredConstructor(String.class);
            Computer computer=(Computer) contor.newInstance(name);
            System.out.println(computer.getName());

            System.out.println("-----get field value by reflect------");
            Field field=cla.getDeclaredField("name");
            field.setAccessible(true);
            Object val=field.get(computer);
            System.out.println(val);

            System.out.println("-----set field value by reflect-------");
            field.set(computer,"mimi.qi");
            System.out.println(computer.getName());

            System.out.println("------exe method by reflect-----------");
            Method method=cla.getDeclaredMethod("printInfo");
            method.invoke(computer);
            System.out.println("------over-----");

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public void foo1(Map<String,Son> map, List<String> list,String str){
        System.out.println("foo1");
    }

    public Map<Son,String> foo2(){
        System.out.println("foo2");
        return null;
    }

    public void foo3(){
        System.out.println("foo3");
    }

    public Integer foo4(){
        return 1;
    }

    /**
     * Type 是java 中所有类型的超级接口，包括:原类型 参数化类型 泛型数组类型 类型变量 基本类型
     * class 原类型与基本类型
     *  parameterizedType 参数化类型
     *  GenericArrayType  泛型数组类型
     *  TypeVariable 类型变量
     *  wildcard 通配符类型
     *
     *  都是为了我们的反射泛型这个话题二开展的
     *  Java 中的泛型仅仅是给编译器 javac 使用的，确保数据的安全性和免去强制类型转换的麻烦
     *  但是一旦编译完成，所
     * 有与泛型有关的类型全部擦除。 使用泛型直接读取泛型，是读取不到的，因为反射是操作加载以后的类的。
     */
    public void showInfo(){

        System.out.println("获取泛型参数列表");
        Class cla=RestoreAliases.class;
        try{
            System.out.println("---------获取参数的泛型类型---------");
            Method method=cla.getDeclaredMethod("foo1",Map.class,List.class,String.class);
            Type [] types=method.getGenericParameterTypes(); // 获取带泛型参数的类型
            System.out.println(types.length);
            for (Type type : types) {
                if(type instanceof ParameterizedType){
                    Type [] genericType =((ParameterizedType) type).getActualTypeArguments();

                    for (Type type1 : genericType) {
                        System.out.println(type1);
                    }

                }
            }

            System.out.println("--------- 获取泛型方法的返回值 -------------");
            Method method2= cla.getDeclaredMethod("foo2",null);
            Type returnType2=method2.getGenericReturnType();
            if(returnType2 instanceof ParameterizedType ){

                Type [] type2=((ParameterizedType)returnType2).getActualTypeArguments();
                for (Type type : type2) {
                    System.out.println("返回的泛型值类型:"+type);
                }
            }


            System.out.println("------获取五泛型的方法的返回值1------");
            Method method3=cla.getMethod("foo3");
            Type returnType=method3.getGenericReturnType();
            System.out.println(returnType instanceof  ParameterizedType);
            System.out.println("------获取五泛型的方法的返回值2------");
            Method method4=cla.getMethod("foo4");
            Type returnTypeJ=method4.getReturnType();
            System.out.println(returnTypeJ instanceof ParameterizedType);

        }catch (Exception exception){

        }

    }

    /**
     * 这个东西不是很好理解的
     * 1.undo log
     * 2.read view
     * mvcc
     * 如何实现 并发控制的 安全性的，
     * 正在一步一步的探究中.
     *
     *
     * 了解它最它最本质的问题:
     *
     * 1.并发控制的问题
     *
     * 1 case read read ，天生支持高并发，不需要控制
     *
     * 2 case write vs write 必须控制，否则会出现更'新丢失',控制方式，有且只有一种:排它锁,结果: 顺序的去执行update 操作；
     *
     * 3.
     *
     *  read -> write  你不能改变这个值;
     *
     *  write -> read
     *
     *  这个时候就要控制，如果不控制，就会出现 脏读，不可重复度，幻读 的危险；
     *
     *  用不同的隔离级别去掌控不同的case，隔离级别越低，并发性约好，读取数据的安全性越低
     *  隔离级别越高，数据安全性越高，并发性越高，
     *
     *  所以，我们是在 数据安全性和并发性之间做权衡，利用不同的隔级别

     */
    public void DBInfo(){

    }

    /**
     *今天继续我们的mvvc，卧槽
     * mvcc 依赖undo log 好 read view
     * 还有可见性算法，解决了我们RC 和 RR 的问题
     * 但最终还是没有解决我们的 幻读的问题，辣么，他是怎么解决幻读问题的呢
     *
     * 这个就是规则:
     *
     * MySQL 会根据以下规则来判断版本链中的哪个版本（记录）是在事务中可见的：
     *
     * trx_id < min_trx_id，那么该记录则在当前事务可见，因为修改该版本记录的事务在当前事务生成 Read View 之前就已经提交。
     *
     * trx_id in (rw_trx_ids)，那么该记录在当前事务不可见，因为需改该版本记录的事务在当前事务生成 Read View 之前还未提交。
     *
     * trx_id > max_trx_id，那么该记录在当前事务不可见，因为修改该版本记录的事务在当前事务生成 Read View 之前还未开启。
     *
     * trx_id = curr_trx_id，那么该记录在当前事务可见，因为修改该版本记录的事务就是当前事务。

     *
     * 在 INNODB 使用mvcc 和 next-key-lock 解决幻读的问题
     *
     * RC
     * RR
     *
     * 在RR的事务隔离级别下，数据库会使用next-key lock 锁来 锁住本条记录以及索引区间.
     * select * from table where id>3 锁住的就是id=3这条记录以及id>3这个区间范围，锁住索引记录之间的范围，避免范围间插入记录，以避免产生幻影行记录
     *
     *
     * RC
     * 在 READ COMMITTED 隔离级别下，会使用 MVCC。在开启一个读取事务之后，它会在每一个 select 操作之前都生成一个 Read View。
     *
     *
     * RR
     *
     * READ COMMITTED 是在每次执行 select 操作时，都会生成一个新的 Read View。而 REPEATABLE READ 只会在第一次执行 select 操作时生成一个 Read View，直到该事务提交之前，所有的 select 操作都是使用第一次生成的 Read View。
     *
     * SERIALIZABLE
     *
     *
     * 读写之间的并发性控制而言.
     */
    public void mvvcInMySql(){

    }

    /**
     * IO 多路复用
     * https://mp.weixin.qq.com/s/JHqVY02mMJIpuZ4s9XOrVg]
     *
     * TCP IP 多路复用技术
     */
    public void IOMulitUsed(){


    }


}

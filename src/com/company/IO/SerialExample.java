package com.company.IO;

import com.company.model.Human;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialExample {

    /**
     * 1.一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问
     * 2.transient 关键字只能修饰变量，而不能修饰方法和类，注意，本地变量不能被transient关键字修饰的，。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
     * 3.静态变量 不管是否被transient 修饰，均不能被序列化的啊；
     *
     * 个静态变量不管是否被transient修饰，均不能被序列化），反序列化后类中static型变量username的值为当前JVM中对应static变量的值
     *
     * 这个值是jvm 中的值，不是 反序列的的出来的值，卧槽；
     */
    public void testTransient() {

        Human human = new Human();
        human.setUserName("jimi");
        human.setPassWord("9127");

        System.out.println("read before Serializable ...");
        System.out.println(human.getUserName());
        System.out.println(human.getPassWord());

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\ser.txt";

        //write object into file (ser)
        try {

            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
            os.writeObject(human);
            os.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //read object from file ("");

        // 在反序列化之前改变username的值
        human.setUserName("jmwang........................(静态变量中的值)............................");


        try {
            ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(filePath));
            Human humanDeser=(Human) inputStream.readObject();
            System.out.println("read after ser/deser");
            System.out.println(humanDeser.getUserName());
            System.out.println(humanDeser.getPassWord());
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}

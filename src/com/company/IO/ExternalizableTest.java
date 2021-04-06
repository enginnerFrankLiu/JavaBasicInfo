package com.company.IO;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * 载入 验证 准备 解析 初始化
 *
 * 1.在动态绑定的情况下，解析阶段发生在初始化阶段之后
 *
 * 2.static final 修饰的变量被称作为常量，和类变量不同。常量一旦赋值就不会改变了
 *
 * 3.该阶段将常量次中的符合引用转化为直接引用
 *
 * 符号引用：以一组符合 来描述锁所引用的目标
 *
 * 符号引用以一组符号（任何形式的字面量，只要在使用时能够无歧义的定位到目标即可）来描述所引用的目标。
 *
 *  直接引用通过对符号引用进行解析，找到引用的实际内存地址
 *
 *  1.启动类加载器
 *  2.扩展类加载器
 *  3.应用类加载器
 *
 *  将其放在运行时数据区的方法区
 *
 *  加载：
 *  通过“类全名” 刘奇获取定义此类的二进制字节流
 *  通过“类全名” 来获取定义此类的二进制字节流
 *
 *  将字节流所代表的静态存储结构转换为方法区运行时数据结构
 * 在java堆中生成一个代表这个类的java.lang.Class对象，作为方法区这些数据的访问入口
 *
 * linkInfo:
 * https://juejin.cn/post/6844903910486835213
 *
 *
 * https://zhuanlan.zhihu.com/p/27215051
 *
 *
 * 该阶段将常亮池中的符号引用转化为直接引用；
 * 符号引用：以一组符号来描述锁引用的目标
 * 在编译时，java类并不知道所引用的类的实际地址，因此只能使用符号引用来代替；
 * 比如 com.Wanger 类引用了 com.Chenmo 类，编译时 Wanger 类并不知道 Chenmo 类的实际内存地址，因此只能使用符号 com.Chenmo
 *
 * 直接引用通过对符号引用进行解析，找到引用的实际内存地址。
 *
 * 启动类加载器（Bootstrap Class-Loader），加载 jre/lib 包下面的 jar 文件，比如说常见的 rt.jar。
 *
 * 扩展类加载器（Extension or Ext Class-Loader），加载 jre/lib/ext 包下面的 jar 文件
 *
 * 应用类加载器（Application or App Clas-Loader），根据程序的类路径（classpath）来加载 Java 类。
 *
 * 每个java类都维护着一个定义它的类的加载器的引用，
 *
 */
public class ExternalizableTest implements Externalizable {

    /**
     * 取决于你序列化的方式；
     */
    public transient String content="被transient修饰的变量，但是继承的是Externalizable,内部有自定义的序列化和发序列化的实现方式";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content=(String)in.readObject();
    }
}

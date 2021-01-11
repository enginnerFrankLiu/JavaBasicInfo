package com.company.IO;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
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

package com.company.jvm;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private static final List<String> DECOMMISSIONING_TENANT_IDS = new ArrayList<String>() {
        {
            add("035e298e-5b8b-4940-a156-3c5f43d5d676");
            add("dff5b4a9-fe61-456c-8b86-ae4a9778024d");
            add("328b1188-ba3d-45f9-bc35-cdec0dfa1d2b");
            add("1139278e-7b6a-458e-acbd-58eb3d370619");
            add("19ca5a64-d0ee-4450-9c6d-39ca2973fce0");
            add("7b82e4d1-97b8-4404-af86-c6019fa8c622");
            add("98f17ed4-043f-4d39-a6ca-643e9830e797");
            add("f2175878-d86f-4198-b8f4-941c4f278022");
            add("0fb9c09d-2f37-4a71-b812-a5e4eed2f784");
            add("28c21173-8a54-4ad6-b252-6b138ba12b41");
            add("f3832165-36f5-400a-86ca-a0112d37a129");
            add("f0f2eb2a-0d24-479f-af3d-194a865a9026");
            add("f108d1e0-6859-4c39-b53a-3e6a87cfb9b8");
            add("2b8f42ab-c01d-4a65-adf4-cae51c0a5f1d");
            add("f5e80a63-5ee2-46a1-840b-63f0f5f810c0");
            add("afa445e3-25ad-4391-850b-9fd6dfcbfc4a");
            add("87af553f-f4b4-4a0e-88c9-e297e685f0da");
            add("51eb63a2-e470-48be-80d0-99092e1c1392");
            add("73e367f1-e291-423b-a323-682653b3ea57");
            add("6fc56989-02bb-47e5-82ae-c4f99a2e1b1e");
            add("8ec5c7c0-70f9-4988-99b9-177f1094a123");
        }
    };

        /**
         * 1.我们没有new 出新的对象，所以我们不会触发
         * 构造函数的执行
         *
         * 2.java中没有静态构造函数的，但是却有我们静态代码区域，整体效果都是一样可行的，比较ok.
         *
         * 3.居然也没有调用我们son的静态代码块，
         *
         * 加载 验证 准备 解析 初始化 使用 卸载 等几个主要的过程，整体效果还算是ok.
         *
         * 最近都在看java jvm 闲逛的东西，所以并没有太多的代码提交;
         *
         */
        public void info() {
            System.out.println(Son.age);
        }

    /**
     * Because Double Brace Initialization (DBI) creates an anonymous class with a reference to the instance of the owning object,
     * its use can lead to memory leaks if the anonymous inner class is returned and held by other objects.
     * Even when there's no leak, DBI is so obscure that it's bound to confuse most maintainers.
     */
    public void testAnonyInit(){

            System.out.println(DECOMMISSIONING_TENANT_IDS);
        }
    }

package com.company.basic;
public class Rate {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Rate(String id){
        this.id=id;
    }


    public class log{

        public void info(){

            if(getId()=="1"){
                System.out.println("11");
            }else{
                System.out.println("22");
            }
        }
    }

    /**
     * 局部类不能用public或private，它的作用域被限定在生命这个局部类的块中。
     */
    public void writeInfo(){

        String version="versioninfo";

        class write{

            public void doInfo(){

                System.out.println("局部内部类，居然可以在方法体重创建 我们这个内");
                System.out.println("但是 这个类 不能被申明为public 或者 private，作用范围：仅限于方法体内部，卧槽");
            }
        }


        write w=new write();
        w.doInfo();

        System.out.println(version);

    }

}

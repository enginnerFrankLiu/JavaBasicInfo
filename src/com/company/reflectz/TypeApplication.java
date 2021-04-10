package com.company.reflectz;

import java.lang.reflect.*;
import java.util.Arrays;

public class TypeApplication<T extends Number> {

    public T field;


    public void showJJ() {

        try {
            Field field = TypeApplication.class.getField("field");

            Type type=field.getGenericType();

            if(type instanceof TypeVariable){

                System.out.println("获取泛型膜拜的通配符:");
                TypeVariable typeVariable=(TypeVariable)type;
                System.out.println(typeVariable.getName());

                System.out.println("获取泛型的上边界限制:");
                Type [] boundTypes=typeVariable.getBounds();
                for (Type boundType : boundTypes) {
                    System.out.println(boundType.toString());
                }
                GenericDeclaration genericDeclaration=typeVariable.getGenericDeclaration();

                ///GenericDeclaration接口继承AnnotatedElement接口，所以可以很方便的获取声明类型的注解信息
                System.out.println("TypeVariable.getGenericDeclaration方法：获取T类型的声明类型:");
                System.out.println(genericDeclaration.toString());

                System.out.println("TypeVariable.getAnnotatedBounds方法：jdk新增的方法，获取上界的AnnotateType类型");

                AnnotatedType [] annotatedTypesBounds=typeVariable.getAnnotatedBounds();
                for (AnnotatedType annotatedTypesBound : annotatedTypesBounds) {
                    //这个居然是个空数组.
                    System.out.println(Arrays.toString(annotatedTypesBound.getAnnotations()));
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }


    }

}

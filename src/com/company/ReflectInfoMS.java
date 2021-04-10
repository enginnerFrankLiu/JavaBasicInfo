package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * java 中的type
 * 原始与基本类型：class
 * 参数化类型:ParameterizedType 表示一种参数化的类型如: Collection<String> 可以获取string的信息
 * 泛型数组类型 GenericArrayType 泛型数组类型
 * 类型变量:TypeVariable  各种类型变量的公共父接口
 * 通配符:WildcardType 代表一种通配符类型表达式:-> 比如 extend number? super integer
 *
 * 重点大概就是这两种，
 * 1.ParameterizedType
 *    参数化类型，如:Map<String, Integer>
 * 2.GenericArrayType
 *    泛型数组类型,如:List<String> [].(Integer [] 这个不是的)
 */
public class ReflectInfoMS {

    public Map<String, Integer> field;

    //还有这种list 类型的 数组
    public List<String> [] list;

    public Integer [] house;

    public void showParameterizedType() {
        try {
            Field field = ReflectInfoMS.class.getField("field");
            Type fieldType = field.getGenericType();
            if (fieldType instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) fieldType;
                //getActualTypeArguments 方法类获取 map 中 k v 实际类型;
                for (Type actualType : pType.getActualTypeArguments()) {
                    System.out.println(actualType.toString());
                }
                //获取原始的类型
                Type rawType = pType.getRawType();
                System.out.println(rawType.toString());

                Type ownType = pType.getOwnerType();
                System.out.println(ownType);
            }
        } catch (Exception exception) {

            System.out.println(exception);
        }
    }

    public void showGenericArrayType() {
        try {
//            Field fieldOfList = ReflectInfoMS.class.getField("list");
            Field fieldOfList = ReflectInfoMS.class.getField("house");
            Type fieldType = fieldOfList.getGenericType();
            if (fieldType instanceof GenericArrayType) {

                GenericArrayType genericArrayType = (GenericArrayType) fieldType;
                Type componentType = genericArrayType.getGenericComponentType();
                System.out.println(componentType instanceof ParameterizedType);


                //既然是一个parameterized类型，那么我就可以继续获取其中的类型
                System.out.println("既然是一个parameterized类型，那么我就可以继续获取其中的类型");
                ParameterizedType parameterizedType=(ParameterizedType)componentType;

                for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                    System.out.println(actualTypeArgument.toString());
                }

                System.out.println("get raw type");

                Type rawType=parameterizedType.getRawType();
                System.out.println(rawType.toString());


                System.out.println("owerType:");
                Type ownerType=parameterizedType.getOwnerType();

                System.out.println(ownerType);

            }else{
                System.out.println("no");
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

}

package com.company;

import javax.net.ssl.SSLContext;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class Main {

    /**
     *
     */
    static void Fuck() {

        System.out.println("");

        for (SeasonEnum value : SeasonEnum.values()) {

            System.out.println("enum name:" + value.name());
        }

        System.out.println("---------------------------");

        for (SeasonEnum value : SeasonEnum.values()) {
            System.out.println("enum index:" + value.ordinal());

        }

        System.out.println("---------------------------");
        System.out.println("valueOf(String name) 由名称获取枚举类中定义的常量");
        for (SeasonEnum value : SeasonEnum.values()) {
            System.out.println(SeasonEnum.valueOf(value.toString()));

        }
        //it is quick useful and interesting information.
        System.out.println("---------------------------");
        System.out.println("values() return array of seasons info.");
        SeasonEnum[] seasons = SeasonEnum.values();
        for (SeasonEnum season : seasons) {
            System.out.println(season.toString());
        }
    }

    /**
     * demetermination order status whether it is finished.
     *
     * @param orderStatus
     * @return
     */
    static boolean isFinished(int orderStatus) {
        return (OrderStatusEnum.FINISH.getIndex() == orderStatus);
    }

    static void notifyByOrderStatus(OrderStatusEnum orderStatusEnum) {
        switch (orderStatusEnum) {
            case UNPAID:
                System.out.println("老板，你下单了，赶紧付钱吧");
                break;
            case PAID:
                System.out.println("我已经付钱啦");
                break;
            case SEND:
                System.out.println("已发货");
                break;
            case FINISH:
                System.out.println("订单完成啦");
                break;
        }
    }

    /**
     * the differnent way to init enum sets.
     */
    static void infoMS() {
        EnumSet<SeasonEnum> seasonEnums1, seasonEnums2, seasonEnums3, seasonEnums4;
        seasonEnums1 = EnumSet.of(SeasonEnum.SPRING, SeasonEnum.SUMMER);
        seasonEnums2 = EnumSet.complementOf(seasonEnums1);
        seasonEnums3 = EnumSet.allOf(SeasonEnum.class);
        seasonEnums4 = EnumSet.range(SeasonEnum.SPRING, SeasonEnum.WINTER);
        System.out.println(seasonEnums1);
        System.out.println("-----------");
        System.out.println(seasonEnums2);
        System.out.println("-----------");
        System.out.println(seasonEnums3);
        System.out.println("-----------");
        System.out.println(seasonEnums4);
    }

    /**
     *
     */
    static void enumMapInfo() {
        Map<SeasonEnum, String> enumMap = new EnumMap<SeasonEnum, String>(SeasonEnum.class);
        enumMap.put(SeasonEnum.SPRING, "春天");
        enumMap.put(SeasonEnum.WINTER, "冬天");
        enumMap.put(SeasonEnum.FALL, "秋天");
        enumMap.put(SeasonEnum.SUMMER, "夏天");
        System.out.println(enumMap);
    }

    /**
     * information for test function.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("application start.");
        // Fuck();
        // notifyByOrderStatus(OrderStatusEnum.SEND);
//        infoMS();
//        enumMapInfo();
        System.out.println("application end.");
    }

}

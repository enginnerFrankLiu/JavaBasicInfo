package com.company;

/**
 * enum;
 */
public enum OrderStatusEnum implements ISeasonBehaviour {

    UNPAID(0, "未付款"), PAID(1, "已付款"), SEND(2, "已发货"), FINISH(3, "已完成");

    private int index;

    private String description;

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return description;
    }

    OrderStatusEnum(int index, String description) {
        this.index = index;
        this.description = description;
    }

    @Override
    public void showSeasonBeauty() {
        System.out.println("enum type can implement some interface to extend some new function.");
    }

    @Override
    public String getSeasonName() {
        return "to do../";
    }
}

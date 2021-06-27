package com.company.basic;

public class Label {

    /**
     * 真正对外暴露的属性
     * 前两个是必须填充的属性
     * <p>
     * 后面的是可选参数
     */
    private final int id;
    private final String name;

    private final String remark;

    public static class Builder {

        private final int id;
        private final String name;

        private String remark;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder Remark(String val) {
            remark = val;
            return this;
        }

        public Label build() {
            return new Label(this);
        }

    }

    private Label(Builder builder) {
        this.remark = builder.remark;
        this.id = builder.id;
        this.name = builder.name;

    }

    @Override
    public String toString() {
        String message = "id:" + this.id + " name:" + this.name + " remark:" + this.remark;
        return message;
    }
}

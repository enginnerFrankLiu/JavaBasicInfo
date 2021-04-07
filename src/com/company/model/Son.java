package com.company.model;

public class Son extends Parent implements IAction {
    @Override
    public void fuck() {
        System.out.println("implement fuck method info q.");
    }

    /**
     * @return
     */
    private String getMoney(int a, int b, String c) {
        String result = String.valueOf(a + b);
        String val = result + c;
        return val;
    }

    private String getInfo(){
        return "information";
    }

}

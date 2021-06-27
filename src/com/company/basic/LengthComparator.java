package com.company.basic;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {

    /**
     * 调整两个顺序 可以 决定是从 正序 还是倒序
     * @param first
     * @param second
     * @return
     */
    public int compare(String first, String second) {
        return second.length() - first.length();
    }
}

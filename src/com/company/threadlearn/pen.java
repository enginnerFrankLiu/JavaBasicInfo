package com.company.threadlearn;

import java.lang.annotation.Repeatable;

@Repeatable(pens.class)
public @interface pen {
    String role() default "information tech.";
}

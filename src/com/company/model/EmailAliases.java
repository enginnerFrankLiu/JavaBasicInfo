package com.company.model;

import java.util.HashMap;
import java.util.Set;

public class EmailAliases {
    private Set<String> aliases;

    private EmailAliases(HashMap<String, String> h) {
        aliases = h.keySet();
    }

    public void printKeys() {
        System.out.format("Mail keys :%n");
        for (String alias : aliases) {
            System.out.println(alias);
        }
    }

}

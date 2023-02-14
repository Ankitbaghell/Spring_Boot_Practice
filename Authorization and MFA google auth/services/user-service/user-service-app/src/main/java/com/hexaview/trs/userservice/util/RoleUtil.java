package com.hexaview.trs.userservice.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoleUtil {

    public static final String ADMIN = "Admin";

    public static final String USER = "User";

    private final Map<String, Set<String>> roleMap;

    private final Set<String> ignoreUrl;

    public RoleUtil(){
        roleMap = new HashMap<>();
        ignoreUrl = new HashSet<>();
        initMap();
    }

    private void initMap() {
        ignoreUrl.add("users/");
        roleMap.put(USER, ignoreUrl);
    }

    public boolean isIgnored(String url) {
        for (String key : ignoreUrl) {
            if (url.contains(key)) {
                return true;
            }
        }
        return false;
    }
}

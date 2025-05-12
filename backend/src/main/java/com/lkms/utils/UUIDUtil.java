package com.lkms.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String getUUIDString() {
        String uuid = "";
        UUID ranuuid = UUID.randomUUID();
        uuid = ranuuid.toString();
        return uuid;
    }

}


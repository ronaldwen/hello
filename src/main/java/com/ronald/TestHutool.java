package com.ronald;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.UUID;

public class TestHutool {
    public static void main(String[] args) {
        TestHutool th = new TestHutool();
        th.testUUID();

    }

    public void testUUID(){
        UUID uuid = UUID.fastUUID();
        Console.log(uuid.toString(true));
    }
}

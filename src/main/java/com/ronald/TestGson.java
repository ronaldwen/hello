package com.ronald;

import com.google.gson.Gson;

import java.util.Map;

public class TestGson {

    private String jsonStr = "{   \"orgid\":1,   \"orginal\": {     \"a\":1,     \"b\":2   } }";

    public static void main(String[] args){
        System.out.print(1);
        TestGson tg = new TestGson();
        tg.test();
    }

    public void test(){
        Gson gson = new Gson();
        Notice notice = gson.fromJson(jsonStr, Notice.class);
        System.err.println(notice);
    }

    class Notice{
        String orgid;
        Map<String, String> orginal;


    }
}

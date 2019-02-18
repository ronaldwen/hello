package com.ronald;

import java.util.List;
import java.util.Map;

public class TestJson {


    /**
     * data : {"MachIdList":[{"id":363,"text":"#1"},{"id":364,"text":"#4"},{"id":505,"text":"#13"}],"WorkGroup":[{"id":49,"text":"白班"},{"id":50,"text":"夜班"}]}
     * err : 0
     * msg : 操作成功
     * state : true
     * tip : -1
     */

    private Map<String, List<SelBean>> data;
    private int err;
    private String msg;
    private boolean state;
    private int tip;

    public Map<String, List<SelBean>> getData() {
        return data;
    }

    public void setData(Map<String, List<SelBean>> data) {
        this.data = data;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }


    public static class SelBean {

        private int id;
        private String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}

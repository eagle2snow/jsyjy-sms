package com.jsyjy.sms.pojo;

public class TStudent {
    private Integer id;

    private String sName;

    private String sSex;

    private Integer sCid;

    private Integer sTid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex == null ? null : sSex.trim();
    }

    public Integer getsCid() {
        return sCid;
    }

    public void setsCid(Integer sCid) {
        this.sCid = sCid;
    }

    public Integer getsTid() {
        return sTid;
    }

    public void setsTid(Integer sTid) {
        this.sTid = sTid;
    }
}
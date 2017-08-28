package com.jyh.com.jyh.EntityClass;

/**
 * Created by vvguoliang on 2017/8/25.
 * <p>
 * 个人资料实体类数据
 */

public class PersonalDataEntity {

    private int url_path = 0;

    private String titil = "";

    private String bottom = "";

    private String stater = "";

    private String other = "";

    public PersonalDataEntity(int url_path, String titil, String bottom, String stater, String other) {
        this.bottom = bottom;
        this.other = other;
        this.stater = stater;
        this.titil = titil;
        this.url_path = url_path;
    }

    public int getUrl_path() {
        return url_path;
    }

    public void setUrl_path(int url_path) {
        this.url_path = url_path;
    }

    //    public String getUrl_path() {
//        return url_path;
//    }
//
//    public void setUrl_path(String url_path) {
//        this.url_path = url_path;
//    }

    public String getTitil() {
        return titil;
    }

    public void setTitil(String titil) {
        this.titil = titil;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getStater() {
        return stater;
    }

    public void setStater(String stater) {
        this.stater = stater;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

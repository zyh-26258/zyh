package com.enetiy;

/**
 * Student实体类
 * 目的是为查询学生信息提供一个容器
 * 实体类当中的属性是和student表当中的列名一一进行对应的
 */
public class Student {
    //学生id
    private Integer id;
    //学生姓名
    private String name;
    //学生性别
    private String sex;
    //学生民族
    private String minZu;
    //学生所在系
    private String suoZaiXi;
    //学生专业名
    private String ZhuanYeMing;
    private String banJi;

    public Student() {
    }

    public Student(Integer id, String name, String sex, String minZu, String suoZaiXi, String zhuanYeMing, String banJi) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.minZu = minZu;
        this.suoZaiXi = suoZaiXi;
        ZhuanYeMing = zhuanYeMing;
        this.banJi = banJi;
    }

    public String getBanJi() {
        return banJi;
    }

    public void setBanJi(String banJi) {
        this.banJi = banJi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMinZu() {
        return minZu;
    }

    public void setMinZu(String minZu) {
        this.minZu = minZu;
    }

    public String getSuoZaiXi() {
        return suoZaiXi;
    }

    public void setSuoZaiXi(String suoZaiXi) {
        this.suoZaiXi = suoZaiXi;
    }

    public String getZhuanYeMing() {
        return ZhuanYeMing;
    }

    public void setZhuanYeMing(String zhuanYeMing) {
        ZhuanYeMing = zhuanYeMing;
    }
}

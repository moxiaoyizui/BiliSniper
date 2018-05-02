package com.yang.bilisniper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Entity
@Table(name = "member")
public class Member {
    @Id
    private Long mid;

    @Column
    private String name;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private boolean approve;

    private String sex;

    private Integer rank;

    private String face;    //头像

    private Integer DisplayRank;

    private Long regtime;

    private String birthday;

    private String place;

    private String description;

    private String article;

    private String sign;

    private String toutu;

    private Integer toutuId;

    private String theme;

    private String theme_preview;

    private Integer coins;

    private String im9_sign;

    private Long playNum;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private boolean fans_badge; //是否开启粉丝专属勋章

    private Integer follwer;    //粉丝数

    private Integer following;  //关注数

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getDisplayRank() {
        return DisplayRank;
    }

    public void setDisplayRank(Integer displayRank) {
        DisplayRank = displayRank;
    }

    public Long getRegtime() {
        return regtime;
    }

    public void setRegtime(Long regtime) {
        this.regtime = regtime;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getToutu() {
        return toutu;
    }

    public void setToutu(String toutu) {
        this.toutu = toutu;
    }

    public Integer getToutuId() {
        return toutuId;
    }

    public void setToutuId(Integer toutuId) {
        this.toutuId = toutuId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme_preview() {
        return theme_preview;
    }

    public void setTheme_preview(String theme_preview) {
        this.theme_preview = theme_preview;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getIm9_sign() {
        return im9_sign;
    }

    public void setIm9_sign(String im9_sign) {
        this.im9_sign = im9_sign;
    }

    public Long getPlayNum() {
        return playNum;
    }

    public void setPlayNum(Long playNum) {
        this.playNum = playNum;
    }

    public boolean isFans_badge() {
        return fans_badge;
    }

    public void setFans_badge(boolean fans_badge) {
        this.fans_badge = fans_badge;
    }

    public Integer getFollwer() {
        return follwer;
    }

    public void setFollwer(Integer follwer) {
        this.follwer = follwer;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "Member{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", approve=" + approve +
                ", sex='" + sex + '\'' +
                ", rank=" + rank +
                ", face='" + face + '\'' +
                ", DisplayRank=" + DisplayRank +
                ", regtime=" + regtime +
                ", birthday=" + birthday +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", article='" + article + '\'' +
                ", sign='" + sign + '\'' +
                ", toutu='" + toutu + '\'' +
                ", toutuId=" + toutuId +
                ", theme='" + theme + '\'' +
                ", theme_preview='" + theme_preview + '\'' +
                ", coins=" + coins +
                ", im9_sign='" + im9_sign + '\'' +
                ", playNum=" + playNum +
                ", fans_badge=" + fans_badge +
                ", follwer=" + follwer +
                ", following=" + following +
                '}';
    }
}

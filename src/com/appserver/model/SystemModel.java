package com.appserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by shikee_app03 on 16/4/20.
 */
@Entity
public class SystemModel {

    private long id;
    private String   version;
    private String[] bannerUrls;
    private String[] launchImageUrl;
    private int      launchImageShowTime;
    private String[] hotKeywork;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String[] getBannerUrls() {
        return bannerUrls;
    }
    public void setBannerUrls(String[] bannerUrls) {
        this.bannerUrls = bannerUrls;
    }
    public String[] getLaunchImageUrl() {
        return launchImageUrl;
    }
    public void setLaunchImageUrl(String[] launchImageUrl) {
        this.launchImageUrl = launchImageUrl;
    }
    public int getLaunchImageShowTime() {
        return launchImageShowTime;
    }
    public void setLaunchImageShowTime(int launchImageShowTime) {
        this.launchImageShowTime = launchImageShowTime;
    }
    public String[] getHotKeywork() {
        return hotKeywork;
    }
    public void setHotKeywork(String[] hotKeywork) {
        this.hotKeywork = hotKeywork;
    }
}

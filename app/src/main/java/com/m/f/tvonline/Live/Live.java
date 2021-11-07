package com.m.f.tvonline.Live;


public class Live {

  public String live_name;
  public String path;

    public Live(String live_name, String path) {
        this.live_name = live_name;
        this.path = path;
    }

    public String getLive_name() {
        return live_name;
    }

    public String getPath() {
        return path;
    }

    public void setLive_name(String live_name) {
        this.live_name = live_name;
    }

    public void setPath(String path) {
        this.path = path;
    }



}

package com.springCloudAd.constant;


import lombok.Getter;

@Getter
public enum CreativeTyoe {

    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");

    private int type;
    private String desc;

    CreativeTyoe(int type,String desc){
        this.type=type;
        this.desc=desc;
    }

}

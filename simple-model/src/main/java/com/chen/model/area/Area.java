package com.chen.model.area;

import com.chen.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Area extends BaseModel {

    @Column(columnDefinition = STRING + "'城市编码'")
    private String code;
    @Column(columnDefinition = STRING + "'行政区名称'")
    private String name;
    @Column(columnDefinition = STRING + "'中心点'")
    private String center;
    @Column(columnDefinition = STRING + "'行政区划级别'")
    private String level;

    @ManyToOne
    private Area parent; // 上级

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }
}

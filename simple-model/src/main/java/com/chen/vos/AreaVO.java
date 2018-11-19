package com.chen.vos;

import com.chen.model.area.Area;

public class AreaVO extends OneData {

    private Long areaId;
    private String code;
    private String name;
    private String center;
    private String level;
    private Long parentId;

    public AreaVO() {}

    public AreaVO(Area area) {
        super(area.getId());
        this.areaId = area.getId();
        this.code = area.getCode();
        this.name = area.getName();
        this.center = area.getCenter();
        this.level = area.getLevel();
        this.parentId = area.getParent() != null ? area.getParent().getId() : null;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

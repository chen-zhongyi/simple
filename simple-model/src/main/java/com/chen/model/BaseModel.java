package com.chen.model;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public class BaseModel implements Serializable {

    public static final String STRING = "varchar(255) comment ";
    public static final String STRING_1000 = "varchar(1000) comment ";
    public static final String STRING_2000 = "varchar(2000) comment ";
    public static final String STRING_3000 = "varchar(3000) comment ";
    public static final String STRING_4000 = "varchar(4000) comment ";
    public static final String STRING_5000 = "varchar(5000) comment ";
    public static final String STRING_TEXT = "longtext comment ";
    public static final String LONG = "bigint comment ";
    public static final String INTEGER = "int comment ";
    public static final String FLOAT = "float comment ";
    public static final String DOUBLE = "double comment ";
    public static final String BOOLEAN = "tinyint(1) comment ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = LONG + "'ID'")
    private Long id;

    @Version
    @Column(columnDefinition = LONG + "'版本'")
    private long version;

    @Column(columnDefinition = BOOLEAN + "'是否被删除'")
    private boolean deleted = false;

    @Column(columnDefinition = LONG + "'创建时间'")
    private long createTime = System.currentTimeMillis();

    @Column(columnDefinition = LONG + "'修改时间'")
    private long updateTime = System.currentTimeMillis();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}

package com.zc.generator.domain;

import cn.hutool.core.util.ObjectUtil;
import com.zc.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * ry 数据库表
 *
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    private String tableName;

    private String vueTableName;

    /**
     * 表描述
     */
    private String tableComment;

    private String engine;

    private String tableCollation;

    /**
     * 表的主键列信息
     */
    private transient ColumnInfo primaryKey;

    /**
     * 表的列名(不包含主键)
     */
    private transient List<ColumnInfo> columns;

    /**
     * 类名(第一个字母大写)
     */
    private String className;

    /**
     * 类名(第一个字母小写)
     */
    private String classname;

    public ColumnInfo getColumnsLast() {
        ColumnInfo columnInfo = null;
        if (ObjectUtil.isNotNull(columns) && !columns.isEmpty()) {
            columnInfo = columns.get(0);
        }
        return columnInfo;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

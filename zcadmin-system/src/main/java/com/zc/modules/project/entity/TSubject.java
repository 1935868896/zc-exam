package com.zc.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableLogic;
/**
 * 年级表 t_subject
 *
 * @author zhangc
 * @date 2021-09-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="年级",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TSubject extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="学科名称",name="name")
    private String name;

    @ApiModelProperty(value="年级",name="level")
    private Integer level;

    @ApiModelProperty(value="年级名",name="levelName")
    private String levelName;

    @ApiModelProperty(value="排序",name="itemOrder")
    private Integer itemOrder;
    @TableLogic
    @ApiModelProperty(value="逻辑删除",name="isDelete")
    private Boolean isDelete;

}

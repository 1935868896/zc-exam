package com.zc.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 文本表 t_text_content
 *
 * @author zhangc
 * @date 2021-09-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="文本",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TTextContent extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="文本内容",name="content")
    private String content;

}

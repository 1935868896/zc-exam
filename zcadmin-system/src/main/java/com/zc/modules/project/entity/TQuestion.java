package com.zc.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 问题表 t_question
 *
 * @author zhangc
 * @date 2021-09-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="问题",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TQuestion extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="1.单选题 2.多选题 3.判断题 4.填空题 5.简答题",name="questionType")
    private Integer questionType;

    @ApiModelProperty(value="学科",name="subjectId")
    private Integer subjectId;

    @ApiModelProperty(value="分数",name="score")
    private Integer score;

    @ApiModelProperty(value="级别",name="gradeLevel")
    private Integer gradeLevel;

    @ApiModelProperty(value="题目难度",name="difficult")
    private Integer difficult;

    @ApiModelProperty(value="正确答案",name="correct")
    private String correct;

    @ApiModelProperty(value="题目 填空、 题干、解析、答案等信息",name="infoTextContentId")
    private Integer infoTextContentId;

    @ApiModelProperty(value="创建人",name="createUser")
    private Integer createUser;

    @ApiModelProperty(value="1.正常",name="status")
    private Integer status;
    @TableLogic
    @ApiModelProperty(value="逻辑删除",name="isDelete")
    private Boolean isDelete;

}

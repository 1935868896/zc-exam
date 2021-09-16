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
 * 试卷表 t_exam_paper
 *
 * @author zhangc
 * @date 2021-09-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="试卷",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TExamPaper extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="试卷名称",name="name")
    private String name;

    @ApiModelProperty(value="学科",name="subjectId")
    private Integer subjectId;

    @ApiModelProperty(value="试卷类型( 1.固定试卷 4.时段试卷 6.任务试卷 )",name="paperType")
    private Integer paperType;

    @ApiModelProperty(value="年级",name="gradeLevel")
    private Integer gradeLevel;

    @ApiModelProperty(value="分数",name="score")
    private Integer score;

    @ApiModelProperty(value="题目数量",name="questionCount")
    private Integer questionCount;

    @ApiModelProperty(value="建议时长",name="suggestTime")
    private Integer suggestTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="限制开始时间",name="limitStartTime")
    private Date limitStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="限制结束时间",name="limitEndTime")
    private Date limitEndTime;

    @ApiModelProperty(value="试卷框架 内容为JSON",name="frameTextContentId")
    private Integer frameTextContentId;

    @ApiModelProperty(value="创建人",name="createUser")
    private Integer createUser;
    @TableLogic
    @ApiModelProperty(value="逻辑删除",name="isDelete")
    private Boolean isDelete;

    @ApiModelProperty(value="任务试卷id",name="taskExamId")
    private Integer taskExamId;

}

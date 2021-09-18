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
 * 答案表 t_exam_paper_answer
 *
 * @author zhangc
 * @date 2021-09-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="答案",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TExamPaperAnswer extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="试卷id",name="examPaperId")
    private Integer examPaperId;

    @ApiModelProperty(value="试卷名称",name="paperName")
    private String paperName;

    @ApiModelProperty(value="试卷类型",name="paperType")
    private Integer paperType;

    @ApiModelProperty(value="学科id",name="subjectId")
    private Integer subjectId;

    @ApiModelProperty(value="系统判定得分",name="systemScore")
    private Integer systemScore;

    @ApiModelProperty(value="最终得分",name="userScore")
    private Integer userScore;

    @ApiModelProperty(value="试卷总分",name="paperScore")
    private Integer paperScore;

    @ApiModelProperty(value="做对题目数量",name="questionCorrect")
    private Integer questionCorrect;

    @ApiModelProperty(value="题目数量",name="questionCount")
    private Integer questionCount;

    @ApiModelProperty(value="做题时间(秒)",name="doTime")
    private Integer doTime;

    @ApiModelProperty(value="状态(1待判断,2完成)",name="status")
    private Integer status;

    @ApiModelProperty(value="创建人",name="createUser")
    private Integer createUser;

    @ApiModelProperty(value="任务试卷id",name="taskExamId")
    private Integer taskExamId;

}

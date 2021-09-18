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
 * 答案问题表 t_exam_paper_question_customer_answer
 *
 * @author zhangc
 * @date 2021-09-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="答案问题",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TExamPaperQuestionCustomerAnswer extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="id")
    private Integer id;

    @ApiModelProperty(value="问题id",name="questionId")
    private Integer questionId;

    @ApiModelProperty(value="试卷id",name="examPaperId")
    private Integer examPaperId;

    @ApiModelProperty(value="试卷答案id",name="examPaperAnswerId")
    private Integer examPaperAnswerId;

    @ApiModelProperty(value="问题类型",name="questionType")
    private Integer questionType;

    @ApiModelProperty(value="科目id",name="subjectId")
    private Integer subjectId;

    @ApiModelProperty(value="得分",name="customerScore")
    private Integer customerScore;

    @ApiModelProperty(value="题目原始分数",name="questionScore")
    private Integer questionScore;

    @ApiModelProperty(value="问题内容",name="questionTextContentId")
    private Integer questionTextContentId;

    @ApiModelProperty(value="答案",name="answer")
    private String answer;

    @ApiModelProperty(value="做题内容",name="textContentId")
    private Integer textContentId;

    @ApiModelProperty(value="是否正确",name="doRight")
    private Boolean doRight;

    @ApiModelProperty(value="创建人",name="createUser")
    private Integer createUser;

    @ApiModelProperty(value="题目排序",name="itemOrder")
    private Integer itemOrder;

}

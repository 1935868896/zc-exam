package com.zc.modules.project.vo;

import com.zc.modules.project.entity.TExamPaperAnswer;
import lombok.*;

/**
 * @author ZhangC
 * @create 2021-09-18-14:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperAnswerVO extends TExamPaperAnswer {
    private String subjectName;

}

package com.zc.modules.project.dto;

import com.zc.modules.project.entity.TExamPaperQuestionCustomerAnswer;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author ZhangC
 * @create 2021-09-18-15:17
 */
@Data
@RequiredArgsConstructor
public class QuestionErrorDTO extends TExamPaperQuestionCustomerAnswer {
    String questionContent;
    String subjectName;

}

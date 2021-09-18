package com.zc.modules.project.vo;

import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.entity.answer.AnswerObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ZhangC
 * @create 2021-09-18-17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionErrorDetailVO {
    private QuestionDTO question;
    private AnswerObject answer;
}

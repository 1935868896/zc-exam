package com.zc.modules.project.dto;

import com.zc.entity.BaseEntity;
import com.zc.modules.project.entity.TQuestion;
import com.zc.modules.project.entity.question.QuestionObject;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-15-11:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO extends TQuestion {
    private QuestionObject questionObject;
    private String content;
    private List<String> correctArray;
}

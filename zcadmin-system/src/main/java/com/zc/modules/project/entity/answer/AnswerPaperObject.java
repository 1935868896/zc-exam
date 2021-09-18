package com.zc.modules.project.entity.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-18-17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPaperObject {
    private Integer score;
    private Integer doTime;
    private Integer id;
    private List<AnswerObject> answerItems;
}

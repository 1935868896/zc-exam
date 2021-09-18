package com.zc.modules.project.entity.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-18-17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerObject {
    private String content;
    private List<String> contentArray;
    private Boolean doRight;
    private Integer itemOrder;
}

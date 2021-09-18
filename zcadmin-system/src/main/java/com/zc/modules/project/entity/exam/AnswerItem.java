package com.zc.modules.project.entity.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-18-9:29
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerItem {
    private Boolean completed;
    private String content;
    private List<String> contentArray;
    private Integer itemOrder;
    private Integer questionId;
}

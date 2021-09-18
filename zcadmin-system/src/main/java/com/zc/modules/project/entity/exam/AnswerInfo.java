package com.zc.modules.project.entity.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-18-9:28
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerInfo {

    private List<AnswerItem> answerItems;
    private Integer doTime;
    private Integer id;

}

package com.zc.modules.project.entity.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-16-14:09
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TitleItem {
    private List<QuestionItem> questionItems;
    private String name;

}

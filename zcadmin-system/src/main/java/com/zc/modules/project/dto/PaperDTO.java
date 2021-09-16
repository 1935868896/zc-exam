package com.zc.modules.project.dto;

import com.zc.modules.project.entity.TExamPaper;
import com.zc.modules.project.entity.paper.QuestionItem;
import com.zc.modules.project.entity.paper.TitleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZhangC
 * @create 2021-09-16-14:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDTO extends TExamPaper {
    private List<TitleItem> titleItems;
}

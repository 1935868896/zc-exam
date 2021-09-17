package com.zc.modules.project.entity.paper;

import com.alibaba.fastjson.annotation.JSONField;
import com.zc.modules.project.entity.question.QuestionObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZhangC
 * @create 2021-09-16-14:09
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionItem {
    private Integer Id;
    private Integer itemOrder;

    @JSONField(serialize = false)
    private Long score;

    @JSONField(serialize = false)
    private Integer questionType;

    @JSONField(serialize = false)
    private QuestionObject questionObject;

}

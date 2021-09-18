package com.zc.modules.project.vo;

import com.zc.modules.project.dto.PaperDTO;
import com.zc.modules.project.entity.answer.AnswerPaperObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhangC
 * @create 2021-09-18-17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperAnswerReadVO {
    private PaperDTO paper;
    private AnswerPaperObject answer;
}

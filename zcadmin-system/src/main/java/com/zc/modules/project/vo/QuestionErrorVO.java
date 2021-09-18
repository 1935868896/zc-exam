package com.zc.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author ZhangC
 * @create 2021-09-18-15:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionErrorVO {

    private Integer id;
    private String shortTitle;
    private Integer questionType;
    private String subjectName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}

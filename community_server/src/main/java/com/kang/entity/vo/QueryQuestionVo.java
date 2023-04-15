package com.kang.entity.vo;

import lombok.Data;


@Data
public class QueryQuestionVo {

    private Long authorId;
    private Integer qaState;
    private Integer categoryCode;
    private String order;
    private String searchContent;

    /**
     * 0:未解决  1:已解决
     */
    private Integer selectState;
}

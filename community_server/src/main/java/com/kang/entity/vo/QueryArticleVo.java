package com.kang.entity.vo;

import lombok.Data;


@Data
public class QueryArticleVo {

    private Long authorId;
    private Integer articleState;
    private Integer categoryCode;
    private String order;
    private String albumId;
}

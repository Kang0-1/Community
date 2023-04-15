package com.kang.entity.vo;

import lombok.Data;

@Data
public class QueryResourceVo {

    private Long authorId;
    private Integer resourceState;
    private Integer categoryCode;
    private String order;
    private String searchContent;
}

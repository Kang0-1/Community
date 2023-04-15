package com.kang.entity.vo;

import lombok.Data;

@Data
public class QueryVideoVo {

    private Long authorId;

    private Integer videoState;

    private Integer categoryCode;

    private String order;

    private String albumId;

}

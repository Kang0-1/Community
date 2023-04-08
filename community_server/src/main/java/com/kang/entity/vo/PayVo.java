package com.kang.entity.vo;

import lombok.Data;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/30 19:58
 */
@Data
public class PayVo {

    private Long payUid;

    private Long authorId;

    private String payPassword;

    private Double goodsCoin;

    private String goodsId;

    private Integer goodsType;

    private String goodsName;

}

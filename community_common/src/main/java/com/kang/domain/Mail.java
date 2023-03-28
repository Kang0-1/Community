package com.kang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kang
 * @description 邮件实体
 * @date 2023/3/23 15:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {

    /**
     * 邮件接收方，可多人
     */
    private String recipient;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}

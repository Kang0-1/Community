package com.kang.service.impl;

import com.kang.entity.Message;
import com.kang.mapper.MessageMapper;
import com.kang.service.MessageService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:02:58
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
        @Resource
        private MessageMapper messageMapper;

}


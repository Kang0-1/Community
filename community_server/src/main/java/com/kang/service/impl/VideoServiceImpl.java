package com.kang.service.impl;

import com.kang.entity.Video;
import com.kang.mapper.VideoMapper;
import com.kang.service.VideoService;
import com.kang.domain.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Video)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 15:03:00
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {
        @Resource
        private VideoMapper videoMapper;

}


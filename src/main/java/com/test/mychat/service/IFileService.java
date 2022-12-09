package com.test.mychat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.mychat.common.vo.WrappedResult;
import com.test.mychat.pojo.LocalFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IFileService extends IService<LocalFile> {

    WrappedResult uploadFile(MultipartFile file, HttpServletRequest request,String userId,String diaryId);
}

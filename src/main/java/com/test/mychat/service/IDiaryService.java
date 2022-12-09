package com.test.mychat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.mychat.common.util.ParameterCondition;
import com.test.mychat.common.vo.WrappedResult;
import com.test.mychat.pojo.Diary;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IDiaryService extends IService<Diary> {
    String addDiary(Map<String,Object> map) throws Exception;

    WrappedResult queryDiaryByCondition(ParameterCondition<Diary> param) throws Exception;

    void deleteDiaryByids(List<String> ids) throws Exception;

    void updateByMap(Map<String, Object> map)throws Exception;

    void downLoadDiary(String diaryId)throws Exception;
}

package com.test.mychat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mychat.pojo.Diary;

import java.util.List;
import java.util.Map;

public interface DiaryMapper extends BaseMapper<Diary> {

    List<Map<String, Object>> queryDiaryListByCondition(Diary diary);
}

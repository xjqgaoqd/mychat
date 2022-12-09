package com.test.mychat.common.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParameterCondition<T> implements Serializable {
    Object filter;

    Integer pageIndex = 0;

    Integer pageSize = 10;

    public T getParameterPO(){
        return (T) getFilter();
    }
}

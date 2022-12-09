package com.test.mychat.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Page {
    private Long pageIndex;
    private Long pageSize;
}
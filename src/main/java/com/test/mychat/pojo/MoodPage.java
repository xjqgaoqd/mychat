package com.test.mychat.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MoodPage extends Page {
    private String key;
    private String value;
}


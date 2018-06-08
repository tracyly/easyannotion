package com.ymsun.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C) 2018 IFLYTEK Inc., All Rights Reserved.
 *
 * @author: sunyuanming
 * @date: 2018/6/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnClick {
    int [] value();
}

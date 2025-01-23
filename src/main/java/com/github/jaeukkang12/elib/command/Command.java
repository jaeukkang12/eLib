package com.github.jaeukkang12.elib.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String parent(); // 상위 명령어
    String sub(); // 하위 명령어
    String permission() default ""; // 권한
}

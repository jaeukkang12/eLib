package com.github.jaeukkang12.elib.messages;

import com.github.jaeukkang12.elib.utils.StringUtil;

public final class Message {

    private Message() {}

    public static final String ERROR = StringUtil.color("&c명령어를 실행하던 중 오류가 발생했습니다.");
    public static final String WRONG_COMMAND = StringUtil.color("&c알 수 없는 명령어입니다!");
    public static final String NO_PERMISSION = StringUtil.color("&c명령어를 사용할 수 없습니다!");
}

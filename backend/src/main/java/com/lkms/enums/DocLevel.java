package com.lkms.enums;

public enum DocLevel implements BaseEnum{
    ARTICLE(0),
    PARAGRAPH(1),
    SUBPARAGRAPH(2),
    ITEM(3)
    ;

    private final Integer code;
    DocLevel(int code) {
        this.code=code;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}

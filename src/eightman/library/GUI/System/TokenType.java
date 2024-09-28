package eightman.library.GUI.System;

import eightman.library.GUI.MODULE.Equipment_Module;

public enum TokenType {
    UNITS("units"),
    FLEET("fleet"),
    TASKFORCE("task_force"),
    SHIP("ship"),
    NAME("name"),
    NAVAL_BASE("naval_base"),
    LOCATION("location"),
    DEFINITION("definition"),
    OWNER("owner"),
    VERSION_NAME("version_name"),
    ASSIGNMENT("="),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    SPACE(" "),
    KAKKO("\""),
    STARTEXP("start_experience_factor"),
    EQUIPMENT("equipment"),
    SHIPHULL("ship_hull"),
    AMOUNT("amount"),
    CMMENTOUT("#"),
    EOF("EOF"),
    ILLEGAL("ILLEGAL"),
    IDENT("IDENT"), // add, foobar, x, y, ...
    ;
    private final String str;

    TokenType(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }
}
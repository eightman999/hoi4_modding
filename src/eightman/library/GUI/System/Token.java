package eightman.library.GUI.System;

public class Token {
    public enum TokenType {
        UNITS,
        FLEET,
        TASKFORCE,
        SHIP,
        NAME,
        NAVAL_BASE,
        LOCATION,
        DEFINITION,
        OWNER,
        VERSION_NAME,
        STARTEXP,
        EQUIPMENT,
        AMOUNT,
        ASSIGNMENT,
        OPEN_BRACE,
        CLOSE_BRACE,
        SPACE,
        KAKKO,
        CMMENTOUT,
        NUMBER,
        EOF,
        ILLEGAL,
        IDENT,
        STRING,
        SHIP_HULL,
        TYPE,
        NAME_GROUP,
        UPGRADES,
        N_MODULES,
        CREATE_EQUIPMENT_VARIANT,
        SPRITE_TYPE,
        SPRITE_TYPES,
        TEXTURE_FILE,
    }

    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
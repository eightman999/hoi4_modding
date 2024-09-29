package eightman.library.GUI.System;

public class Token {
    public enum TokenType {
        UNITS, FLEET, TASKFORCE, SHIP, NAME, NAVAL_BASE, LOCATION, DEFINITION, OWNER, VERSION_NAME, STARTEXP, EQUIPMENT, SHIPHULL, AMOUNT, ASSIGNMENT, OPEN_BRACE, CLOSE_BRACE, SPACE, KAKKO, CMMENTOUT, NUMBER, EOF, ILLEGAL, IDENT, STRING,SHIP_HULL
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
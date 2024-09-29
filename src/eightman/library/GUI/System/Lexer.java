package eightman.library.GUI.System;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\bnaval_base\\b|\\btask_force\\b|\\bunits\\b|\\bfleet\\b|\\bship\\b|\\bname\\b|\\bdefinition\\b|\\bowner\\b|\\bversion_name\\b|\\bstart_experience_factor\\b|\\bship_hull_[a-z]+_\\d+\\b|=|\\{|\\}|\\d+\\.\\d+|\\d+|\"[^\"]*\"|\\w+|#.*"
    );

    private Matcher matcher;
    private String input;

    public Lexer(String input) {
        this.input = input;
        this.matcher = TOKEN_PATTERN.matcher(input);
    }

    public Token nextToken() {
        if (!matcher.find()) {
            return new Token(Token.TokenType.EOF, "");
        }

        String tokenValue = matcher.group();
        Token.TokenType type;

        switch (tokenValue) {
            case "naval_base":
                type = Token.TokenType.NAVAL_BASE;
                break;
            case "task_force":
                type = Token.TokenType.TASKFORCE;
                break;
            case "units":
                type = Token.TokenType.UNITS;
                break;
            case "fleet":
                type = Token.TokenType.FLEET;
                break;
            case "ship":
                type = Token.TokenType.SHIP;
                break;
            case "name":
                type = Token.TokenType.NAME;
                break;
            case "definition":
                type = Token.TokenType.DEFINITION;
                break;
            case "owner":
                type = Token.TokenType.OWNER;
                break;
            case "version_name":
                type = Token.TokenType.VERSION_NAME;
                break;
            case "start_experience_factor":
                type = Token.TokenType.STARTEXP;
                break;
            case "=":
                type = Token.TokenType.ASSIGNMENT;
                break;
            case "{":
                type = Token.TokenType.OPEN_BRACE;
                break;
            case "}":
                type = Token.TokenType.CLOSE_BRACE;
                break;
            case "location":
                type = Token.TokenType.LOCATION;
                break;
            case "equipment":
                type = Token.TokenType.EQUIPMENT;
                break;
            case "amount":
                type = Token.TokenType.AMOUNT;
                break;
            default:
                if (tokenValue.matches("#.*")) {
                    type = Token.TokenType.CMMENTOUT;
                } else if (tokenValue.matches("\\d+\\.\\d+")) {
                    type = Token.TokenType.NUMBER;
                } else if (tokenValue.matches("\\d+")) {
                    type = Token.TokenType.NUMBER;
                } else if (tokenValue.matches("\"[^\"]*\"")) {
                    type = Token.TokenType.STRING;
                } else if (tokenValue.matches("ship_hull_[a-z]+_\\d+")) {
                    type = Token.TokenType.SHIP_HULL;
                } else {
                    type = Token.TokenType.IDENT;
                }
                break;
        }

        return new Token(type, tokenValue);
    }
}
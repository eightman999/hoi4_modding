package eightman.library.GUI.System;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\bnaval_base\\b|\\btask_force\\b|\\bunits\\b|\\bfleet\\b|\\bship\\b|\\bname\\b|\\bdefinition\\b|\\bowner\\b|\\bversion_name\\b|\\bstart_experience_factor\\b|\\bship_hull_[a-z]+_\\d+\\b|=|\\{|\\}|\\d+\\.\\d+|\\d+|\"[^\"]*\"|\\w+|#.*"
    );

    private final Matcher matcher;
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
            case "name_group":
                type = Token.TokenType.NAME_GROUP;
                break;
            case "upgrades":
                type = Token.TokenType.UPGRADES;
                break;
            case "modules":
                type = Token.TokenType.N_MODULES;
                break;
            case "create_equipment_variant":
                type = Token.TokenType.CREATE_EQUIPMENT_VARIANT;
                break;
            case "SpriteType":
                type = Token.TokenType.SPRITE_TYPE;
                break;
            case "SpriteTypes":
                type = Token.TokenType.SPRITE_TYPES;
                break;
            case "texturefile":
                type = Token.TokenType.TEXTURE_FILE;
                break;
            case "instant_effect":
                type = Token.TokenType.INSTANT_EFFECT;
                break;
            case "add_equipment_production":
                type = Token.TokenType.ADD_EQUIPMENT_PRODUCTION;
                break;
            case "creator":
                type = Token.TokenType.CREATOR;
                break;
            case "requested_factories":
                type = Token.TokenType.REQUESTED_FACTORIES;
                break;
            case "progress":
                type = Token.TokenType.PROGRESS;
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
                } else if (tokenValue.matches("ship_hull_[A-Za-z_0-9]+")) {
                    type = Token.TokenType.SHIP_HULL;
                } else {
                    type = Token.TokenType.IDENT;
                }
                break;
        }

        return new Token(type, tokenValue);
    }
}
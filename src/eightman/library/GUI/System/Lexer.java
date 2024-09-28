package eightman.library.GUI.System;

public class Lexer {

    /** input string. */
    private String input;



    /** current position in input (points to current char). */
    private int position;

    /** current reading position in input (after current char). */
    private int readPosition;

    /** current char under examination. */
    private char ch;

    public Lexer(String input) {
        this.input = input;
        this.readPosition = 0;
        readChar();
    }

    public Token nextToken() {
        skipWhitespace();
        Token token;

        switch (ch) {
            case '=':
                token = new Token(TokenType.ASSIGNMENT, "=");
                break;
            case '{':
                token = new Token(TokenType.OPEN_BRACE, "{");
                break;
            case '}':
                token = new Token(TokenType.CLOSE_BRACE, "}");
                break;
            case ' ':
                token = new Token(TokenType.SPACE, " ");
                break;
            case '"':
                token = new Token(TokenType.KAKKO, "\"");
                break;
            case '#':
                token = new Token(TokenType.CMMENTOUT, "#");
                break;
            case '\0':
                token = new Token(TokenType.EOF, "");
                break;
            default:
                if (Character.isLetter(ch)) {
                    String literal = readIdentifier();
                    TokenType type = lookupTokenType(literal);
                    return new Token(type, literal);
                } else {
                    token = new Token(TokenType.ILLEGAL, String.valueOf(ch));
                }
                break;
        }
        readChar();
        return token;
    }

    private void readChar() {
        if (readPosition >= input.length()) {
            ch = '\0';
        } else {
            ch = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition += 1;
    }

    private String readIdentifier() {
        int startPosition = position;
        while (Character.isLetter(ch) || ch == '_') {
            readChar();
        }
        return input.substring(startPosition, position);
    }

    private void skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            readChar();
        }
    }

    private TokenType lookupTokenType(String literal) {
        switch (literal) {
            case "units":
                return TokenType.UNITS;
            case "fleet":
                return TokenType.FLEET;
            case "task_force":
                return TokenType.TASKFORCE;
            case "ship":
                return TokenType.SHIP;
            case "name":
                return TokenType.NAME;
            case "naval_base":
                return TokenType.NAVAL_BASE;
            case "location":
                return TokenType.LOCATION;
            case "definition":
                return TokenType.DEFINITION;
            case "owner":
                return TokenType.OWNER;
            case "version_name":
                return TokenType.VERSION_NAME;
            case "start_experience_factor":
                return TokenType.STARTEXP;
            case "equipment":
                return TokenType.EQUIPMENT;
            case "ship_hull":
                return TokenType.SHIPHULL;
            case "amount":
                return TokenType.AMOUNT;
            default:
                return TokenType.IDENT;
        }
    }
}
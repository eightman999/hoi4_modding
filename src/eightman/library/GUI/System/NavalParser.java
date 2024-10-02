// NavalParser.java
package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class NavalParser {

    private List<Token> tokens;
    private int currentTokenIndex = 0;

    public NavalParser(Lexer lexer) {
        this.tokens = new ArrayList<>();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.TokenType.EOF) {
            tokens.add(token);
        }
    }

    public List<Fleet> parse() {
        List<Fleet> fleets = new ArrayList<>();
//        while (!isEOF()) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.UNITS)) {
                parseUnits(fleets);
            } else if (check(Token.TokenType.CLOSE_BRACE)) {
                // Handle unexpected close brace
                advance();
            }else if (check(Token.TokenType.INSTANT_EFFECT)) {

            }else {
                throw new RuntimeException("Unexpected token: " + currentToken().getValue());
            }
//        }
        return fleets;
    }

    private void parseUnits(List<Fleet> fleets) {
        consume(Token.TokenType.UNITS);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);
        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.FLEET)) {
                fleets.add(parseFleet());
            } else {
                throw new RuntimeException("Expected fleet but found: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
    }

    private Fleet parseFleet() {
        Fleet fleet = new Fleet();
        consume(Token.TokenType.FLEET);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);

        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.NAME)) {
                consume(Token.TokenType.NAME);
                consume(Token.TokenType.ASSIGNMENT);
                String temp_f_name = consume(Token.TokenType.STRING).getValue();
//                System.out.println("Fleet name: " + temp_f_name);
                fleet.setName(temp_f_name);
            } else if (check(Token.TokenType.NAVAL_BASE)) {
                consume(Token.TokenType.NAVAL_BASE);
                consume(Token.TokenType.ASSIGNMENT);
                int temp_f_naval_base = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
//                System.out.println("Naval base: " + temp_f_naval_base);
                fleet.setNavalBase(temp_f_naval_base);
            } else if (check(Token.TokenType.TASKFORCE)) {
                System.out.println("Task force added");
                fleet.addTaskForce(parseTaskForce());
            } else if (check(Token.TokenType.SHIP)) {
                System.out.println("Ship added");
                fleet.addShip(parseShip());
            } else {
                throw new RuntimeException("Expected task_force or ship but found: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return fleet;
    }

    private TaskForce parseTaskForce() {
        TaskForce taskForce = new TaskForce();
        consume(Token.TokenType.TASKFORCE);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);
        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.NAME)) {
                consume(Token.TokenType.NAME);
                consume(Token.TokenType.ASSIGNMENT);
                String temp_tf_name = consume(Token.TokenType.STRING).getValue();
//                System.out.println("Task force name: " + temp_tf_name);
                taskForce.setName(temp_tf_name);
            } else if (check(Token.TokenType.LOCATION)) {
                consume(Token.TokenType.LOCATION);
                consume(Token.TokenType.ASSIGNMENT);
                int temp_tf_location = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
//                System.out.println("Location: " + temp_tf_location);
                taskForce.setLocation(temp_tf_location);
            } else if (check(Token.TokenType.SHIP)) {
                System.out.println("Ship added");
                taskForce.addShip(parseShip());
            } else {
                throw new RuntimeException("Expected ship but found: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return taskForce;
    }

    private Ship parseShip() {
        Ship ship = new Ship();
        consume(Token.TokenType.SHIP);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);
        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.NAME)) {
                consume(Token.TokenType.NAME);
                consume(Token.TokenType.ASSIGNMENT);
                String temp_s_name = consume(Token.TokenType.STRING).getValue();
//                System.out.println("Ship name: " + temp_s_name);
                ship.setName(temp_s_name);
            } else if (check(Token.TokenType.DEFINITION)) {
                consume(Token.TokenType.DEFINITION);
                consume(Token.TokenType.ASSIGNMENT);
                String temp_s_definition = consume(Token.TokenType.IDENT).getValue();
//                System.out.println("Ship definition: " + temp_s_definition);
                ship.setDefinition(temp_s_definition);
            } else if (check(Token.TokenType.STARTEXP)) {
                consume(Token.TokenType.STARTEXP);
                consume(Token.TokenType.ASSIGNMENT);
                Double temp_s_startexp = Double.valueOf(consume(Token.TokenType.NUMBER).getValue());
//                System.out.println("EXP: " + temp_s_startexp);
                ship.setStartExperienceFactor(temp_s_startexp);
            } else if (check(Token.TokenType.EQUIPMENT)) {
                consume(Token.TokenType.EQUIPMENT);
                consume(Token.TokenType.ASSIGNMENT);
                consume(Token.TokenType.OPEN_BRACE);
                while (!check(Token.TokenType.CLOSE_BRACE)) {
                    if (check(Token.TokenType.CMMENTOUT)) {
                        skipComment();
                    } else if (check(Token.TokenType.SHIP_HULL)) {
                        String temp_equip = consume(Token.TokenType.SHIP_HULL).getValue();
                        consume(Token.TokenType.ASSIGNMENT);
                        consume(Token.TokenType.OPEN_BRACE);
//                        System.out.println("Equipment: " + temp_equip);
                        ship.addEquipment(temp_equip);
                    } else if (check(Token.TokenType.AMOUNT)) {
                        consume(Token.TokenType.AMOUNT);
                        consume(Token.TokenType.ASSIGNMENT);
                        int amount = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
//                        System.out.println("Amount: " + amount);
                        ship.setAmount(amount);
                    } else if (check(Token.TokenType.OWNER)) {
                        consume(Token.TokenType.OWNER);
                        consume(Token.TokenType.ASSIGNMENT);
                        String owner = consume(Token.TokenType.IDENT).getValue();
//                        System.out.println("Owner: " + owner);
                        ship.setOwner(owner);
                    } else if (check(Token.TokenType.VERSION_NAME)) {
                        consume(Token.TokenType.VERSION_NAME);
                        consume(Token.TokenType.ASSIGNMENT);
                        String versionName = consume(Token.TokenType.STRING).getValue();
//                        System.out.println("Version name: " + versionName);
                        ship.setVersionName(versionName);
                    } else {
                        throw new RuntimeException("Unexpected token in equipment: " + currentToken().getValue());
                    }
                }
                consume(Token.TokenType.CLOSE_BRACE);
                consume(Token.TokenType.CLOSE_BRACE);
            } else {
                throw new RuntimeException("Unexpected token in ship: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return ship;
    }

    private void parseInstanceEffect(List<production_ships> production_ships) {
        consume(Token.TokenType.INSTANT_EFFECT);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);
        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.ADD_EQUIPMENT_PRODUCTION)) {
                production_ships.add(parseProductionShips());
            } else {
                throw new RuntimeException("Unexpected token in instant effect: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
    }

    private

    private void skipComment() {
        while (!isEOF() && currentToken().getType() != Token.TokenType.CMMENTOUT) {
            advance();
        }
        // Skip the comment token itself
        if (!isEOF()) {
            advance();
        }
    }

    private Token consume(Token.TokenType expectedType) {
        Token token = currentToken();
        if (token.getType() != expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but found " + token.getType());
        }
        advance();
        return token;
    }

    private boolean check(Token.TokenType type) {
        return currentToken().getType() == type;
    }

    private Token currentToken() {
        return tokens.get(currentTokenIndex);
    }

    private void advance() {
        if (currentTokenIndex < tokens.size() - 1) {
            currentTokenIndex++;
        }
    }

    private boolean isEOF() {
        return currentTokenIndex >= tokens.size() || currentToken().getType() == Token.TokenType.EOF;
    }
}
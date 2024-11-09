package eightman.library.GUI.System;

import eightman.library.Core;

import java.util.ArrayList;
import java.util.List;

public class NavalParser {

    private final List<Token> tokens;
    private int currentTokenIndex = 0;

    public NavalParser(Lexer lexer) {
        this.tokens = new ArrayList<>();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.TokenType.EOF) {
            tokens.add(token);
        }
    }

    public Units parse() {
        Core.out.println("Parsing started");
        List<Fleet> fleets = new ArrayList<>();
        try {
            while (!isEOF()) {
                if (check(Token.TokenType.CMMENTOUT)) {
                    skipComment();
                } else if (check(Token.TokenType.UNITS)) {
                    parseUnits(fleets);
                } else if (check(Token.TokenType.CLOSE_BRACE)) {
                    advance();
                } else if (check(Token.TokenType.INSTANT_EFFECT)) {
                    advance();
                } else {
                    throw new RuntimeException("Unexpected token: " + currentToken().getValue());
                }
            }
        } catch (Exception e) {
            Core.ERROR();
            e.printStackTrace();
        }
        Core.out.println("Parsing completed");
        return new Units(fleets);
    }

    private void parseUnits(List<Fleet> fleets) {
        Core.out.println("Parsing units");
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
        Core.out.println("Parsing fleet");
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
                fleet.setName(temp_f_name);
                Core.out.println("Parsed fleet name: " + temp_f_name);
            } else if (check(Token.TokenType.NAVAL_BASE)) {
                consume(Token.TokenType.NAVAL_BASE);
                consume(Token.TokenType.ASSIGNMENT);
                int temp_f_naval_base = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
                fleet.setNavalBase(temp_f_naval_base);
                Core.out.println("Parsed fleet naval base: " + temp_f_naval_base);
            } else if (check(Token.TokenType.TASKFORCE)) {
                fleet.addTaskForce(parseTaskForce());
            } else {
                throw new RuntimeException("Expected task_force but found: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return fleet;
    }

    private TaskForce parseTaskForce() {
        Core.out.println("Parsing task force");
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
                taskForce.setName(temp_tf_name);
                Core.out.println("Parsed task force name: " + temp_tf_name);
            } else if (check(Token.TokenType.LOCATION)) {
                consume(Token.TokenType.LOCATION);
                consume(Token.TokenType.ASSIGNMENT);
                int temp_tf_location = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
                taskForce.setLocation(temp_tf_location);
                Core.out.println("Parsed task force location: " + temp_tf_location);
            } else if (check(Token.TokenType.SHIP)) {
                taskForce.addShip(parseShip());
            } else {
                throw new RuntimeException("Expected ship but found: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return taskForce;
    }

    private Ship parseShip() {
        Core.out.println("Parsing ship");
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
                ship.setName(temp_s_name);
                Core.out.println("Parsed ship name: " + temp_s_name);
            } else if (check(Token.TokenType.DEFINITION)) {
                consume(Token.TokenType.DEFINITION);
                consume(Token.TokenType.ASSIGNMENT);
                String temp_s_definition = consume(Token.TokenType.IDENT).getValue();
                ship.setDefinition(temp_s_definition);
                Core.out.println("Parsed ship definition: " + temp_s_definition);
            } else if (check(Token.TokenType.STARTEXP)) {
                consume(Token.TokenType.STARTEXP);
                consume(Token.TokenType.ASSIGNMENT);
                double temp_s_startexp = Double.parseDouble(consume(Token.TokenType.NUMBER).getValue());
                ship.setStartExperienceFactor(temp_s_startexp);
                Core.out.println("Parsed ship start experience: " + temp_s_startexp);
            } else if (check(Token.TokenType.pride_of_the_fleet)) {
                consume(Token.TokenType.pride_of_the_fleet);
                consume(Token.TokenType.ASSIGNMENT);
                String prideValue = consume(Token.TokenType.STRING).getValue();
                if ("yes".equals(prideValue)) {
                    ship.setPrideOfTheFleet(true);
                }
                Core.out.println("Parsed ship pride of the fleet: " + prideValue);
            } else if (check(Token.TokenType.EQUIPMENT)) {
                ship.addEquipment(parseEquipment());
            } else {
                throw new RuntimeException("Unexpected token in ship: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return ship;
    }

    private Equipment parseEquipment() {
        Core.out.println("Parsing equipment");
        Equipment equipment = new Equipment();
        consume(Token.TokenType.EQUIPMENT);
        consume(Token.TokenType.ASSIGNMENT);
        consume(Token.TokenType.OPEN_BRACE);
        while (!check(Token.TokenType.CLOSE_BRACE)) {
            if (check(Token.TokenType.CMMENTOUT)) {
                skipComment();
            } else if (check(Token.TokenType.SHIP_HULL)) {
                String shipHull = consume(Token.TokenType.SHIP_HULL).getValue();
                consume(Token.TokenType.ASSIGNMENT);
                consume(Token.TokenType.OPEN_BRACE);
                while (!check(Token.TokenType.CLOSE_BRACE)) {
                    if (check(Token.TokenType.AMOUNT)) {
                        consume(Token.TokenType.AMOUNT);
                        consume(Token.TokenType.ASSIGNMENT);
                        int amount = Integer.parseInt(consume(Token.TokenType.NUMBER).getValue());
                        equipment.setAmount(amount);
                        Core.out.println("Parsed equipment amount: " + amount);
                    } else if (check(Token.TokenType.OWNER)) {
                        consume(Token.TokenType.OWNER);
                        consume(Token.TokenType.ASSIGNMENT);
                        String owner = consume(Token.TokenType.OWNER_IDENTIFIER).getValue();
                        equipment.setOwner(owner);
                        Core.out.println("Parsed equipment owner: " + owner);
                    } else if (check(Token.TokenType.VERSION_NAME)) {
                        consume(Token.TokenType.VERSION_NAME);
                        consume(Token.TokenType.ASSIGNMENT);
                        String versionName = consume(Token.TokenType.STRING).getValue();
                        equipment.setVersionName(versionName);
                        Core.out.println("Parsed equipment version name: " + versionName);
                    } else {
                        throw new RuntimeException("Unexpected token in equipment: " + currentToken().getValue());
                    }
                }
                consume(Token.TokenType.CLOSE_BRACE);
                equipment.setShipHull(shipHull);
                Core.out.println("Parsed equipment ship hull: " + shipHull);
            } else {
                throw new RuntimeException("Unexpected token in equipment: " + currentToken().getValue());
            }
        }
        consume(Token.TokenType.CLOSE_BRACE);
        return equipment;
    }

    private void skipComment() {
        while (!isEOF() && currentToken().getType() != Token.TokenType.CMMENTOUT) {
            advance();
        }
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
        } else {
            currentTokenIndex = tokens.size(); // Ensure it goes beyond the last index to indicate EOF
        }
    }

    private boolean isEOF() {
        Core.out.println("Checking EOF");
        return currentTokenIndex >= tokens.size() || currentToken().getType() == Token.TokenType.EOF;
    }
}
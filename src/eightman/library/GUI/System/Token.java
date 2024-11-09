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
        INSTANT_EFFECT,
        ADD_EQUIPMENT_PRODUCTION,
        CREATOR,
        REQUESTED_FACTORIES,
        PROGRESS,
        pride_of_the_fleet,
        DIVISION_TEMPLATE,
        REGIMENTS,
        DIVISION,
        DIVISION_NAME_GROUP,
        SUPPORT,
        FORCE_EQUIPMENT_VARIANTS,
        X,
        Y,
        START_EQUIPMENT_FACTOR,
        AIR_WINGS,
        SUB_UNITS,
        SPRITE,
        MAP_ICON_CATEGORY,
        PRIORITY,
        ACTIVE,
        SCREEN_SHIP,
        CAPITAL_SHIP,
        CARRIER,
        SUBMARINE,
        CONVOY,
        NEED,
        NEED_EQUIPMENT_MODELS,
        ANY,
        ALL,
        MAX_ORG,
        SUPPRY_CONSUMPTION,
        CRITICAL_PARTS,
        CRITICAL_PART_DAMAGE_CHANCE_MULT,
        HIT_PROFILE_MULT,
        OWNER_IDENTIFIER

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
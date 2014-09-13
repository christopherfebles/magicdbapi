package com.christopherfebles.magic.dao;

/**
 * Shared constants for DAO Objects.
 * 
 * @author Christopher Febles
 *
 */
public final class MagicDAOConstants {

    public static final String ALL_CARDS_PREFIX = "a.";
    public static final String ALL_CARDS_TABLE = " MagicDB.All_Cards a ";
    public static final String ALL_CARDS_TABLE_NO_ALIAS = " MagicDB.All_Cards ";

    public static final String MY_CARDS_PREFIX = "my.";
    public static final String MY_CARDS_TABLE = " MagicDB.My_Cards my ";
    public static final String MY_CARDS_TABLE_NO_ALIAS = " MagicDB.My_Cards ";

    public static final String CARD_TYPE_PREFIX = "t.";
    public static final String CARD_TYPE_TABLE = " MagicDB.Card_Types t ";
    public static final String CARD_TYPE_TABLE_NO_ALIAS = " MagicDB.Card_Types ";

    public static final String CARD_SELECT_COLUMNS = ALL_CARDS_PREFIX + "multiverse_id, " +
                                                     ALL_CARDS_PREFIX + "name, " + 
                                                     ALL_CARDS_PREFIX + "cost, " + 
                                                     ALL_CARDS_PREFIX + "types, " + 
                                                     ALL_CARDS_PREFIX + "text, " + 
                                                     ALL_CARDS_PREFIX + "power, " + 
                                                     ALL_CARDS_PREFIX + "toughness, " + 
                                                     ALL_CARDS_PREFIX + "expansion, " + 
                                                     ALL_CARDS_PREFIX + "flavor_text, " + 
                                                     ALL_CARDS_PREFIX + "rarity, " + 
                                                     ALL_CARDS_PREFIX + "artist, " + 
                                                     ALL_CARDS_PREFIX + "number, " + 
                                                     ALL_CARDS_PREFIX + "watermark, " + 
                                                     ALL_CARDS_PREFIX + "language ";
    
    public static final String DATABASE_QUERY_LOG_MSG = "Database query: {}";

    /**
     * Private constructor
     */
    private MagicDAOConstants() {
    }
    
}

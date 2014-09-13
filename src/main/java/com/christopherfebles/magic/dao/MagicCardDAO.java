package com.christopherfebles.magic.dao;

import java.util.List;

import com.christopherfebles.magic.model.MagicCard;

/**
 * Data access object for Magic Cards.
 * 
 * Naming conventions: <br> 
 *      add/remove<br>
 *      numberOf<br>
 *      get<br>
 * 
 * @author Christopher Febles
 *
 */
public interface MagicCardDAO {

    /*********** Manage Owned Cards ***********/
    /**
     * Load the total number of cards owned
     * 
     * @return The number of cards owned, or -1 if an error occurred.
     */
    int numberOfCardsOwned();

    /**
     * How many copies of the given card are owned?
     * 
     * @param multiverseId
     *            The card to look up.
     * @return The number of copies owned
     */
    int numberOfOwnedCard( Integer multiverseId );

    /**
     * Load the list of all owned Magic cards from the database
     * 
     * @return The list of populated Magic cards
     */
    List<MagicCard> getOwnedCards();

    /**
     * Is the given card already owned?
     * 
     * @param multiverseId
     *            The card to check
     * @return True if at least one copy of the card is owned, false otherwise
     */
    boolean isCardOwned( Integer multiverseId );

    /**
     * Remove/delete a card from the list of owned cards
     * 
     * @param multiverseId
     *            The ID of the card to delete
     * @return True if successful, false otherwise.
     */
    boolean removeOwnedCardById( Integer multiverseId );

    /**
     * Increment the amount owned of the given Magic card. Insert a new row if necessary.
     * 
     * @param multiverseId
     *            The card owned
     * @return True if successful, false otherwise
     */
    boolean incrementOwnedCard( Integer multiverseId );

    /**
     * Increment the amount owned of the given Magic card. Insert a new row if necessary.
     * 
     * @see #incrementOwnedCard(Integer)
     * 
     * @param multiverseId
     *            The card owned
     * @return True if successful, false otherwise
     */
    boolean addOwnedCard( Integer multiverseId );

    /**
     * Decrement the amount owned of the given Magic card. Delete row if necessary.
     * 
     * @param multiverseId
     *            The card owned
     * @return True if successful, false otherwise
     */
    boolean decrementOwnedCard( Integer multiverseId );

    /**
     * Set the amount owned of the given Magic card to the given value. Insert or delete if necessary.
     * 
     * @param multiverseId
     *            The card owned
     * @param newCardCount
     *            The total number of cards owned
     * @return The number of rows affected by this update
     */
    int updateOwnedCardCount( Integer multiverseId, Integer newCardCount );

    /*********** Manage Cards in Database ***********/

    /**
     * @return The total number of cards in the database
     */
    int numberOfCardsInDatabase();

    /**
     * Save the given card to the database
     * 
     * @param card
     *            A fully populated MagicCard
     * @return True if save successful, false otherwise.
     */
    boolean addCardToDatabase( MagicCard card );

    /**
     * Save the given card to the database
     * 
     * @param card
     *            A fully populated MagicCard
     * @return True if save successful, false otherwise.
     */
    boolean saveCardToDatabase( MagicCard card );

    /**
     * Load a card from the database with the given ID
     * 
     * @param multiverseId
     *            A non-null ID value
     * @return The MagicCard specified by the given ID, or null if none found
     */
    MagicCard getCardFromDatabaseById( Integer multiverseId );

    /**
     * Check if the card with the given ID is stored in the database
     * 
     * @param multiverseId
     *            A non-null ID value
     * @return True if the ID is in the database, false otherwise
     */
    boolean isCardInDatabase( Integer multiverseId );

    /**
     * Delete the given card completely from the database
     * 
     * @param multiverseId
     *            The ID of the card to delete
     * @return True if successful, false otherwise.
     */
    boolean removeCardFromDatabaseById( Integer multiverseId );

    /**
     * Load the highest Multiverse ID stored in the database
     * 
     * @return The current highest id in the database, or -1 if an error occurs.
     */
    int getHighestMultiverseId();

    /**
     * Load all the Multiverse IDs stored in the database
     * 
     * @return The current ids in the database, or null if an error occurs.
     */
    List<Integer> getAllMultiverseIds();

    /**
     * Load the card image specified by the given multiverse id
     * 
     * @param multiverseId
     *            The id of the card image to load
     * @return A byte array of the card image
     */
    byte[] getCardImageById( Integer multiverseId );

}

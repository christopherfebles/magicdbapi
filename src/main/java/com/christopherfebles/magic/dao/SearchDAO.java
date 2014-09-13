package com.christopherfebles.magic.dao;

import java.util.List;

import com.christopherfebles.magic.dao.parameter.SearchParameter;
import com.christopherfebles.magic.model.MagicCard;

/**
 * Database search interface.
 * 
 * @see SearchParameter
 * @author Christopher Febles
 *
 */
public interface SearchDAO {

    /**
     * Load the given page from the database with the given search parameters
     * 
     * @param pageNumber
     *            The number of page to load
     * @param searchParams
     *            The search parameters to use for this search
     * @return A list of results, from zero to {@link #numberOfResultsPerPage()} in length
     */
    List<MagicCard> getPageWithSearchParameters( int pageNumber, List<SearchParameter> searchParams );

    /**
     * Load all results from the database with the given search parameters
     * 
     * @param searchParams
     *            The search parameters to use for this search
     * @return A list of all search results
     */
    List<MagicCard> getAllWithSearchParameters( List<SearchParameter> searchParams );

    /**
     * Load a MagicCard from the database with the given search parameters
     * 
     * @param pointer
     *            The position of the MagicCard in the list of total results to load
     * @param searchParams
     *            The search parameters to use for this search
     * @return A single MagicCard
     */
    MagicCard getNextWithSearchParameters( int pointer, List<SearchParameter> searchParams );

    /**
     * Load the total number of cards that will result from a query with the given model
     * 
     * @param searchParams
     *            The search parameters to use for a future search
     * @return The number of rows that will result from a search with the given model
     */
    int numberOfResultsWithSearchParameters( List<SearchParameter> searchParams );

    /**
     * @return The default number of results per paged search
     */
    int numberOfResultsPerPage();

    /**
     * Shared method for loading cards from the database. If pageNumber is negative, load all results.
     * 
     * @param pageNumber
     *            The page to load, or a negative number to indicate load all.
     * @param pageSize
     *            The size of the page in number of MagicCard
     * @param searchParams
     *            The parameters for this search
     * @return A list of all the cards loaded
     */
    List<MagicCard> getPageWithSearchParametersAndPageSize( int pageNumber, int pageSize, List<SearchParameter> searchParams );

    /** Group searches by Name **/

    /**
     * Load the given page from the database, grouped by Name, with the given search parameters
     * 
     * @param pageNumber
     *            The number of page to load
     * @param searchParams
     *            The search parameters to use for this search
     * @return A list of results, from zero to {@link #numberOfResultsPerPage()} in length
     */
    List<MagicCard> getPageByNameWithSearchParameters( int pageNumber, List<SearchParameter> searchParams );

    /**
     * Load all results from the database, grouped by Name, with the given search parameters
     * 
     * @param searchParams
     *            The search parameters to use for this search
     * @return A list of all search results
     */
    List<MagicCard> getAllByNameWithSearchParameters( List<SearchParameter> searchParams );

    /**
     * Load a MagicCard from the database, grouped by Name, with the given search parameters
     * 
     * @param pointer
     *            The position of the MagicCard in the list of total results to load
     * @param searchParams
     *            The search parameters to use for this search
     * @return A single MagicCard
     */
    MagicCard getNextByNameWithSearchParameters( int pointer, List<SearchParameter> searchParams );

    /**
     * Load MagicCards from the database, grouped by Name
     * 
     * @param pageNumber
     *            The page to load, or a negative number to indicate load all.
     * @param pageSize
     *            The size of the page (MagicCard names)
     * @param searchParams
     *            The parameters for this search
     * @return A list of all the cards loaded
     */
    List<MagicCard> getPageByNameWithSearchParametersAndPageSize( int pageNumber, int pageSize, List<SearchParameter> searchParams );

    /**
     * Get the number of unique names returned for the given search, grouped by MagicCard name
     * 
     * @param searchParams
     *            The search parameters to use for a future byName search
     * @return The number of names that will result from a byName search with the given model
     */
    int numberOfUniqueNamesWithSearchParameters( List<SearchParameter> searchParams );

}

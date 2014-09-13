package com.christopherfebles.magic.dao.parameter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.christopherfebles.magic.enums.DatabaseSearchable;

import static com.christopherfebles.magic.dao.MagicDAOConstants.*;

/**
 * Define a generic, reusable, loopable database search parameter.<br>
 * <br>
 * A list of SearchParameter objects is ultimately converted to a SQL Where clause in SearchDAO.
 * 
 * @see com.christopherfebles.magic.dao.SearchDAO
 * @author Christopher Febles
 *
 */
public class SearchParameter {

    /**
     * This number is appended to the parameterName to keep it unique across SearchParameter instances.
     */
    private static long parameterCount = 0L;

    private boolean isAnd;
    private String searchText;
    private FieldName fieldName;
    private String parameterName;

    /**
     * Create a new AND SearchParameter with a provided DatabaseSearchable object.
     * 
     * @see FieldName
     * @see DatabaseSearchable
     * 
     * @param fieldName
     *            An Enum representing the database column to search.
     * @param searchObj
     *            A DatabaseSearchable object whose value should be used in this search.
     */
    public SearchParameter( FieldName fieldName, DatabaseSearchable searchObj ) {
        this( fieldName, searchObj.getDatabaseSearchText(), true );
    }

    /**
     * Create a new AND/OR SearchParameter with a provided DatabaseSearchable object.
     * 
     * @see FieldName
     * @see DatabaseSearchable
     * 
     * @param fieldName
     *            An Enum representing the database column to search.
     * @param searchObj
     *            A DatabaseSearchable object whose value should be used in this search.
     * @param isAnd
     *            True for AND, False for OR
     */
    public SearchParameter( FieldName fieldName, DatabaseSearchable searchObj, boolean isAnd ) {
        this( fieldName, searchObj.getDatabaseSearchText(), isAnd );
    }

    /**
     * Create a new AND SearchParameter with a provided search string.
     * 
     * @see FieldName
     * 
     * @param fieldName
     *            An Enum representing the database column to search.
     * @param searchText
     *            The string to use in this search.
     */
    public SearchParameter( FieldName fieldName, String searchText ) {
        this( fieldName, searchText, true );
    }

    /**
     * Create a new AND/OR SearchParameter with a provided search string.<br>
     * <br>
     * Primary Constructor.
     * 
     * @see FieldName
     * 
     * @param fieldName
     *            An Enum representing the database column to search.
     * @param searchText
     *            The string to use in this search.
     * @param isAnd
     *            True for AND, False for OR
     */
    public SearchParameter( FieldName fieldName, String searchText, boolean isAnd ) {
        this.setFieldName( fieldName );
        this.setSearchText( searchText );
        this.setIsAnd( isAnd );
        this.setParameterName( fieldName.name() + parameterCount );
        parameterCount++;
    }

    private void setIsAnd( boolean isAnd ) {
        this.isAnd = isAnd;
    }

    private void setParameterName( String parameterName ) {
        this.parameterName = parameterName;
    }

    /**
     * Get the name to use in parameterized SQL queries.<br>
     * <br>
     * This name is guaranteed (but not thread-safe) to be unique across all SearchParameter objects.
     * 
     * @return The parameterName to use in SQL queries
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Does this SearchParameter represent an OR search?
     * 
     * @return True if OR, false otherwise.
     */
    public boolean isOr() {
        return !isAnd;
    }

    /**
     * Does this SearchParameter represent an AND search?
     * 
     * @return True if AND, false otherwise.
     */
    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd( boolean isAnd ) {
        this.isAnd = isAnd;
    }

    /**
     * Get the value to search on in the database.
     * 
     * @return The value to include in a SQL query to the database. This value may include wildcards.
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Set the text to search on in the database. This value may include wildcards.
     * 
     * @param searchText
     *            The text to search on in the database. This value may include wildcards.
     */
    public void setSearchText( String searchText ) {
        this.searchText = searchText;
    }

    /**
     * Get the column (field) to search on within the database.
     * 
     * @return The field to search on in the database.
     */
    public FieldName getFieldName() {
        return fieldName;
    }

    /**
     * Set the search column (field) for this SearchParameter.
     * 
     * @param fieldName
     *            An enumeration object that represents the column to search.
     */
    public void setFieldName( FieldName fieldName ) {
        this.fieldName = fieldName;
    }

    @Override
    /**
     * Determines equality via Reflection, excluding parameterName.
     */
    public boolean equals( Object o ) {
        if ( !( o instanceof SearchParameter ) ) {
            return false;
        }
        return EqualsBuilder.reflectionEquals( this, o, "parameterName" );
    }

    @Override
    /**
     * HashCode calculated via Reflection, excluding parameterName.
     */
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode( this, "parameterName" );
    }

    /**
     * An enumeration of database columns for use when creating new SearchParameters.
     * 
     * @author Christopher Febles
     *
     */
    public enum FieldName {

        COLOR( ALL_CARDS_PREFIX + "color" ), NAME( ALL_CARDS_PREFIX + "name" ), TYPE( CARD_TYPE_PREFIX + "type_name" ), SUBTYPE( CARD_TYPE_PREFIX + "type_name" ), LANGUAGE(
                ALL_CARDS_PREFIX + "language" ), EXPANSION( ALL_CARDS_PREFIX + "expansion" ), OWNED( MY_CARDS_PREFIX + "multiverse_id" );

        private String columnName;

        private FieldName( String columnName ) {
            this.setColumnName( columnName );
        }

        /**
         * Get the database column (including table alias/prefix) represented by this FieldName.<br>
         * <br>
         * This value is hardcoded into the Enum.
         * 
         * @return Get the column to search in the database.
         */
        public String getColumnName() {
            return columnName;
        }

        /**
         * Reset the database column to be represented by this FieldName.<br>
         * <br>
         * Previously public, this method is now private. Since this is an Enum, this value probably shouldn't change.
         * 
         * @param columnName
         *            The column to search in the database.
         */
        private void setColumnName( String columnName ) {
            this.columnName = columnName;
        }

        /**
         * Get the table to which this FieldName's column belongs to.<br>
         * <br>
         * This value isn't hardcoded, as the column name is. Rather, it is determined dynamically based on the column's prefix(alias).
         * 
         * @return The table to search in the database.
         */
        public String getTableName() {
            String tableName = ALL_CARDS_TABLE;

            if ( this.getColumnName().startsWith( CARD_TYPE_PREFIX ) ) {
                tableName = CARD_TYPE_TABLE;
            } else if ( this.getColumnName().startsWith( MY_CARDS_PREFIX ) ) {
                tableName = MY_CARDS_TABLE;
            }

            return tableName;
        }

        /**
         * Get the prefix (alias) of the table to which this FieldName's column belongs to.<br>
         * <br>
         * This method internally refers to {@link #getTableName()}.
         * 
         * @see #getTableName()
         * @return The prefix (alias) of the table to search in the database.
         */
        public String getTablePrefix() {
            String prefix = ALL_CARDS_PREFIX;

            if ( this.getTableName().equals( CARD_TYPE_TABLE ) ) {
                prefix = CARD_TYPE_PREFIX;
            } else if ( this.getTableName().equals( MY_CARDS_TABLE ) ) {
                prefix = MY_CARDS_PREFIX;
            }

            return prefix;
        }
    }

    @Override
    /**
     * String generated via Reflection.
     */
    public String toString() {
        return ReflectionToStringBuilder.toString( this, ToStringStyle.MULTI_LINE_STYLE );
    }

}

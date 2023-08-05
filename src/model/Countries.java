package model;

/**
 * Represents a country with its details and properties.
 */
public class Countries {
    private int country_id;
    private String country_name;
    /**
     * Constructs a Country object with specified details.
     *
     * @param country_id The ID of the country.
     * @param country_name The name of the country.
     */
    public Countries(int country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }
    /**
     * Returns the ID of the country.
     *
     * @return The ID of the country.
     */
    public int getId() {return country_id;}
    /**
     * Returns the name of the country.
     *
     * @return The name of the country.
     */
    public String getName() {return country_name;}
    /**
     * Returns a string representation of the country's name.
     *
     * @return The name of the country.
     */
    @Override
    public String toString(){
        return (country_name);
    }
}

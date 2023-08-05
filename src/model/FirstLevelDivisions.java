package model;

/**
 * Represents a first-level division with its details and properties.
 */
public class FirstLevelDivisions {
    private int division_id;
    private String division;
    private int country_id;
    /**
     * Constructs a FirstLevelDivisions object with specified details.
     *
     * @param division_id The ID of the first-level division.
     * @param division The name of the first-level division.
     * @param country_id The ID of the country associated with the division.
     */
    public FirstLevelDivisions(int division_id, String division, int country_id) {
        this.division_id = division_id;
        this.division = division;
        this.country_id = country_id;
    }
    /**
     * Returns the id of the first-level division.
     *
     * @return The id of the first-level division.
     */
    public int getDivision_id() {
        return division_id;
    }
    /**
     * Returns the name of the first-level division.
     *
     * @return The name of the first-level division.
     */
    public String getDivision() {
        return division;
    }
    /**
     * Returns the Country id of the first-level division.
     *
     * @return The Country id of the first-level division.
     */
    public int getCountry_id() {
        return country_id;
    }
    /**
     * Sets the id of the first-level division.
     *
     * @param division_id The id of the first-level division to set.
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }
    /**
     * Sets the name of the first-level division.
     *
     * @param division The name of the first-level division to set.
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * Sets the related country of the first-level division.
     *
     * @param country_id The related country of the first-level division to set.
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * Returns a string representation of the division's name.
     *
     * @return The name of the division.
     */
    @Override
    public String toString() {
        return (division);
    }

}

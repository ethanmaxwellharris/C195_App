package model;

/**
 * Represents a contact with its details and properties.
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Constructs a Contact object with specified details.
     *
     * @param contactId The ID of the contact.
     * @param contactName The name of the contact.
     * @param email The email of the contact.
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Returns a string representation of the contact's ID and name.
     *
     * @return A formatted string containing the contact's ID and name.
     */
    @Override
    public String toString(){ return "#" + String.valueOf(contactId) + " " + (contactName); }
    /**
     * Returns the contact ID.
     *
     * @return The contact ID.
     */
    public int getContactId() {
        return contactId;
    }
    /**
     * Sets the contact ID.
     *
     * @param contactId The new contact ID.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**
     * Returns the contact name.
     *
     * @return The contact name.
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * Sets the contact name.
     *
     * @param contactName The new contact name.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * Returns the email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

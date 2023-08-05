package model;

/**
 * The Users class represents user information in the application.
 * Each user has a unique user ID, a username, and a password.
 */
public class Users {
    private int userId;
    private String userName;
    private String password;

    /**
     * Constructs a new Users object with the specified user details.
     *
     * @param userId   The unique ID of the user.
     * @param userName The username of the user.
     * @param password The password of the user.
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
    /**
     * Returns a string representation of the user's username.
     *
     * @return The username of the user as a string.
     */
    @Override
    public String toString(){
        return (userName);
    }
    /**
     * Returns the user's unique ID.
     *
     * @return The user's unique ID.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the user's unique ID.
     *
     * @param userId The new unique ID for the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Sets the username of the user.
     *
     * @param userName The new username for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

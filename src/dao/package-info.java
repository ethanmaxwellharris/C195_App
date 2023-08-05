/**
 * The `dao` package contains Data Access Object (DAO) classes responsible for interacting with
 * the database and providing methods to manage various data entities within the appointment scheduling system.
 * These classes handle database operations such as querying, inserting, updating, and deleting data records.
 * <p>
 * DAO Classes Included:
 * <p>
 * - {@link dao.DBAppointments}:
 *   Provides methods to access and manipulate appointment data stored in the database.
 *   Supports operations like retrieving appointments, creating new appointments, and deleting appointments.
 * </p>
 * - {@link dao.DBConnection}:
 *   Manages the database connection and serves as a utility class to establish and close database connections.
 *   Ensures efficient and reliable interaction between the application and the database.
 * <p>
 * - {@link dao.DBContacts}:
 *   Handles CRUD (Create, Read, Update, Delete) operations for contact information in the database.
 *   Allows retrieval of contact details and associated appointments.
 * </p>
 * - {@link dao.DBCountries}:
 *   Provides methods to manage country data, including fetching country names and related information.
 * <p>
 * - {@link dao.DBCustomers}:
 *   Handles customer data management in the database.
 *   Offers operations to retrieve customer records, create new customers, and update customer details.
 * </p>
 * - {@link dao.DBFirstLevelDivisions}:
 *   Manages first-level division data, which represents subdivisions within a country.
 *   Supports operations like fetching division details, names, and associated country information.
 * <p>
 * - {@link dao.DBReports}:
 *   Provides methods to generate various reports and statistical information based on the stored data.
 *   Supports data retrieval for business analysis and decision-making.
 * <p>
 * - {@link dao.DBUsers}:
 *   Handles user data management, authentication, and authorization.
 *   Supports login verification, user role management, and secure access control.
 * </p>
 * The DAO classes collaborate closely with the database to ensure proper handling of data operations
 * while abstracting database-specific details from the rest of the application.
 * They provide a structured interface for the application to interact with the database,
 * helping maintain separation of concerns and promoting data integrity.
 * <p>
 * @since 1.0
 * @version 1.0
 */
package dao;
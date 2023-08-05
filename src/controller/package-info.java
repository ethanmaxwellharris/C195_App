/**
 * The `controller` package contains the controllers responsible for managing various user interactions
 * and application logic within the appointment scheduling system.
 * These controllers handle actions such as user authentication, navigation, appointment management,
 * customer management, and generating reports.
 * <p>
 * Controllers Included:
 * <p>
 * - {@link controller.AddAppointmentController}:
 *   Manages the user interface for adding new appointments to the system.
 *   Validates input data and interacts with the database to save new appointments.
 * </p>
 * - {@link controller.AddCustomerController}:
 *   Handles the user interface for adding new customers to the system.
 *   Performs data validation and database operations to store customer information.
 * <p>
 * - {@link controller.ModifyAppointmentController}:
 *   Controls the modification of existing appointments.
 *   Allows users to edit appointment details and updates the database accordingly.
 * </p>
 * - {@link controller.ModifyCustomerController}:
 *   Manages the modification of customer records.
 *   Provides functionality for updating customer information in the database.
 * <p>
 * - {@link controller.LoginController}:
 *   Handles user authentication and login functionality.
 *   Verifies user credentials, logs login attempts, and grants access to authorized users.
 * </p>
 * - {@link controller.MainScreenController}:
 *   Controls the main application screen and its components.
 *   Handles UI initialization, customer and appointment data display, and navigation.
 * <p>
 * - {@link controller.ReportsController}:
 *   Manages the generation of various reports for business analysis.
 *   Provides methods for querying and presenting appointment-related information.
 * </p>
 * The controllers in this package collaborate with data access objects (DAOs) to interact with the database,
 * as well as with other UI components to display relevant information to the user.
 * They play a crucial role in enforcing business logic, validating input, and ensuring a smooth user experience.
 * <p>
 * @since 1.0
 * @version 1.0
 */
package controller;
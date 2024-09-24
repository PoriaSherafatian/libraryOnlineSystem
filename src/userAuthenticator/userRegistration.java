package userAuthenticator;

import java.sql.*;
import java.util.Scanner;
import libraryBookData.bookInformationData;

public class userRegistration {

    public static void main(String[] args) throws Exception {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";

        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to 'POORI LIBRARY' if you are our customer please enter USER-LOGIN to Login to your account" +
                "\nor if are new please enter NEW for create nre account in our library" +
                "\nand if you are our employee please enter DASHBOARD to enter :  ");
        String authenticator = scanner.nextLine().toUpperCase();

        switch (authenticator) {
            case "USER-LOGIN":

                System.out.println("Hi dear please enter your GMAIL to login in your library account : ");
                String logInUserGmail = scanner.nextLine().toLowerCase();

                System.out.println("Please enter your account PASSCODE : ");
                int logInUserPassword = scanner.nextInt();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Poria1382");
                    Statement statement = connection.createStatement();
                    ResultSet exportUserNameFromDataBase = statement.executeQuery("SELECT userName FROM library.libraryUser;");
                    while (exportUserNameFromDataBase.next()) {
                        System.out.println("Hi '" + exportUserNameFromDataBase.getString(1).toUpperCase() + "' welcome back");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                bookInformationData.libraryBooks();
                break;

            case "NEW":

                System.out.println("Hi dear for signup please enter your GMAIL first : ");
                String signUpUserGmail = scanner.nextLine().toLowerCase();

                System.out.println("Please your USER-NAME for your account : ");
                String signUpUserName = scanner.next().toLowerCase();

                System.out.println("Please enter your PASSCODE for your account : ");
                int signUpPassCode = scanner.nextInt();

                try {
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(url, "root", "Poria1382");
                    String sql = "INSERT INTO libraryUser (userGmail, userName, passCode) VALUES (?, ?, ?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(sql);
                    preparedStmt.setString(1, signUpUserGmail);
                    preparedStmt.setString(2, signUpUserName);
                    preparedStmt.setInt(3, signUpPassCode);
                    preparedStmt.execute();
                    conn.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("Thank you for signup to our library..." +
                        "\nYour account information is :" +
                        "\nYOUR GMAIL : " + signUpUserGmail +
                        "\nYOUR PASSCODE : " + signUpPassCode +
                        "\nYOUR USER-NAME : " + signUpUserName);

                bookInformationData.libraryBooks();

                break;

            case "DASHBOARD":

                System.out.println("Please enter your NAME to login to your dashboard account : ");
                String employeeUserName = scanner.nextLine().toLowerCase();

                System.out.println("Please enter your PASSCODE for your dashboard account : ");
                int employeePassCode = scanner.nextInt();

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Poria1382")) {

                    String query = "SELECT employeeUserName, employeePassCode FROM libraryemployee WHERE employeeUserName = ? AND employeePassCode = ?";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, employeeUserName);
                        preparedStatement.setInt(2, employeePassCode);
                    }
                }

                System.out.println("Welcome to the dashboard account  " + employeeUserName.toUpperCase());
                break;

            default:
                throw new Exception("Please enter your right situation in our library!");
        }
    }


}
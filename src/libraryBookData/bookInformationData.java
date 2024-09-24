package libraryBookData;

import java.sql.*;
import java.util.Scanner;

public class bookInformationData {

    public static void libraryBooks() throws Exception {
        System.out.println("So witch book category you want to rent/read from our library \n" +
                "our available category is : \n" +
                "HISTORY\n" +
                "SIENCE\n" +
                "HORROR\n" +
                "for show all books in these category please write witch one you want  : ");
        String category = new Scanner(System.in).nextLine().toUpperCase();

        switch (category) {

            case "HISTORY":

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Poria1382");
                    Statement statement = connection.createStatement();
                    ResultSet exportBookFromCategory = statement.executeQuery("SELECT * FROM library.historybook;");
                    while (exportBookFromCategory.next()) {
                        System.out.println("\nBOOK NAME : " + exportBookFromCategory.getString(1) + "\nBOOK WRITER : " + exportBookFromCategory.getString(2) + "\nBOOK PRICE :  " + exportBookFromCategory.getString(3) + "\nPUBLISHER COMPANY NAME :  " + exportBookFromCategory.getString(4) );
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("THESE ARE OUR HISTORY BOOKS FOR EACH YOU WANT TO READ PLEASE ENTER THAT BOOK : ");

                break;

            case "SIENCE":

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Poria1382");
                    Statement statement = connection.createStatement();
                    ResultSet exportBookFromCategory = statement.executeQuery("SELECT * FROM library.siencebook;");
                    while (exportBookFromCategory.next()) {
                        System.out.println("\nBOOK NAME : " + exportBookFromCategory.getString(1) + "\nBOOK WRITER : " + exportBookFromCategory.getString(2) + "\nBOOK PRICE :  " + exportBookFromCategory.getString(3) + "\nPUBLISHER COMPANY NAME :  " + exportBookFromCategory.getString(4) );
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("THESE ARE OUR SIENCE BOOKS FOR EACH YOU WANT TO READ PLEASE ENTER THAT BOOK : ");
                break;

            case "HORROR":

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Poria1382");
                    Statement statement = connection.createStatement();
                    ResultSet exportBookFromCategory = statement.executeQuery("SELECT * FROM library.horrorbook;");
                    while (exportBookFromCategory.next()) {
                        System.out.println("\nBOOK NAME : " + exportBookFromCategory.getString(1) + "\nBOOK WRITER : " + exportBookFromCategory.getString(2) + "\nBOOK PRICE :  " + exportBookFromCategory.getString(3) + "\nPUBLISHER COMPANY NAME :  " + exportBookFromCategory.getString(4) );
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("THESE ARE OUR HORROR BOOKS FOR EACH YOU WANT TO READ PLEASE ENTER THAT BOOK : ");
                break;

            default:
                throw new Exception("PLEASE ENTER THOSE CATEGORY WE HAVE IT IN OUR LIBRARY !!!");
        }

        System.out.println("You wanna to RENT this book or BUY it please type it : ");
        String buyRent = new Scanner(System.in).nextLine().toLowerCase();
        switch (buyRent){
            case "RENT":
                System.out.println("we will sent this book as rent as soon as possible");
                break;

                case "BUY":
                    System.out.println("we will sent this book as buy as soon as possible");
                    break;

            default:
                throw new Exception("PLEASE VALID DATA");
        }

    }
}
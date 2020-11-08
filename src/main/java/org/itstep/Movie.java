package org.itstep;

import java.sql.*;
import java.util.Scanner;

public class Movie {

    private final static String URL = "jdbc:mysql://localhost/movies";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    Statement stmt = conn.createStatement();

    private Scanner scanner = new Scanner(System.in);

    public Movie() throws SQLException {
    }

    public void addMovie() throws SQLException {
        System.out.println("Введите название фильма:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into Movies(Title, RealeaseYear, Rating) value ('");
        stringBuilder.append(scanner.nextLine());
        stringBuilder.append("', ");
        System.out.println("Введите год выпуска фильма:");
        stringBuilder.append(scanner.nextInt());
        stringBuilder.append(", ");
        System.out.println("Введите рейтинг фильма:");
        stringBuilder.append(scanner.nextInt());
        stringBuilder.append(");");
        System.out.println("Столбцов измениено: " + stmt.executeUpdate(stringBuilder.toString()));
    }

    public void delByTitle() throws SQLException {
        System.out.println("Введите название фильма");
        System.out.println("Столбцов изменено: " + stmt.executeUpdate("delete from movies where Title='" + scanner.nextLine() + "';"));
    }

    public void delByRating() throws SQLException {
        System.out.println("Введите рейтинг фильма");
        System.out.println("Столбцов изменено: " + stmt.executeUpdate("delete from movies where Rating='" + scanner.nextInt() + "';"));
    }

    public void delByYear() throws SQLException {
        System.out.println("Введите год фильма");
        System.out.println("Столбцов изменено: " + stmt.executeUpdate("delete from movies where RealeaseYear='" + scanner.nextInt() + "';"));
    }

    public void delByActor() throws SQLException {
        System.out.println("Введите имя актера");
        System.out.println("Столбцов изменено: " + stmt.executeUpdate("delete from actors where FirstName='" + scanner.nextLine() + "';"));
    }

    public void delByDirector() throws SQLException {
        System.out.println("Введите имя продюсера");
        System.out.println("Столбцов изменено: " + stmt.executeUpdate("delete from directors where FirstName='" + scanner.nextLine() + "';"));
    }

    public void srchByTitle() throws SQLException {
        System.out.println("Введите название фильма");
        printTable(stmt.executeQuery("select * from movies where Title='" + scanner.nextLine() + "';"));
    }

    public void srchByRating() throws SQLException {
        System.out.println("Введите рейтинг фильма");
        printTable(stmt.executeQuery("select * from movies where Rating='" + scanner.nextInt() + "';"));
    }

    public void srchByYear() throws SQLException {
        System.out.println("Введите год фильма");
        printTable(stmt.executeQuery("select * from movies where RealeaseYear='" + scanner.nextInt() + "';"));
    }

    public void srchByActor() throws SQLException {
        System.out.println("Введите имя актера");
        printTable(stmt.executeQuery("SELECT \n" +
                "    m.*\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        ma.MovieId\n" +
                "    FROM\n" +
                "        Actors a, MovieActor ma\n" +
                "    WHERE\n" +
                "        a.FirstName = '" + scanner.nextLine() + "'\n" +
                "            AND a.ActorId = ma.ActorId) Tabl\n" +
                "            left join \n" +
                "            Movies m\n" +
                "            on Tabl.MovieId=m.MovieId;"));
    }

    public void srchByDirector() throws SQLException {
        System.out.println("Введите имя продюсера");
        printTable(stmt.executeQuery("select\n" +
                "\t\tm.*\n" +
                "\tfrom\n" +
                "\t\tDirectors d, Movies m\n" +
                "\twhere\n" +
                "\t\td.FirstName='" + scanner.nextLine() + "'\n" +
                "        and m.DirectorId=d.DirectorId;"));
    }

    public void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData rsm = rs.getMetaData();
        for (int i = 1; i < rsm.getColumnCount(); i++) {
            System.out.printf("%30s",rsm.getColumnName(i));
            if (i != rsm.getColumnCount()) System.out.print("|");
        }
        System.out.println();
        while (rs.next()){
            for (int i = 1; i < rsm.getColumnCount(); i++) {
                System.out.printf("%30s", rs.getString(i));
                if (i != rsm.getColumnCount()) System.out.print("|");
            }
            System.out.println();
        }
    }
}

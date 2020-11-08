package org.itstep;

import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Hello world!
 *
 */
public class App1
{

    public static void main( String[] args ) throws SQLException {

        Movie movie = new Movie();

        Menu mainMenu = new Menu("Главное меню.");

        mainMenu.add(new Menu("Добавить фильм.", movie::addMovie));

        Menu delete = new Menu("Удалить фильмы.");
        delete.add(new Menu("По названию.", movie::delByTitle));
        delete.add(new Menu("По рейтингу.", movie::delByRating));
        delete.add(new Menu("По году выпуска.", movie::delByYear));
        delete.add(new Menu("По актеру.", movie::delByActor));
        delete.add(new Menu("По продюсеру.", movie::delByDirector));
        mainMenu.add(delete);

        Menu search = new Menu("Поиск.");
        search.add(new Menu("По названию.", movie::srchByTitle));
        search.add(new Menu("По рейтингу.", movie::srchByRating));
        search.add(new Menu("По году выпуска.", movie::srchByYear));
        search.add(new Menu("По актеру.", movie::srchByActor));
        search.add(new Menu("По продюсеру.", movie::srchByDirector));
        mainMenu.add(search);
        mainMenu.add(new Menu("Выход."));

        while (true) {
            mainMenu.show();
        }
    }

}

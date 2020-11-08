package org.itstep;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private String title;
    private List<Menu> menuList;
    private Action action;

    public Menu(String title) {
        this(title, null);
    }

    public Menu(String name, Action action) {
        this.title = name;
        this.action = action;
        this.menuList = new ArrayList<>();
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu add(Menu menu) {
        this.menuList.add(menu);
        return menu;
    }

    public void show() throws SQLException {
        if (action != null) {
            action.doIt();
            return;
        } else {
            System.out.println(title);
            for (int i = 0; i < this.menuList.size(); i++) {
                System.out.println((i + 1) + ". " + this.menuList.get(i).toString());
            }
            Scanner scanner = new Scanner(System.in);
            int cm = scanner.nextInt() - 1;
            if ((cm == menuList.size() - 1) && (menuList.get(cm).action == null)) System.exit(0);
            menuList.get(cm).show();
            return;
        }
    }

    @Override
    public String toString() {
        return title;
    }
}

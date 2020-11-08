package org.itstep;

import java.sql.SQLException;

@FunctionalInterface
public interface Action {
    void doIt() throws SQLException;
}

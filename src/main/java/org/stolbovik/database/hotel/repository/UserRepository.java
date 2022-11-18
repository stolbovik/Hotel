package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public Optional<List<User>> readUser(@NotNull Statement statement,
                                         @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<User> users = new ArrayList<>();
        do {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setRole(resultSet.getString("Роль"));
            user.setLogin(resultSet.getString("Логин"));
            user.setPassword(resultSet.getString("Пароль"));
            users.add(user);
        } while (resultSet.next());
        return Optional.of(users);
    }

    public int updateUser(@NotNull Statement statement,
                          @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}

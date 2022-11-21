package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.User;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public Optional<List<User>> readUser(@NotNull String query) throws SQLException {
        ResultSet resultSet = Constatns.statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<User> users = new ArrayList<>();
        do {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setRole(resultSet.getInt("Роль"));
            user.setLogin(resultSet.getString("Логин"));
            user.setPassword(resultSet.getString("Пароль"));
            users.add(user);
        } while (resultSet.next());
        return Optional.of(users);
    }

    public int updateUser(@NotNull String query) throws SQLException {
        return Constatns.statement.executeUpdate(query);
    }

}

package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.models.User;
import org.stolbovik.database.hotel.repository.UserRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final Statement statement;

    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    public  UserService(@NotNull Statement statement) {
        this.userRepository = new UserRepository();
        this.statement = statement;
    }

    public Optional<User> identification(@NotNull String login) throws SQLException {
        String query = "select * from Пользователи where Логин = '" + login + "'";
        Optional<List<User>> list = userRepository.readUser(statement, query);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get().get(0));
    }

    public boolean addNewUser(@NotNull String login,
                              @NotNull String password,
                              @NotNull Role role) throws SQLException {
        if(identification(login).isPresent()) {
            throw new IllegalArgumentException("Данный логин уже занят");
        }
        String encodePassword = encoder.encodeToString(password.getBytes());
        String query = "insert into Пользователи (Логин, Пароль, Роль) values " +
                "('" + login + "', '" + encodePassword + "', " + role.getId() + ")";
        int res = userRepository.updateUser(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить нового пользователя");
        }
        return true;
    }

    public boolean authentication(@NotNull User user,
                                  @NotNull String password) {
        return password.equals(new String(decoder.decode(user.getPassword())));
    }
}

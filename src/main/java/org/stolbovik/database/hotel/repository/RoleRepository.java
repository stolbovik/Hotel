package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {

    public Optional<List<Role>> readRole(@NotNull String query) throws SQLException {
        ResultSet resultSet = Constatns.statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Role> roles = new ArrayList<>();
        do {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setRole(resultSet.getString("Роль"));
            roles.add(role);
        } while (resultSet.next());
        return Optional.of(roles);
    }

    public int updateRole(@NotNull String query) throws SQLException {
        return Constatns.statement.executeUpdate(query);
    }

}

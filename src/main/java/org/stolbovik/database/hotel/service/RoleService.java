package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.repository.RoleRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class RoleService {

    private final RoleRepository repository;
    private final Statement statement;

    public RoleService(@NotNull Statement statement) {
        this.repository = new RoleRepository();
        this.statement = statement;
    }

    public Role getRoleByName(@NotNull String name) throws SQLException {
        String query = "select * from Роли where Роль = '" + name + "'";
        Optional<List<Role>> list = repository.readRole(statement, query);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Такой роли не существует ");
        }
        return list.get().get(0);
    }

}

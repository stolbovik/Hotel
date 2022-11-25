package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.repository.RoleRepository;
import org.stolbovik.database.hotel.utils.Constatns;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class RoleService {

    private final RoleRepository repository;
    private final Statement statement;

    public RoleService() {
        this.repository = new RoleRepository();
        this.statement = Constatns.STATEMENT;
    }

    public Role getRoleByName(@NotNull String name) throws SQLException, IllegalArgumentException {
        String query = "select * from Роли where Роль = '" + name + "'";
        Optional<List<Role>> list = repository.readRole(statement, query);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Такой роли не существует ");
        }
        return list.get().get(0);
    }

    public Role getRoleById(int id) throws SQLException, IllegalArgumentException {
        String query = "select * from Роли where ID = '" + id + "'";
        Optional<List<Role>> list = repository.readRole(statement, query);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Роли с таким id нет");
        }
        return list.get().get(0);
    }

}

package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Role;
import org.stolbovik.database.hotel.models.User;
import org.stolbovik.database.hotel.service.RoleService;
import org.stolbovik.database.hotel.service.UserService;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController() {
        this.userService = new UserService();
        this.roleService = new RoleService();
    }

    public Role loginIn(@NotNull String login,
                        @NotNull String password) throws SQLException, IllegalArgumentException {
        User user = userService.identification(login);
        userService.authentication(user, password);
        return roleService.getRoleById(user.getRole());
    }

}

package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Equipment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipmentRepository {

    public Optional<List<Equipment>> readEquipments(@NotNull Statement statement,
                                                    @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Equipment> equipments = new ArrayList<>();
        do {
            Equipment equipment = new Equipment();
            equipment.setId(resultSet.getInt("id"));
            equipment.setName(resultSet.getString("Название"));
            equipment.setStatusOfUsage(resultSet.getBoolean("Статус использования"));
            equipments.add(equipment);
        } while (resultSet.next());
        return Optional.of(equipments);
    }

    public int updateEquipment(@NotNull Statement statement,
                               @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}

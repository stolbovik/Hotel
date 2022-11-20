package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Equipment;
import org.stolbovik.database.hotel.repository.EquipmentRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final Statement statement;


    public EquipmentService(@NotNull Statement statement) {
        this.equipmentRepository = new EquipmentRepository();
        this.statement = statement;
    }

    public List<Equipment> getFreeEquipmentByName(@NotNull String name) throws SQLException, IllegalArgumentException {
        String query = "select * from [Спец.оборудование] where Название = '" + name + "'" +
                "and [Статус использования] = 0";
        Optional<List<Equipment>> list = equipmentRepository.readEquipments(statement, query);
        if (list.isEmpty()){
            throw new IllegalArgumentException("Нет свободного оборудования данного типа");
        }
        return list.get();
    }

    public Equipment getEquipmentById(int id) throws SQLException, IllegalArgumentException {
        String query = "select * from [Спец.оборудование] where ID = " + id;
        Optional<List<Equipment>> list = equipmentRepository.readEquipments(statement, query);
        if (list.isEmpty()){
            throw new IllegalArgumentException("Нет свободного оборудования данного типа");
        }
        return list.get().get(0);
    }

    public boolean setStatusOfEmployment(@NotNull Equipment equipment, boolean status) throws SQLException {
        int temp;
        if (status) {
            temp = 1;
        } else {
            temp = 0;
        }
        String query = "update [Спец.оборудование] set [Статус использования] = " + temp +
                " where ID = " + equipment.getId();
        int res = equipmentRepository.updateEquipment(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус занятости у оборудования");
        }
        return true;
    }

}

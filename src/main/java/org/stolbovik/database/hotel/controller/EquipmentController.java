package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Equipment;
import org.stolbovik.database.hotel.service.EquipmentService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController() {
        this.equipmentService = new EquipmentService();
    }

    public List<String> getFreeEquipment() throws SQLException, IllegalArgumentException{
        List<Equipment> list = equipmentService.getFreeEquipment();
        List<String> names = new ArrayList<>();
        for (Equipment equipment : list) {
            names.add(equipment.getName());
        }
        return names;
    }

}

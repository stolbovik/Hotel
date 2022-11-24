package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.service.PaidServiceService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaidServiceController {

    private final PaidServiceService paidServiceService;

    public PaidServiceController() {
        this.paidServiceService = new PaidServiceService();
    }

    public List<String> getAllPaidServiceWithPrice() throws SQLException, IllegalArgumentException{
        List<PaidService> list = paidServiceService.getAllPaidservice();
        List<String> names = new ArrayList<>();
        for (PaidService paidService : list) {
            names.add(paidService.getName() + " (" + paidService.getPrice() + " рублей)");
        }
        return names;
    }

    public PaidService getPaidServiceByName(@NotNull String name) throws SQLException, IllegalArgumentException{
        return paidServiceService.getPaidServiceByName(name);
    }

}

package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.repository.PaidServiceRepository;
import org.stolbovik.database.hotel.utils.Constatns;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PaidServiceService {

    private final PaidServiceRepository paidServiceRepository;
    private final Statement statement;

    public PaidServiceService() {
        this.paidServiceRepository = new PaidServiceRepository();
        this.statement = Constatns.STATEMENT;
    }

    public List<PaidService> getAllPaidservice() throws SQLException, IllegalArgumentException {
        String query = "select * from [Платные услуги]";
        Optional<List<PaidService>> list = paidServiceRepository.readPaidServices(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new IllegalArgumentException("В отеле нет платных услуг");
    }

    public PaidService getPaidServiceByName(@NotNull String name) throws SQLException, IllegalArgumentException {
        String query = "select * from [Платные услуги] where Название = '" + name + "'";
        Optional<List<PaidService>> list = paidServiceRepository.readPaidServices(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new IllegalArgumentException("Данной платной услуги нет");
    }
}

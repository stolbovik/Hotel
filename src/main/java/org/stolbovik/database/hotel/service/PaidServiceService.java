package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.repository.PaidServiceRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PaidServiceService {

    private final PaidServiceRepository paidServiceRepository;
    private final Statement statement;

    public PaidServiceService(@NotNull Statement statement) {
        this.paidServiceRepository = new PaidServiceRepository();
        this.statement = statement;
    }

    public PaidService getPaidServiceByName(@NotNull String name) throws SQLException {
        String query = "select * from [Платные услуги] where Название = '" + name + "'";
        Optional<List<PaidService>> list = paidServiceRepository.readPaidServices(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Данной платной услуги нет");
    }
}

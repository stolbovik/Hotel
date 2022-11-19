package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;
import org.stolbovik.database.hotel.repository.OrderedServiceRepository;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OrderedServiceService {

    private final OrderedServiceRepository orderedServiceRepository;
    private final Statement statement;

    private OrderedServiceService(@NotNull Statement statement) {
        this.orderedServiceRepository = new OrderedServiceRepository();
        this.statement = statement;
    }

    public boolean addNewOrderedService(@NotNull ServiceWithEmployee serviceWithEmployee,
                                        @NotNull Date date,
                                        @NotNull Booking booking) throws SQLException {
        String query = "insert into [Заказанные услуги] ([Услуга и исполнитель], Дата, [Номер комнаты]) values (" +
                serviceWithEmployee.getId() + ", '" + HelpFunction.dateToSqlDate(date) + "', " + booking.getId() +
                ")";
        int res = orderedServiceRepository.updateOrderedService(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить заявку на платную услугу");
        }
        return true;
    }

}

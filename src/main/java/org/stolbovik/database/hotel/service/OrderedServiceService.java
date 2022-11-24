package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.OrderedService;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;
import org.stolbovik.database.hotel.repository.OrderedServiceRepository;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderedServiceService {

    private final OrderedServiceRepository orderedServiceRepository;
    private final Statement statement;

    public OrderedServiceService() {
        this.orderedServiceRepository = new OrderedServiceRepository();
        this.statement = Constatns.STATEMENT;
    }

    public boolean addNewOrderedService(@NotNull ServiceWithEmployee serviceWithEmployee,
                                        @NotNull Date date,
                                        @NotNull Booking booking) throws SQLException {
        if (getRequestByServiceWithEmployeeAndDate(serviceWithEmployee, date).isPresent()) {
            throw new IllegalArgumentException("На данную дату услуга от данного сотрудника уже заказана");
        }
        String query = "insert into [Заказанные услуги] ([Услуга и исполнитель], Дата, [Номер комнаты]) values (" +
                serviceWithEmployee.getId() + ", '" + HelpFunction.dateToSqlDate(date) + "', " + booking.getId() +
                ")";
        int res = orderedServiceRepository.updateOrderedService(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить заявку на платную услугу");
        }
        return true;
    }

    private Optional<OrderedService> getRequestByServiceWithEmployeeAndDate(@NotNull ServiceWithEmployee serviceWithEmployee,
                                                                            @NotNull Date date) throws SQLException {
        String query = "select * from [Заказанные услуги] where [Услуга и исполнитель] = " +
                serviceWithEmployee.getId() + " and Дата = '" + HelpFunction.dateToSqlDate(date) + "'";
        Optional<List<OrderedService>> list = orderedServiceRepository.readOrderedServices(statement, query);
        return list.map(orderedServices -> orderedServices.get(0));
    }

}

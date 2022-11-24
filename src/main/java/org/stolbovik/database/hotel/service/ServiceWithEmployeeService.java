package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;
import org.stolbovik.database.hotel.models.OrderedService;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;
import org.stolbovik.database.hotel.repository.EmployeeRepository;
import org.stolbovik.database.hotel.repository.OrderedServiceRepository;
import org.stolbovik.database.hotel.repository.ServiceWithEmployeeRepository;
import org.stolbovik.database.hotel.utils.Constatns;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ServiceWithEmployeeService {

    private final ServiceWithEmployeeRepository serviceWithEmployeeRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderedServiceRepository orderedServiceRepository;
    private final Statement statement;

    public ServiceWithEmployeeService() {
        this.serviceWithEmployeeRepository = new ServiceWithEmployeeRepository();
        this.employeeRepository = new EmployeeRepository();
        this.orderedServiceRepository = new OrderedServiceRepository();
        this.statement = Constatns.STATEMENT;
    }

    public ServiceWithEmployee getServiceWithFreeEmployeeNow(@NotNull PaidService paidService) throws SQLException {
        String query = "select * from [Сопоставление услуг и исполнителей] where [id услуги] = " + paidService.getId();
        Optional<List<ServiceWithEmployee>> list = serviceWithEmployeeRepository.readServiceWithEmployee(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данную платную услугу мы пока не оказываем");
        }
        Optional<ServiceWithEmployee> serviceWithEmployee = Optional.empty();
        for(ServiceWithEmployee service: list.get()) {
            query = "select * from Сотрудники where ID = " + service.getIdEmployee();
            Employee employee = employeeRepository.readEmployees(statement, query).get().get(0);
            if (!employee.getStatusOfEmployment()) {
                serviceWithEmployee = Optional.of(service);
                break;
            }
        }
        if(serviceWithEmployee.isPresent()) {
            return serviceWithEmployee.get();
        }
        throw new SQLException("Все сотрудники этой услуги сейчас заняты");
    }

    public ServiceWithEmployee getServiceWithFreeEmployeeAndDate(@NotNull PaidService paidService,
                                                                 @NotNull Date date) throws SQLException {
        String query = "select * from [Сопоставление услуг и исполнителей] where [id услуги] = " + paidService.getId();
        Optional<List<ServiceWithEmployee>> list = serviceWithEmployeeRepository.readServiceWithEmployee(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Данную платную услугу мы пока не оказываем");
        }
        Optional<ServiceWithEmployee> serviceWithEmployee = Optional.empty();
        for(ServiceWithEmployee service: list.get()) {
            query = "select * from [Заказанные услуги] where [Услуга и исполнитель] = " + service.getId() +
            " AND Дата = '" + HelpFunction.dateToSqlDate(date) + "'";
            Optional<List<OrderedService>> list1 = orderedServiceRepository.readOrderedServices(statement, query);
            if (list1.isEmpty()) {
                serviceWithEmployee = Optional.of(service);
                break;
            }
        }
        if(serviceWithEmployee.isPresent()) {
            return serviceWithEmployee.get();
        }
        throw new SQLException("Все сотрудники этой услуги заняты на данную дату");
    }

}

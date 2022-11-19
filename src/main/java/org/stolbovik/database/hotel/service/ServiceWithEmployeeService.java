package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Employee;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;
import org.stolbovik.database.hotel.repository.EmployeeRepository;
import org.stolbovik.database.hotel.repository.ServiceWithEmployeeRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ServiceWithEmployeeService {

    private final ServiceWithEmployeeRepository serviceWithEmployeeRepository;
    private final EmployeeRepository employeeRepository;
    private final Statement statement;

    public ServiceWithEmployeeService(@NotNull Statement statement) {
        this.serviceWithEmployeeRepository = new ServiceWithEmployeeRepository();
        this.employeeRepository = new EmployeeRepository();
        this.statement = statement;
    }

    public ServiceWithEmployee getServiceWithFreeEmployee(@NotNull PaidService paidService) throws SQLException {
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
        throw new SQLException("К сожалению, все сотрудники этой услуги заняты");
    }
}

package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.*;
import org.stolbovik.database.hotel.repository.*;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ServiceWithEmployeeRepository serviceWithEmployeeRepository;
    private final OrderedServiceRepository orderedServiceRepository;
    private final RequestForCleaningRepository requestForCleaningRepository;
    private final PostRepository postRepository;
    private final Statement statement;

    public EmployeeService(@NotNull Statement statement) {
        this.employeeRepository = new EmployeeRepository();
        this.serviceWithEmployeeRepository = new ServiceWithEmployeeRepository();
        this.orderedServiceRepository = new OrderedServiceRepository();
        this.requestForCleaningRepository = new RequestForCleaningRepository();
        this.postRepository = new PostRepository();
        this.statement = statement;
    }

    public List<Employee> getEmployees() throws SQLException {
        String query = "select * from Сотрудники";
        Optional<List<Employee>> list = employeeRepository.readEmployees(statement, query);
        if (list.isPresent()) {
            return list.get();
        }
        throw new SQLException("Нет сотрудников в базе данных\n");
    }

    public List<Employee> getFreeEmployeeByPost(@NotNull Post post) throws SQLException {
        String query = "select * from Сотрудники where [ID должности] = " + post.getId() +
                " and Занят = 0";
        Optional<List<Employee>> list = employeeRepository.readEmployees(statement, query);
        if (list.isEmpty()) {
            throw new SQLException("Нет свободных сотрудников этой должности\n");
        }
        return list.get();
    }

    public boolean addNewEmployee(@NotNull String firstName,
                                  @NotNull String lastName,
                                  @NotNull String fatherName,
                                  @NotNull String passport,
                                  @NotNull Post post,
                                  @NotNull Date birthday) throws SQLException {
        String query = "insert into Сотрудники (ФИО, Паспорт, [Дата рождения], [ID Должности]) " +
                "values ('" + firstName + " " + lastName + fatherName + "', " + passport
                + ", '" + HelpFunction.dateToSqlDate(birthday) + "'," + post.getId() + ")";
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить сотрудника в базу");
        }
        return true;
    }

    public Employee getEmployeeByPassport(@NotNull String passport) throws SQLException {
        String query = "select * from Сотрудники where Паспорт = " + passport;
        Optional<List<Employee>> list = employeeRepository.readEmployees(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new SQLException("Данного сотрудника нет в базе");
    }

    public boolean deleteEmployee(@NotNull Employee employee) throws SQLException {
        if (employee.getStatusOfEmployment()) {
            throw new IllegalArgumentException("Нельзя уволить сотрудника пока он работает");
        }
        String query = "select * from [Сопоставление услуг и исполнителей] where [id работника] = " + employee.getId();
        Optional<List<ServiceWithEmployee>> list = serviceWithEmployeeRepository.readServiceWithEmployee(statement, query);
        if (list.isPresent()) {
            for (ServiceWithEmployee serviceWithEmployee: list.get()) {
                query = "delete from [Заказанные услуги] where [Услуга и исполнитель] = " + serviceWithEmployee.getId();
                orderedServiceRepository.updateOrderedService(statement, query);
            }
            query = "delete from [Сопоставление услуг и исполнителей] where [id работника] = " + employee.getId();
            int res = serviceWithEmployeeRepository.updateServiceWithEmployee(statement, query);
            if (res <= 0) {
                throw new SQLException("Не удалось удалить услугу из 'Сопоставление услуг и исполнителей'");
            }
        }
        query = "delete from [Заявки на уборку] where Выполняющий = " + employee.getId();
        requestForCleaningRepository.updateRequestForCleaning(statement, query);
        query = "delete from Сотрудники where ID = " + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось удалить сотрудника");
        }
        return true;
    }

    public boolean setStatusOfEmployment(@NotNull Employee employee, boolean status) throws SQLException {
        int temp;
        if (status) {
            temp = 1;
        } else {
            temp = 0;
        }
        String query = "update Сотрудники set Занят = " + temp + " where ID = " + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось изменить статус занятости у сотрудника");
        }
        return true;
    }

    public boolean checkEmployeeByEquipment(@NotNull Employee employee) {
        return employee.getIdOfEquipment() == null;
    }

    public boolean setEquipment(@NotNull Employee employee,
                                @NotNull Equipment equipment) throws SQLException {
        String query = "update Сотрудники set [ID Спец.оборудования] = " + equipment.getId() +
                " where ID = " + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось назначить оборудование сотруднику");
        }
        return true;
    }

    public boolean deleteEquipment(@NotNull Employee employee) throws SQLException {
        String query = "update Сотрудники set [ID Спец.оборудования] = NULL where ID = "
                + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось удалить оборудование у сотруднику");
        }
        return true;
    }

    public boolean increaseOfPrize(@NotNull Employee employee,
                                   int prize) throws SQLException {
        if (prize <= 0) {
            throw new IllegalArgumentException("Необходимо положительное число для прибавки");
        }
        String query = "update Сотрудники set Прибавка = " + (employee.getPrize() + prize) +
                " where ID = " + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось увеличить прибавку сотруднику");
        }
        return true;
    }

    public boolean decreaseOfPrize(@NotNull Employee employee,
                                   int prize) throws SQLException {
        if (prize <= 0) {
            throw new IllegalArgumentException("Необходимо положительное число для прибавки");
        }
        if ((employee.getPrize() - prize) < 0) {
            throw new IllegalArgumentException("Нельзя уменьшить прибавку меньше нуля");
        }
        String query = "update Сотрудники set Прибавка = " + (employee.getPrize() - prize) +
                " where ID = " + employee.getId();
        int res = employeeRepository.updateEmployee(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось уменьшить прибавку сотруднику");
        }
        return true;
    }

    public int getSumSalary(@NotNull Date start,
                            @NotNull Date end) throws SQLException {
        List<Employee> list = getEmployees();
        long countDay = HelpFunction.getDayBetweenDate(start, end);
        int salary = 0;
        for(Employee employee: list) {
            String query = "select * from Должности where ID = " + employee.getIdOFPost();
            int salary1 = postRepository.readPost(statement, query).get().get(0).getSalary();
            float koeff = 1 + (float)employee.getPrize()/100;
            salary += salary1 * countDay * koeff;
        }
        return salary;
    }

}

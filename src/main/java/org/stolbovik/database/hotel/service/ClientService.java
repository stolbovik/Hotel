package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.repository.ClientRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;
    private final Statement statement;

    public ClientService(@NotNull Statement statement) {
        this.clientRepository = new ClientRepository();
        this.statement = statement;
    }

    public Client getClientByPassport(@NotNull String passport) throws SQLException, IllegalArgumentException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Данного клиента нет в баззе");
        }
        return list.get().get(0);
    }

    public boolean checkClientByPassport(@NotNull String passport) throws SQLException, IllegalArgumentException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        return list.isPresent();
    }

    public void addClient(@NotNull String firstName,
                          @NotNull String lastName,
                          @NotNull String fatherName,
                          @NotNull String passport) throws SQLException {
        String query =  "insert into Клиенты (ФИО, Паспорт) values ('"
                        + firstName + ' ' + lastName + ' ' + fatherName + "', " +
                        passport + ")";
        int res = clientRepository.updateClient(statement, query);
        if (res != 1) {
            throw new SQLException("Не удалось добавить вас в систему отеля");
        }
    }

}

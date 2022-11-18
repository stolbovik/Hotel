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

    public ClientService(Statement statement) {
        this.clientRepository = new ClientRepository();
        this.statement = statement;
    }

    public Optional<Client> getClientByPassport(@NotNull String passport) throws SQLException {
        String query = "select * from Клиенты where Паспорт = " + passport;
        Optional<List<Client>> list = clientRepository.readClients(statement, query);
        return list.map(clients -> clients.get(0));
    }

    public boolean addClient(@NotNull String firstName,
                             @NotNull String lastName,
                             @NotNull String fatherName,
                             @NotNull String passport) throws SQLException {
        String query =  "insert into Клиенты (ФИО, Паспорт) values ("
                        + firstName + ' ' + lastName + ' ' + fatherName + ", " +
                        passport + ")";
        int res = clientRepository.updateClient(statement, query);
        if (res == 1) {
            return true;
        } else {
            throw new SQLException("Не удалось добавить вас в систему отеля");
        }
    }

}

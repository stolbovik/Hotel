package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {

    public Optional<List<Client>> readClients(@NotNull Statement statement,
                                              @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<Client> clients = new ArrayList<>();
        do {
            Client client = new Client();
            client.setId(resultSet.getInt("id"));
            client.setFIO(resultSet.getString("ФИО"));
            client.setPassport(resultSet.getLong("Паспорт"));
            clients.add(client);
        } while (resultSet.next());
        return Optional.of(clients);
    }

    public int updateClient(@NotNull Statement statement,
                            @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}

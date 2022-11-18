package org.stolbovik.database.hotel.repository;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.EntryInBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComplaintBookRepository {

    public Optional<List<EntryInBook>> readEntrys(@NotNull Statement statement,
                                                        @NotNull String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        List<EntryInBook> entrysInBooks = new ArrayList<>();
        do {
            EntryInBook entryInBook = new EntryInBook();
            entryInBook.setId(resultSet.getInt("ID"));
            entryInBook.setIdOfClient(resultSet.getInt("ID клиента"));
            entryInBook.setText(resultSet.getString("Сообщение"));
            entryInBook.setAnswer(resultSet.getString("Ответ администратора"));
            entrysInBooks.add(entryInBook);
        } while (resultSet.next());
        return Optional.of(entrysInBooks);
    }
    public int updateComplaintBook(@NotNull Statement statement,
                                        @NotNull String query) throws SQLException {
        return statement.executeUpdate(query);
    }

}

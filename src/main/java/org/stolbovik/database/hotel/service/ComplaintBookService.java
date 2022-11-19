package org.stolbovik.database.hotel.service;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.EntryInBook;
import org.stolbovik.database.hotel.models.StatusOfCleaningRequest;
import org.stolbovik.database.hotel.repository.ComplaintBookRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ComplaintBookService {

    private final ComplaintBookRepository complaintBookRepository;
    private final Statement statement;

    public ComplaintBookService(@NotNull Statement statement){
        this.complaintBookRepository = new ComplaintBookRepository();
        this.statement = statement;
    }

    public EntryInBook getEntryByClient(@NotNull Client client) throws SQLException {
        String query = "select * from [Книга жалоб и предложений] where [ID клиента] = " + client.getId();
        Optional<List<EntryInBook>> list = complaintBookRepository.readEntrys(statement, query);
        if (list.isPresent()) {
            return list.get().get(0);
        }
        throw new IllegalArgumentException("Записей от данного клиента нет");
    }

    public boolean addTextToEntry(@NotNull EntryInBook entryInBook,
                                  @NotNull String message) throws SQLException {
        String query = "update [Книга жалоб и предложений] set Сообщение = '" + entryInBook.getText() +
                ' ' + message + "' where ID = " + entryInBook.getId();
        int res = complaintBookRepository.updateComplaintBook(statement, query);
        if (res != 1) {
            throw  new SQLException("Не удалось добавить запись к клиенту");
        }
        return true;
    }

    public boolean addNewEnrty(@NotNull String message,
                               @NotNull Client client) throws SQLException {
        String query = "inset into [Книга жалоб и предложений] ([ID клиента], Сообщение) " +
                "values (" + client.getId() + ", '" + message + "')";
        int res = complaintBookRepository.updateComplaintBook(statement, query);
        if (res != 1) {
            throw  new SQLException("Не удалось добавить запись клиента в книгу");
        }
        return true;
    }

    public boolean addAnswerToEntry(@NotNull EntryInBook entryInBook,
                                    @NotNull String answer) throws SQLException {
        String query = "update [Книга жалоб и предложений] set [Ответ администратора] = '" +
                entryInBook.getAnswer() + " " + answer + "' where ID = " + entryInBook.getId();
        int res = complaintBookRepository.updateComplaintBook(statement, query);
        if (res != 1) {
            throw  new SQLException("Не удалось добавить ответ на запись клиента");
        }
        return true;
    }

}

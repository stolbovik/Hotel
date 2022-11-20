package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.EntryInBook;
import org.stolbovik.database.hotel.service.ClientService;
import org.stolbovik.database.hotel.service.ComplaintBookService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ComplaintBookController {

    private final ComplaintBookService complaintBookService;
    private final ClientService clientService;

    private ComplaintBookController(@NotNull Statement statement) {
        this.complaintBookService = new ComplaintBookService(statement);
        this.clientService = new ClientService(statement);
    }

    public void acceptComplaintOfClient(@NotNull String passport,
                                        @NotNull String message,
                                        @NotNull String answer) throws SQLException, IllegalArgumentException {
        Client client = clientService.getClientByPassport(passport);
        Optional<EntryInBook> entryInBook = complaintBookService.getEntryByClient(client);
        if (entryInBook.isEmpty()) {
            complaintBookService.addNewEntry(message, client);
            entryInBook = complaintBookService.getEntryByClient(client);
        } else {
            complaintBookService.addTextToEntry(entryInBook.get(), message);
        }
        complaintBookService.addAnswerToEntry(entryInBook.get(), answer);
    }


}

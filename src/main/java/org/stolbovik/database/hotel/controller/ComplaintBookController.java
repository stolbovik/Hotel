package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Client;
import org.stolbovik.database.hotel.models.EntryInBook;
import org.stolbovik.database.hotel.service.ClientService;
import org.stolbovik.database.hotel.service.ComplaintBookService;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ComplaintBookController {

    private final ComplaintBookService complaintBookService;
    private final ClientService clientService;

    public ComplaintBookController() {
        this.complaintBookService = new ComplaintBookService();
        this.clientService = new ClientService();
    }

    public void acceptComplaintOfClient(@NotNull String passport,
                                        @NotNull String message,
                                        @NotNull String answer) throws SQLException, IllegalArgumentException {
        HelpFunction.checkPassport(passport);
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

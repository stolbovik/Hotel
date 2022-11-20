package org.stolbovik.database.hotel.controller;

import org.jetbrains.annotations.NotNull;
import org.stolbovik.database.hotel.models.Booking;
import org.stolbovik.database.hotel.models.PaidService;
import org.stolbovik.database.hotel.models.ServiceWithEmployee;
import org.stolbovik.database.hotel.service.BookingService;
import org.stolbovik.database.hotel.service.OrderedServiceService;
import org.stolbovik.database.hotel.service.PaidServiceService;
import org.stolbovik.database.hotel.service.ServiceWithEmployeeService;
import org.stolbovik.database.hotel.utils.HelpFunction;

import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OrderedServiceController {

    private final OrderedServiceService orderedServiceService;
    private final PaidServiceService paidServiceService;
    private final BookingService bookingService;
    private final ServiceWithEmployeeService serviceWithEmployeeService;

    private OrderedServiceController(@NotNull Statement statement) {
        this.orderedServiceService = new OrderedServiceService(statement);
        this.paidServiceService = new PaidServiceService(statement);
        this.bookingService = new BookingService(statement);
        this.serviceWithEmployeeService = new ServiceWithEmployeeService(statement);
    }

    public int orderPaidService(@NotNull String name,
                                @NotNull String passport,
                                @NotNull Date date) throws SQLException, IllegalArgumentException {
        HelpFunction.checkDate(date);
        HelpFunction.checkPassport(passport);
        Booking booking = bookingService.getTodayBookingByPassport(passport);
        if (date.compareTo(booking.getDateOfDeparture()) > 0) {
            throw new IllegalArgumentException("Закзать услугу можно только на время бронирования номера");
        }
        PaidService paidService = paidServiceService.getPaidServiceByName(name);
        ServiceWithEmployee serviceWithEmployee = serviceWithEmployeeService.
                getServiceWithFreeEmployeeAndDate(paidService, date);
        orderedServiceService.addNewOrderedService(serviceWithEmployee, date, booking);
        return paidService.getPrice();
    }

}

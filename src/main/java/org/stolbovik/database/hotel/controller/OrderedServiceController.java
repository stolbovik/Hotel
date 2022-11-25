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

import java.sql.SQLException;
import java.util.Date;

public class OrderedServiceController {

    private final OrderedServiceService orderedServiceService;
    private final PaidServiceService paidServiceService;
    private final BookingService bookingService;
    private final ServiceWithEmployeeService serviceWithEmployeeService;

    public OrderedServiceController() {
        this.orderedServiceService = new OrderedServiceService();
        this.paidServiceService = new PaidServiceService();
        this.bookingService = new BookingService();
        this.serviceWithEmployeeService = new ServiceWithEmployeeService();
    }

    public void orderPaidService(@NotNull String name,
                                 @NotNull String passport,
                                 @NotNull Date date) throws SQLException, IllegalArgumentException {
        HelpFunction.checkDate(date);
        HelpFunction.checkPassport(passport);
        Booking booking = bookingService.getTodayBookingByPassport(passport);
        if (date.compareTo(booking.getDateOfDeparture()) > 0) {
            throw new IllegalArgumentException("Заказ услуги только на время бронирования");
        }
        PaidService paidService = paidServiceService.getPaidServiceByName(name);
        ServiceWithEmployee serviceWithEmployee = serviceWithEmployeeService.
                getServiceWithFreeEmployeeAndDate(paidService, date);
        orderedServiceService.addNewOrderedService(serviceWithEmployee, date, booking);
    }

}

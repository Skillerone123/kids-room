package ua.softserveinc.tc.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserveinc.tc.constants.MailConstants;
import ua.softserveinc.tc.constants.QuartzConstants;
import ua.softserveinc.tc.dto.BookingDto;
import ua.softserveinc.tc.entity.Booking;
import ua.softserveinc.tc.entity.BookingState;
import ua.softserveinc.tc.service.BookingService;

import ua.softserveinc.tc.service.MailService;
import ua.softserveinc.tc.util.DateUtil;
import ua.softserveinc.tc.util.Log;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nestor on 29.06.2016.
 */

@Service(QuartzConstants.SEND_REMINDER)
public class SendReminderJob {
    @Log
    private static org.slf4j.Logger logger;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MailService mailService;

    private void task() {
        Date today = DateUtil.dateNow().getTime();
        Date tomorrow = DateUtil.dateTomorrow().getTime();
        bookingService.getBookings(today, tomorrow, BookingState.BOOKED)
                .stream()
                .collect(Collectors.groupingBy(Booking::getUser))
                .forEach((recipient, bookings) -> {
                    try {
                        List<BookingDto> dtos = bookings
                                .stream().map(Booking::getDto).collect(Collectors.toList());
                        mailService.sendReminder(recipient, MailConstants.REMINDER_SUBJECT, dtos);
                    } catch (MessagingException me){
                        logger.error("Error sending e-mail", me);
                    }
                });

    }
}
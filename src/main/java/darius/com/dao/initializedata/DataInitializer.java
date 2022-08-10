package darius.com.dao.initializedata;

import darius.com.databasemock.DatabaseMock;
import darius.com.model.*;
import darius.com.util.DecimalUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInitializer {
    public DataInitializer() {
        DataInitializer.InitializeUserData();
        DataInitializer.InitializeTrainData();
        DataInitializer.InitializeBookingData();
    }

    private static DatabaseMock database = DatabaseMock.database;

    public static void InitializeUserData() {
        User user = new User("Darius", "1234", new BigDecimal(5000L));
        User user2 = new User("Nicolae", "1234", new BigDecimal(2000L));

        database.addUser(user);
        database.addUser(user2);
    }

    public static void InitializeTrainData() {
        Long trainNumber = 1L;
        Map<TrainClass, Long> trainClassToAvailableSeats = new HashMap<TrainClass, Long>() {{
            Long numberOfAvailableSeats = 40L;
            put(TrainClass.AC, numberOfAvailableSeats);
            Long numberOfAvailableSeats2 = 20L;
            put(TrainClass.SL, numberOfAvailableSeats2);
        }};
        Map<TrainClass, Long> trainClassToBookedSeats = new HashMap<TrainClass, Long>() {{
            Long numberOfBookedSeats = 5L;
            put(TrainClass.AC, numberOfBookedSeats);
            Long numberOfBookedSeats2 = 3L;
            put(TrainClass.SL, numberOfBookedSeats2);
        }};
        Map<TrainClass, BigDecimal> trainTicketPricePerClass = new HashMap<TrainClass, BigDecimal>() {{
            BigDecimal ticketPricePerClass = new BigDecimal(52.52D).round(MathContext.DECIMAL64).setScale(4);
            put(TrainClass.AC, ticketPricePerClass);
            BigDecimal ticketPricePerClass2 = new BigDecimal(57.25D).round(MathContext.DECIMAL64).setScale(4);
            put(TrainClass.SL, ticketPricePerClass2);
        }};
        Train train = new Train(trainNumber, trainClassToAvailableSeats, trainClassToBookedSeats, trainTicketPricePerClass);

        Long trainNumber2 = 2L;
        Map<TrainClass, Long> trainClassToAvailableSeats2 = new HashMap<TrainClass, Long>() {{
            Long numberOfAvailableSeats = 255L;
            put(TrainClass.AC, numberOfAvailableSeats);
            Long numberOfAvailableSeats2 = 52L;
            put(TrainClass.SL, numberOfAvailableSeats2);
        }};
        Map<TrainClass, Long> trainClassToBookedSeats2 = new HashMap<TrainClass, Long>() {{
            Long numberOfBookedSeats = 1L;
            put(TrainClass.AC, numberOfBookedSeats);
            Long numberOfBookedSeats2 = 2L;
            put(TrainClass.SL, numberOfBookedSeats2);
        }};
        Map<TrainClass, BigDecimal> trainTicketPricePerClass2 = new HashMap<TrainClass, BigDecimal>() {{
            BigDecimal ticketPricePerClass = new BigDecimal(12.55D).round(MathContext.DECIMAL64).setScale(4);
            put(TrainClass.AC, ticketPricePerClass);
            BigDecimal ticketPricePerClass2 = new BigDecimal(5.5624D).round(MathContext.DECIMAL64).setScale(4);
            put(TrainClass.SL, ticketPricePerClass2);
        }};
        Train train2 = new Train(trainNumber2, trainClassToAvailableSeats2, trainClassToBookedSeats2, trainTicketPricePerClass2);

        database.addTrain(train);
        database.addTrain(train2);
    }

    public static void InitializeBookingData() {
        List<Ticket> bookedTickets = new ArrayList<>();
        String bookedByUsername = "Darius";
        bookedTickets.add(new Ticket(bookedByUsername, 1L, 1L, 1L, TrainClass.SL,
                DecimalUtil.roundAndScaleBigDecimal(new BigDecimal(200.24D))));
        bookedTickets.add(new Ticket(bookedByUsername, 2L, 2L, 3L,
                TrainClass.AC, DecimalUtil.roundAndScaleBigDecimal(new BigDecimal(800.525D))));
        bookedTickets.add(new Ticket(bookedByUsername, 3L, 1L, 6L, TrainClass.SL, new BigDecimal(100L)));
        bookedTickets.add(new Ticket(bookedByUsername, 4L, 2L, 1L, TrainClass.AC, new BigDecimal(160L)));
        bookedTickets.add(new Ticket(bookedByUsername, 5L, 1L, 1L, TrainClass.AC, new BigDecimal(2500L)));

        String bookedByUsername2 = "Nicolae";
        List<Ticket> bookedTickets2 = new ArrayList<>();
        bookedTickets2.add(new Ticket(bookedByUsername2, 6L, 1L, 1L,
                TrainClass.AC, DecimalUtil.roundAndScaleBigDecimal(new BigDecimal(600.2664D))));
        bookedTickets2.add(new Ticket(bookedByUsername2, 7L, 2L, 3L, TrainClass.AC, new BigDecimal(700L)));
        bookedTickets2.add(new Ticket(bookedByUsername2, 8L, 2L, 6L, TrainClass.SL, new BigDecimal(200L)));
        bookedTickets2.add(new Ticket(bookedByUsername2, 9L, 2L, 1L,
                TrainClass.SL, DecimalUtil.roundAndScaleBigDecimal(new BigDecimal(820.634D))));
        bookedTickets2.add(new Ticket(bookedByUsername2, 10L, 1L, 1L, TrainClass.AC, new BigDecimal(300L)));


        database.addBooking(new Booking(bookedTickets));
        database.addBooking(new Booking(bookedTickets2));

    }
}

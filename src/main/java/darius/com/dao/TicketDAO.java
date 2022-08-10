package darius.com.dao;

import darius.com.databasemock.DatabaseMock;
import darius.com.exception.InvalidCredentialsException;
import darius.com.model.*;
import darius.com.util.PriceCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class TicketDAO {
    private Scanner scanner = new Scanner(System.in);
    ;
    private static DatabaseMock database = DatabaseMock.database;

    private String currentUserUsername = "";

    public void login(String username, String password) throws InvalidCredentialsException {
        if (!database.validateUser(username, password)) {
            throw new InvalidCredentialsException();
        } else {
            List<Ticket> ticketsForUser = database.getAllTicketsByUser(username);
            currentUserUsername = username;
            System.out.println(ticketsForUser);
            System.out.println("“No more train details are available");
        }
    }

    public void showTrains() {
        Map<Long, Train> availableTrains = database.getAvailableTrains();
        availableTrains.forEach((id, train) -> {
            System.out.println("Train number: " + id + " | train info: " + train);
        });
    }

    public void bookTicket(Long trainNumber) {
        System.out.println("Enter the train class SL/AC");
        String trainClassString = scanner.nextLine();
        TrainClass trainClass = TrainClass.valueOf(trainClassString);
        System.out.println("Enter the number of seats");
        Long numberOfSeats = scanner.nextLong();
        BigDecimal ticketPrice = database.calculatePriceForTicketNumberAndTicketClass(numberOfSeats, trainClass, trainNumber);
        if(trainClass == TrainClass.AC){
            ticketPrice = ticketPrice.add(new BigDecimal(200L));
        }
        User user = database.findUserByUsername(currentUserUsername);


        Ticket ticket = new Ticket(currentUserUsername, Math.abs(new Random().nextLong()),
                trainNumber, numberOfSeats, trainClass, ticketPrice);
        user.setBalance(user.getBalance().subtract(ticketPrice));
        database.addBooking(new Booking(Arrays.asList(ticket)));
        System.out.println("Booked ticket details: " + ticket);
        System.out.println("Current user balance: " + user.getBalance());
    }

    public void cancelTicket(Long ticketNumber) {
        User user = database.findUserByUsername(currentUserUsername);
        Ticket ticket = database.findTicketByUsername(user.getUsername(), ticketNumber);
        BigDecimal subtractAmount = PriceCalculator.penaltyAmountByTrainClass(ticket.getTrainClass());

        BigDecimal refundAmount = ticket.getPrice().subtract(subtractAmount);
        System.out.println("Refund amount: " + refundAmount);

        user.setBalance(user.getBalance().subtract(subtractAmount));
        database.cancelTicket(ticketNumber);
        System.out.println("Current user balance: " + user.getBalance());
    }
}

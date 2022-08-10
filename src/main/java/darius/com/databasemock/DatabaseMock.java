package darius.com.databasemock;

import darius.com.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseMock {
    public static final DatabaseMock database = new DatabaseMock();
    private final List<Booking> bookedTickets;
    private final Map<Long, Train> availableTrains;
    private final Map<String, User> availableUsers;

    private DatabaseMock() {
        this.bookedTickets = new ArrayList<>();
        this.availableTrains = new HashMap<>();
        this.availableUsers = new HashMap<>();
    }

    public void addTrain(Train train) {
        availableTrains.put(train.getTrainNumber(), train);
    }

    public void addUser(User user) {
        availableUsers.put(user.getUsername(), user);
    }

    public void addBooking(Booking booking) {
        bookedTickets.add(booking);
    }

    public List<Ticket> getAllTickets() {
        return bookedTickets.stream().flatMap(bookedTicket -> bookedTicket.getBookedTickets().stream()).collect(Collectors.toList());
    }

    public List<Booking> getBookedTickets() {
        return bookedTickets;
    }

    public Map<Long, Train> getAvailableTrains() {
        return availableTrains;
    }

    public Map<String, User> getAvailableUsers() {
        return availableUsers;
    }

    public Train findTrainByTrainNumber(Long trainNumber){
        return availableTrains.values().stream().filter(train -> train.getTrainNumber().equals(trainNumber)).findFirst().orElse(null);
    }

    public BigDecimal calculatePriceForTicketNumberAndTicketClass(Long numberOfTickets, TrainClass trainClass, Long trainNumber){
        Train train = findTrainByTrainNumber(trainNumber);
        BigDecimal pricePerTicketForClass = train.getTrainTicketPricePerClass().get(trainClass);
        return pricePerTicketForClass.multiply(BigDecimal.valueOf(numberOfTickets));
    }

    public void clearBookings() {
        bookedTickets.clear();
    }

    public void clearTrains() {
        availableTrains.clear();
    }

    public void clearUsers() {
        availableUsers.clear();
    }


    public void clearAll() {
        clearBookings();
        clearTrains();
        clearUsers();
    }

    public boolean validateUser(String username, String password){
        User user = availableUsers.get(username);
        return user.getPassword().equals(password);
    }

    public List<Ticket> getAllTicketsByUser(String username) {
        return getAllTickets().stream().filter(booking -> booking.getBookedByUsername().equals(username)).collect(Collectors.toList());
    }

    public Ticket findTicketByUsername(String username, Long ticketNumber){
        return getAllTicketsByUser(username).stream().filter(ticket -> ticket.getTicketNumber().equals(ticketNumber)).findFirst().orElse(null);
    }

    public User findUserByUsername(String username){
        return availableUsers.values().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
    public void cancelTicket(Long tickerNumber){
        bookedTickets.stream().forEach(booking -> booking.getBookedTickets().removeIf(ticket -> ticket.getTicketNumber().equals(tickerNumber)));
    }
}


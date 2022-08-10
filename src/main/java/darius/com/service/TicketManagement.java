package darius.com.service;

import darius.com.dao.TicketDAO;
import darius.com.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketManagement {

    private final TicketDAO ticketDAO;

    @Autowired
    public TicketManagement(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void login(String username, String password){
        try {
            ticketDAO.login(username, password);
        } catch (InvalidCredentialsException e) {

        }
    }

    public void showTrains(){
        ticketDAO.showTrains();
    }

    public void bookTicket(Long trainNumber){
        ticketDAO.bookTicket(trainNumber);
    }

    public void cancelTicket(Long tickerNumber){
        ticketDAO.cancelTicket(tickerNumber);
    }

}

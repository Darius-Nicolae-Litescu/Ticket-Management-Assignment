package darius.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    String bookedByUsername;
    Long ticketNumber;
    Long trainNumber;
    Long numberOfSeats;
    TrainClass trainClass;
    BigDecimal price;

    @Override
    public String toString() {
        return "Ticket{" +
                "bookedByUsername='" + bookedByUsername + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", trainNumber=" + trainNumber +
                ", numberOfSeats=" + numberOfSeats +
                ", trainClass=" + trainClass +
                ", price=" + price +
                '}' + "\n";
    }
}

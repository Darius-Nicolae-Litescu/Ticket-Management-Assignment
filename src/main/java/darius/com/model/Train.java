package darius.com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Train {
    private Long trainNumber;
    private Map<TrainClass, Long> trainClassToAvailableSeats;
    private Map<TrainClass, Long> trainClassToBookedSeats;
    private Map<TrainClass, BigDecimal> trainTicketPricePerClass;

}

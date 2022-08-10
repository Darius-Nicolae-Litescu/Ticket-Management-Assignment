package darius.com.util;

import darius.com.model.Train;
import darius.com.model.TrainClass;

import java.math.BigDecimal;

public class PriceCalculator {

    public static BigDecimal penaltyAmountByTrainClass(TrainClass trainClass){
        return trainClass.equals(TrainClass.AC) ? new BigDecimal(100L): new BigDecimal(50L);
    }

}

package ru.croc.task10.auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

// The user participating in the bids
public class User implements Runnable {
    private final String userName;
    private Lot lot;

    public User (String userName, Lot lot) {
        this.userName = userName;
        this.lot = lot;
    }

    @Override
    public void run() {
        Random randPrice = new Random();
        while (LocalDateTime.now().isBefore(getLot().getAuctionEndTime())) {
            getLot().makeBet(BigDecimal.valueOf(randPrice.nextInt()), getUserName());
        }
    }

    public String getUserName() {
        return userName;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }
}

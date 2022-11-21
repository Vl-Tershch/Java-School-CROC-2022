package ru.croc.task10.auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// The lot participating in the bids
public class Lot {
    private BigDecimal currentPrice;
    private String authorName;
    private LocalDateTime auctionEndTime;
    private static final ReentrantLock lock = new ReentrantLock();

    public Lot(BigDecimal currentPrice, LocalDateTime auctionEndTime) {
        this.currentPrice = currentPrice;
        this.auctionEndTime = auctionEndTime;
    }

    // Thread-safe bet
    public void makeBet(BigDecimal newPrice, String newName) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(getAuctionEndTime())) {
            lock.lock();
            try {
                if (newPrice.compareTo(getCurrentPrice()) > 0) {
                    setCurrentPrice(newPrice);
                    setAuthorName(newName);
                    System.out.println("Bet was accepted. User " + newName + " is now a leader with " +
                            newPrice + " costs.");
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // Information message about winner
    public String winnerOfLot() {
        return "Congratulations, " + getAuthorName() + " ! You win this lot!";
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(LocalDateTime auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "currentPrice=" + currentPrice +
                ", authorName='" + authorName + '\'' +
                ", auctionEndTime=" + auctionEndTime +
                '}';
    }
}

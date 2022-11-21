package ru.croc.task10;

import ru.croc.task10.auction.Lot;
import ru.croc.task10.auction.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Task10 {
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);
        Lot firstLot = new Lot(BigDecimal.valueOf(0), endTime);
        System.out.println(firstLot);
        System.out.println("The bidding has begun!\n");

        // Bid modulation
        int count = 0;
        Thread[] threads = new Thread[100];
        while (count < 100) {
            threads[count] = new Thread(new User(Integer.toString(count), firstLot));
            threads[count].start();
            count++;
        }
        for (int i = 0; i < 100; i++){
            threads[i].join();
        }
        System.out.println("The bidding has ended!");
        System.out.println(firstLot.winnerOfLot());
    }
}

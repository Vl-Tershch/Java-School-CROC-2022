package ru.croc.task9;

import ru.croc.task9.password.PasswordConfig;
import ru.croc.task9.password.PasswordSearcher;
import java.util.concurrent.ExecutionException;

public class Task9 {
    public static void main(String[] args) {
        // vladter -> 81874FBE496B2970FD0155E2E733B0C8
        // taskshe -> 5C7F93E42CE6514EBDCF6F056A630272
        int numberOfThreads = Integer.parseInt(args[0]);
        String passwordHash = args[1];

        String pass = null;
        try {
            PasswordConfig newPass = new PasswordConfig(7, 26);
            PasswordSearcher passwordSearcher = new PasswordSearcher(numberOfThreads, passwordHash, newPass);
            pass = passwordSearcher.findByBruteForce();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Found password: " + pass);

        System.exit(0);
    }
}

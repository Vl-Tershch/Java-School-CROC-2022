package ru.croc.task15;

import ru.croc.task15.utils.AgeGroup;
import ru.croc.task15.utils.GroupsCreater;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        // Exmaple of data for test:
        //18 25 35 45 60 80 100
        //Кошельков Захар Брониславович,105
        //Старостин Ростислав Ермолаевич,50
        //Егоров Алан Петрович,7
        //Ярилова Розалия Трофимовна,29
        //Дьячков Нисон Иринеевич,88
        //Соколов Андрей Сергеевич,15
        //Иванов Варлам Якунович,88

        System.out.println("[1] Enter the sequence of age group boundaries:");
        Scanner scanner = new Scanner(System.in);
        String ageBoundaries = scanner.nextLine();
        System.out.println("[2] Enter the sequence users. Enter END to stop! :");
        String curUser = "";
        List<String[]> users = new ArrayList<>();
        do {
            curUser = scanner.nextLine();
            if (!curUser.equals("END")) {
                users.add(curUser.split(","));
            }
        } while (!curUser.equals("END"));

        System.out.println("\nCreated age groups and members: ");
        List<AgeGroup> createdGroups = GroupsCreater.createGroups(ageBoundaries, users);
        for (int i = createdGroups.size() - 1; i >= 0; i--) {
            System.out.println(createdGroups.get(i));
        }
    }
}

// Example of using:
//[1] Enter the sequence of age group boundaries:
//18 25 35 45 60 80 100
//[2] Enter the sequence users. Enter END to stop! :
//Кошельков Захар Брониславович,105
//Старостин Ростислав Ермолаевич,50
//Егоров Алан Петрович,7
//Ярилова Розалия Трофимовна,29
//Дьячков Нисон Иринеевич,88
//Соколов Андрей Сергеевич,15
//Иванов Варлам Якунович,88
//END
//
//Created age groups and members:
//101+: [Кошельков Захар Брониславович (105)]
//81-100: [Дьячков Нисон Иринеевич (88), Иванов Варлам Якунович (88)]
//46-60: [Старостин Ростислав Ермолаевич (50)]
//26-35: [Ярилова Розалия Трофимовна (29)]
//0-18: [Соколов Андрей Сергеевич (15), Егоров Алан Петрович (7)]
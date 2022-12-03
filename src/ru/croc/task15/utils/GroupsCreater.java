package ru.croc.task15.utils;

import ru.croc.task15.utils.comparators.UserCompare;
import java.util.ArrayList;
import java.util.List;

// Creating groups based on incoming data
public final class GroupsCreater {
    public static List<AgeGroup> createGroups(String ages, List<String[]> users) {
        List<AgeGroup> rezultGroups = new ArrayList<>();
        String[] ageBounds = ages.split(" ");
        int min = 0;
        int max = 0;
        for (int i = 0; i <= ageBounds.length; i++) {
            List<User> groupUsers = new ArrayList<>();
            String diapasone;
            if (i == 0) {
                min = 0;
                max = Integer.parseInt(ageBounds[i]);
            } else if (i == ageBounds.length){
                min = Integer.parseInt(ageBounds[i - 1]) + 1;
                max = Integer.MAX_VALUE;
            } else {
                min = Integer.parseInt(ageBounds[i - 1]) + 1;
                max = Integer.parseInt(ageBounds[i]);
            }
            for (String[] curUser : users) {
                if (Integer.parseInt(curUser[1]) >= min && Integer.parseInt(curUser[1]) <= max) {
                    groupUsers.add(new User(Integer.parseInt(curUser[1]), curUser[0]));
                }
            }
            if (!groupUsers.isEmpty()) {
                if (i == ageBounds.length) {
                    diapasone = min + "+";
                } else {
                    diapasone = min + "-" + max;
                }
                groupUsers.sort((p1, p2) -> new UserCompare().compare(p1, p2));
                rezultGroups.add(new AgeGroup(diapasone, groupUsers));
            }
        }
        return rezultGroups;
    }
}

package ru.croc.task15.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

// Creating groups based on incoming data
public final class GroupsCreator {
    public static List<AgeGroup> createGroups(String[] ages, List<String[]> users) {
        List<AgeGroup> rezultGroups = new ArrayList<>();
        Comparator<User> userCompare = (User o1, User o2) -> {
            if (Objects.equals(o1.getAge(), o2.getAge())) {
                return o1.getFio().compareTo(o2.getFio());
            }
            return o2.getAge() - o1.getAge();
        };

        int min;
        int max;
        for (int i = 0; i <= ages.length; i++) {
            List<User> groupUsers = new ArrayList<>();
            String diapasone;
            if (i == 0) {
                min = 0;
                max = Integer.parseInt(ages[i]);
            } else if (i == ages.length){
                min = Integer.parseInt(ages[i - 1]) + 1;
                max = Integer.MAX_VALUE;
            } else {
                min = Integer.parseInt(ages[i - 1]) + 1;
                max = Integer.parseInt(ages[i]);
            }
            for (String[] curUser : users) {
                int curUserAge = Integer.parseInt(curUser[1]);
                if (curUserAge >= min && curUserAge <= max) {
                    groupUsers.add(new User(curUserAge, curUser[0]));
                }
            }
            if (!groupUsers.isEmpty()) {
                if (i == ages.length) {
                    diapasone = min + "+";
                } else {
                    diapasone = min + "-" + max;
                }
                groupUsers.sort(userCompare);
                rezultGroups.add(new AgeGroup(diapasone, groupUsers));
            }
        }
        return rezultGroups;
    }
}

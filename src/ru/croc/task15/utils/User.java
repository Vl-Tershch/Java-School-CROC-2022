package ru.croc.task15.utils;

// Description of user
public class User {
    private Integer age;
    private String fio;

    public User(Integer age, String fio) {
        if (age > 123 || fio.isEmpty()) {
            throw new IllegalArgumentException("Incorrect user data!");
        }
        this.age = age;
        this.fio = fio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) {
            return false;
        }

        if (!age.equals(user.age)) return false;
        return fio.equals(user.fio);
    }

    @Override
    public int hashCode() {
        int result = age.hashCode();
        result = 31 * result + fio.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return fio + " (" + age + ')';
    }
}

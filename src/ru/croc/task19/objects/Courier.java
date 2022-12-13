package ru.croc.task19.objects;

public class Courier {
    private Integer id;
    private Integer number;
    private String firstName;
    private String lastName;

    public Courier(Integer id, Integer number, String firstName, String lastName) {
        this.id = id;
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Courier courier)) {
            return false;
        }

        if (!id.equals(courier.id)) return false;
        if (!number.equals(courier.number)) return false;
        if (!firstName.equals(courier.firstName)) return false;
        return lastName.equals(courier.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", number=" + number +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

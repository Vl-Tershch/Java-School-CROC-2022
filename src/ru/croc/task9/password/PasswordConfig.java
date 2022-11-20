package ru.croc.task9.password;

// Password Characteristics
public class PasswordConfig {
    private int passLength;
    private int alphabetLength;

    public PasswordConfig(int passLength, int alphabetLength) {
        this.passLength = passLength;
        this.alphabetLength = alphabetLength;
    }

    public int getPassLength() {
        return passLength;
    }

    public void setPassLength(int passLength) {
        this.passLength = passLength;
    }

    public int getAlphabetLength() {
        return alphabetLength;
    }

    public void setAlphabetLength(int alphabetLength) {
        this.alphabetLength = alphabetLength;
    }
}

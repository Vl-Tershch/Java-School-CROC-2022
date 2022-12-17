package ru.croc.finaltask.school.objects;

public class Word {
    private final Integer id;
    private final Integer userId;
    private final String word;
    private final String translate;
    private final Integer learned;

    public Word(Integer id, Integer userId, String word, String translate, Integer learned) {
        this.id = id;
        this.userId = userId;
        this.word = word;
        this.translate = translate;
        this.learned = learned;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

    public Integer getLearned() {
        return learned;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", userId=" + userId +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                ", learned=" + learned +
                '}';
    }
}

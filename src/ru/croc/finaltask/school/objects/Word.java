package ru.croc.finaltask.school.objects;

public class Word {
    private final Integer id;
    private final String word;
    private final String translate;

    public Word(Integer id, String word, String translate) {
        this.id = id;
        this.word = word;
        this.translate = translate;
    }

    public Integer getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}

package ru.croc.finaltask.school.dao;

import ru.croc.finaltask.school.objects.Word;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordDao {
    private final Connection connection;
    public WordDao(Connection connection) {
        this.connection = connection;
    }

    public List<Word> getAllUserWords(String username) {
        List<Word> usersWords = new ArrayList<>();
        Integer userId = 0;
        try (PreparedStatement user = this.connection.prepareStatement("select * from user where " +
                "user.username=?")) {
            user.setString(1, username);
            try (ResultSet resultSet = user.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
            }

            try (PreparedStatement words = this.connection.prepareStatement("select * from word where " +
                    "word.user_id=?")) {
                words.setInt(1, userId);
                try (ResultSet resultSet = words.executeQuery()) {
                    while (resultSet.next()) {
                        usersWords.add(new Word(resultSet.getInt("id"),
                                resultSet.getInt("user_id"), resultSet.getString("word"),
                                resultSet.getString("translate"), resultSet.getInt("learned")));
                    }
                }
            }
            return usersWords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Word findWord(String word) {
        try (PreparedStatement wordStatement = this.connection.prepareStatement("select * from word where" +
                " word.word=?")) {
            wordStatement.setString(1, word);
            try (ResultSet resultSet = wordStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Word(resultSet.getInt("id"), resultSet.getInt("user_id"),
                            resultSet.getString("word"), resultSet.getString("translate"),
                            resultSet.getInt("learned"));
                } else return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Word addWord(Integer userId, String word, String translate) {
        if (findWord(word) == null) {
            String sqlWord = "insert into word(user_id, word, translate, learned) values (" + userId +
                    ",'" + word + "','" + translate + "'," + 0 + ")";
            try (Statement statement = this.connection.createStatement()) {
                statement.execute(sqlWord);
                return findWord(word);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("[SYS]: Данное слово уже существует в системе!");
        }
        return null;
    }

    public Word updateWordLearned(String word) {
        Word curWord = findWord(word);
        if (curWord != null) {
            try (PreparedStatement wordStatement = this.connection.prepareStatement("update word set learned=1 " +
                    "where word.word=?")) {
                wordStatement.setString(1, word);
                wordStatement.executeUpdate();
                return findWord(word);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("[SYS]: Данное слово отсутствует в системе!");
        }
        return null;
    }

    public List<Word> getAllUserLearnedWords(Integer userId) {
        List<Word> usersWords = new ArrayList<>();
        try (PreparedStatement words = this.connection.prepareStatement("select * from word where word.user_id=?" +
                " and word.learned=1")) {
            words.setInt(1, userId);
            try (ResultSet resultSet = words.executeQuery()) {
                while (resultSet.next()) {
                    usersWords.add(new Word(resultSet.getInt("id"),
                            resultSet.getInt("user_id"), resultSet.getString("word"),
                            resultSet.getString("translate"), resultSet.getInt("learned")));
                }
            }
            return usersWords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Word> getAllUserNotLearnedWords(Integer userId) {
        List<Word> usersWords = new ArrayList<>();
        try (PreparedStatement words = this.connection.prepareStatement("select * from word where word.user_id=?" +
                " and word.learned=0")) {
            words.setInt(1, userId);
            try (ResultSet resultSet = words.executeQuery()) {
                while (resultSet.next()) {
                    usersWords.add(new Word(resultSet.getInt("id"),
                            resultSet.getInt("user_id"), resultSet.getString("word"),
                            resultSet.getString("translate"), resultSet.getInt("learned")));
                }
            }
            return usersWords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

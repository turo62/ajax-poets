package com.codecool.web.dao.database;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBPoemDAO extends AbstractDao implements PoemDao {
    public DBPoemDAO(Connection connection) {
        super(connection);
    }
    
    @Override
    public List<Poem> getAllByPoetId(int userId) throws SQLException {
        List<Poem> poems = new ArrayList<>();
    
        String sql = "SELECT * FROM poems WHERE poet_id=?";
    
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    poems.add(fetchPoem(resultSet));
                }
            }
        }
        return poems;
    }
    
    @Override
    public Poem getPoemById(int poemId) throws SQLException {
        String sql = "SELECT * FROM poems WHERE id =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, poemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchPoem(resultSet);
                }
            }
        }
        return null;
    }
    
    /*public Poem add(String title, String content, int userId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO poems (title, content, poet_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, title);
            statement.setString(1, content);
            statement.setInt(3, userId);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            return new Poem(id, title, content, userId);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }*/
    
    private Poem fetchPoem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        int poetId = resultSet.getInt("poet_id");
        return new Poem(id, title, content);
    }
}

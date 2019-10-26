package com.codecool.web.servlet;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.dao.database.DBPoemDAO;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/poems")
public final class PoemsServlet extends AbstractServlet {
    
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DBPoemDAO(connection);
            PoemService poemService = new SimplePoemService(poemDao);
            
            String id = req.getParameter("id");

            List<Poem> poems = poemService.getPoems(id);

            sendMessage(resp, HttpServletResponse.SC_OK, poems);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        /*} catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_OK, ex.getMessage());*/
        }
    }
}

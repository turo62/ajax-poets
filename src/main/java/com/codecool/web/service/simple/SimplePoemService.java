package com.codecool.web.service.simple;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimplePoemService implements PoemService {
    
    private final PoemDao poemDao;
    
    public SimplePoemService(PoemDao poemDao) {
        this.poemDao = poemDao;
    }
    
    @Override
    public List<Poem> getPoems(String userId) throws SQLException {
        return poemDao.getAllByPoetId(Integer.parseInt(userId));
    }
    
    @Override
    public Poem getById(String poemId) throws SQLException, ServiceException {
        try {
            Poem poem = poemDao.getPoemById(Integer.parseInt(poemId));
            if (poem == null) {
                throw new ServiceException("Unknown poem");
            }
            return poem;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}

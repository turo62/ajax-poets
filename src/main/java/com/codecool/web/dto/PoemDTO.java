package com.codecool.web.dto;

import com.codecool.web.model.Poem;

public class PoemDTO {
    
    private final Poem poem;
    
    public PoemDTO(Poem poem) {
        this.poem = poem;
    }
    
    public Poem getPoem() {
        return poem;
    }
}

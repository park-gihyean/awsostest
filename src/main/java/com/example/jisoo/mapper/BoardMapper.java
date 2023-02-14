package com.example.jisoo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.jisoo.model.Board;

@Mapper
public interface BoardMapper {
    public ArrayList<Board> boardList();

    public void boardCreate(Board board);

    public void boardUpdate(Board board);

    public void boardRemove(int boardNo);



    
}

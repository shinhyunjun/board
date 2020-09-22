package com.example.mb.mapper;

import com.example.mb.domain.Board;
import com.example.mb.domain.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    public void create(Board board) throws Exception;

    public Board read(Integer boardNo) throws Exception;

   // public void update(Board board) throws Exception;

   // public void delete(Integer boardNo) throws Exception;

    public List<Board> list(Criteria cri) throws Exception;

    public List<Board> search(@Param("title")String title) throws Exception;

    public int listCount() throws Exception;


}


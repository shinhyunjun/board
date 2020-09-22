package com.example.mb.service;

import com.example.mb.domain.Board;
import com.example.mb.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(Board board) throws Exception;

    public List<Board> list(Criteria cri) throws Exception;

    public List<Board> search(String title) throws Exception;

    public Board read(Integer boardNo) throws Exception;

    public int listCount() throws Exception;
}

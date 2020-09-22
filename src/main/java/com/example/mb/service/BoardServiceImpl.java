package com.example.mb.service;

import com.example.mb.domain.Board;
import com.example.mb.domain.Criteria;
import com.example.mb.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper mapper;

    @Override
    public void register(Board board) throws Exception{
        mapper.create(board);
    }

    @Override
    public List<Board> list(Criteria cri) throws Exception{
        return mapper.list(cri);
    }

    @Override
    public List<Board> search(String title) throws Exception{
        return mapper.search(title);
    }

    @Override
    public Board read(Integer boardNo) throws Exception{
        return mapper.read(boardNo);
    }

    public int listCount() throws Exception{
        return mapper.listCount();
    }
}

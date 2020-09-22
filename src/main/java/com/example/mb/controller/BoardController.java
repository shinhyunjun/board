package com.example.mb.controller;

import com.example.mb.domain.Board;
import com.example.mb.domain.Criteria;
import com.example.mb.domain.PageMaker;
import com.example.mb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String title, Model model) throws Exception {
        Board board = new Board();
        board.setTitle(title);

        model.addAttribute("board", board);

        model.addAttribute("list", service.search(title));

        return "board/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Model model, Criteria cri) throws Exception {
        model.addAttribute("board", new Board());

        model.addAttribute("list", service.list(cri));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(service.listCount());

        model.addAttribute("pageMaker", pageMaker);
    }

  /*  @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Board board, Model model) throws Exception {
        service.modify(board);

        model.addAttribute("msg", "수정이 완료되었습니다.");

        return "board/success";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyForm(int boardNo, Model model) throws Exception {

        model.addAttribute(service.read(boardNo));
    } */

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("boardNo") int boardNo, Model model) throws Exception {

        model.addAttribute(service.read(boardNo));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Board board, Model model) throws Exception {
        service.register(board);

        model.addAttribute("msg", "등록이 완료되었습니다.");

        return "board/success";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerForm(Board board, Model model) throws Exception {

    }
/*
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
        service.remove(boardNo);

        model.addAttribute("msg", "삭제가 완료되었습니다.");

        return "board/success";
    }
*/



}


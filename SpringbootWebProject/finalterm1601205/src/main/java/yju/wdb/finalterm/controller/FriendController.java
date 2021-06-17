package yju.wdb.finalterm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yju.wdb.finalterm.dto.FriendDTO;
import yju.wdb.finalterm.service.FriendService;

import java.util.List;

@Controller
@RequestMapping("/friends")
@Log4j2
@RequiredArgsConstructor
public class FriendController {
    @Autowired
    private FriendService service;

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("result", service.getList());
    }
}

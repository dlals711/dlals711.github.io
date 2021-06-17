package yju.wdb.finaltermm1601205.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yju.wdb.finaltermm1601205.dto.FriendDTO;
import yju.wdb.finaltermm1601205.dto.PageRequestDTO;
import yju.wdb.finaltermm1601205.service.FriendService;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", friendService.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register(){
        log.info("regiser get...");
    }

    @PostMapping("/register")
    public String registerPost(FriendDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        Long bno = friendService.register(dto);

        log.info("BNO: " + bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify" })
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){

        log.info("bno: " + bno);

        FriendDTO friendDTO = friendService.get(bno);

        log.info(friendDTO);

        model.addAttribute("dto", friendDTO);

    }


    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){


        log.info("bno: " + bno);

        friendService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";

    }

    @PostMapping("/modify")
    public String modify(FriendDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        friendService.modify(dto);

        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());

        redirectAttributes.addAttribute("bno",dto.getId());

        return "redirect:/board/read";

    }




}
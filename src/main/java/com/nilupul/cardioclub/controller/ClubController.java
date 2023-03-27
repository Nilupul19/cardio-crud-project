package com.nilupul.cardioclub.controller;

import com.nilupul.cardioclub.dto.ClubDto;
import com.nilupul.cardioclub.model.Club;
import com.nilupul.cardioclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
public class ClubController {


    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
       model.addAttribute("clubs",clubs);
       return "club-list";
    }
    @GetMapping("/clubUi")
    public String createClub(Club club){

        return "addclub";
    }

    @PostMapping("/club")
    public String saveClub(Model model, Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/club/{id}/edit")
    public String editClub(Model model, @PathVariable("id") Long cludId){
        ClubDto clubDto = clubService.findById(cludId);
        model.addAttribute("clubDto",clubDto);
        return "editclub";
    }

    @PostMapping("/club/{id}/update")
    public String updateClub(Model model, @PathVariable("id") Long updatedCid , @ModelAttribute("clubDto") ClubDto updatedDto){
        updatedDto.setId(updatedCid);
        clubService.updateClub(updatedDto);
        return "redirect:/clubs";
    }
    @GetMapping("/club/{id}")
    public String clubDetails(Model model, @PathVariable("id") Long id){
        ClubDto clubDto = clubService.findById(id);
        model.addAttribute("clubDto",clubDto);
        return "club-detail";
    }

}

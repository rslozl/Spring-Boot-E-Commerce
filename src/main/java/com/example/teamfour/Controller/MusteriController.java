package com.example.teamfour.Controller;


import com.example.teamfour.Entity.Musteri;
import com.example.teamfour.Service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class MusteriController {
    @Autowired
    MusteriService musteriService;

    @GetMapping(value = "/musteri")
    public ModelAndView yeniMusteri(ModelMap model) {
        Musteri musteri=new Musteri();
        model.addAttribute("musterilist",musteri);
        List<Musteri> liste=musteriService.findAll();
        return new ModelAndView("musteri","list",liste);
    }

    @PostMapping(value = "/musterikaydet")
    public String musteriKaydet(@RequestBody Musteri musteri, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "musteri";
        }
        musteriService.save(musteri);
        return "redirect:/musteri";
    }

    @GetMapping(value="/editmusteri/{id}")
    public String editMusteri (@PathVariable long id, ModelMap model){
        Musteri musteri=musteriService.findOne(id);
        model.addAttribute("musterilist",musteri);
        return "editmusteri";
    }

    @PostMapping(value = "/editMusteriKaydet")
    public ModelAndView editsaveMusteri(@RequestBody Musteri p){
        Musteri musteri=musteriService.findOne(p.getId());
        musteri.setAd(p.getAd());
        musteri.setAdres(p.getAdres());
        musteri.setTelno(p.getTelno());
        musteri.setVergino(p.getVergino());
        musteriService.save(musteri);
        return new ModelAndView("redirect:/musteri");
    }

    @GetMapping(value = "/deleteMusteri/{id}")
    public ModelAndView deletemusteri(@PathVariable long id){
        Musteri musteri=musteriService.findOne(id);
        musteriService.delete(musteri);
        return new ModelAndView("redirect:/musteri");
    }

}

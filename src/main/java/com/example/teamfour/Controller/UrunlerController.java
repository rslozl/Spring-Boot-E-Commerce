package com.example.teamfour.Controller;

import com.example.teamfour.Entity.Urunler;
import com.example.teamfour.Service.Impl.UrunlerServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UrunlerController {

    @Autowired
    UrunlerServiceimpl urunlerService;

    @GetMapping(value = "/urunler")
    public ModelAndView yeniUrun(ModelMap model) {
        Urunler urunler = new Urunler();
        List<Urunler> liste = (List<Urunler>) urunlerService.findAll();
        //    System.out.println("HASO"+name);
        model.addAttribute("urunlist", urunler);
        return new ModelAndView("urunler", "list", liste);
    }

    @GetMapping(value = "/urunlerara")
    public ModelAndView yeniAra(ModelMap model, @RequestParam("urunadi") String name) {
        Urunler urunler = new Urunler();
        List<Urunler> liste = urunlerService.findByUrunadi(name);
        System.out.println("listemiz" + liste);
        System.out.println("HASO" + name);
        model.addAttribute("urunlist", urunler);
        return new ModelAndView("urunler", "list", liste);
    }

    @PostMapping(value = "/urunkaydet")
    public String urunKaydet(@RequestBody Urunler urunler, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "urunler";
        }
        urunlerService.save(urunler);
        return "redirect:/urunler";
    }


    @GetMapping(value = "/editurunler/{id}")
    public String edit(@PathVariable long id, ModelMap model) {
        Urunler urunler = urunlerService.findOne(id);
        model.addAttribute("urunlist", urunler);
        return "editurunler";
    }

    @PostMapping(value = "/editurunKaydet}")
    public ModelAndView editsave(@ModelAttribute("urunlist") Urunler p) {
        Urunler urunler = urunlerService.findOne(p.getId());
        urunler.setUrunadi(p.getUrunadi());
        urunler.setUrunadedi(p.getUrunadedi());
        urunler.setUrunfiyati(p.getUrunfiyati());
        urunler.setUrunkategori(p.getUrunkategori());
        urunlerService.save(urunler);
        return new ModelAndView("redirect:/urunler");
    }
    @PostMapping(value = "/deleteUrun/{id}}")
    public ModelAndView deleteUrun(@PathVariable long id) {
        Urunler urunler = urunlerService.findOne(id);
        urunlerService.delete(urunler);
        return new ModelAndView("redirect:/urunler");
    }


}














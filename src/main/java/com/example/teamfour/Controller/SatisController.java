package com.example.teamfour.Controller;

import com.example.teamfour.Entity.Musteri;
import com.example.teamfour.Entity.Satis;
import com.example.teamfour.Entity.Urunler;
import com.example.teamfour.Service.Impl.UrunlerServiceimpl;
import com.example.teamfour.Service.MusteriService;
import com.example.teamfour.Service.SatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
@Controller
public class SatisController {
    @Autowired
    UrunlerServiceimpl urunlerService;
    @Autowired
    SatisService satisService;
    @Autowired
    MusteriService musteriService;


    @GetMapping(value = "/satis")
    public ModelAndView yeniSatis(ModelMap model,boolean hata ) {
            Map<String, Object> map = new HashMap<String, Object>();

            List<Musteri> liste=musteriService.findAll();

            List<Urunler> listee=urunlerService.findAll();
            map.put("list",listee);
            map.put("liste",liste);
            map.put("hata",false);
             if(hata){
           map.put("hata",true);
           return new ModelAndView("satis","map",map);
            }
       return new ModelAndView("satis","map",map);
    }
    @PostMapping(value = "/satiskaydet")
    public String urunKaydet(Satis satis, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes, @ModelAttribute("satislist")Satis p){
        model.addAttribute("satislist",satis);
        Urunler urunler=p.getUrunler();


        if(p.getTip().equals("Satis Faturasi"))
        {
            satis.setToplamfiyat(p.getAdetler()*p.getUrunler().getUrunfiyati());
            satis.setFiyat(p.getUrunler().getUrunfiyati());

            if(p.getUrunler().getUrunadedi()-p.getAdetler() <0){
                return "redirect:/satis?hata=true";
            }else{
                urunler.setUrunadedi(p.getUrunler().getUrunadedi()-p.getAdetler());
            }
        }else if(p.getTip().equals("Alım Faturasi")){
            satis.setToplamfiyat((p.getAdetler()*p.getFiyat()));
            satis.setFiyat(p.getFiyat());
            urunler.setUrunadedi(p.getUrunler().getUrunadedi()+p.getAdetler());
        }


        satis.setSatistarihi(new Date());
        satisService.save(satis);
        urunlerService.save(urunler);
        return "redirect:/satis";
    }

    @GetMapping(value = "/raporlar")
    public ModelAndView getAll(ModelMap model){
        List<Satis>  listee=satisService.findAll();
        Map<String ,Integer> fatura=new LinkedHashMap<>();
        int d=0;
        int e=0;
        for (int i=0;i<listee.size();i++){
            if(listee.get(i).getTip().equals("Alım Faturasi")){
                d++;
            }else if(listee.get(i).getTip().equals("Satis Faturasi")){
                    e++;
                }
            fatura.put("alım",d);
            fatura.put("satim",e);
            }
         Collections.reverse(listee);
        model.addAttribute("fatura",fatura);
        return new ModelAndView("raporlar","list",listee);
    }



}

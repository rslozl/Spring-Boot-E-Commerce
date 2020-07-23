package com.example.teamfour.Service.Impl;
import com.example.teamfour.Entity.Urunler;
import com.example.teamfour.Repository.UrunlerRepository;
import com.example.teamfour.Service.UrunlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrunlerServiceimpl implements UrunlerService {


    @Autowired
    UrunlerRepository urunlerRepository;

    public Urunler save(Urunler urunler) {
        return urunlerRepository.save(urunler);
    }

    public List<Urunler> findAll() {
        return urunlerRepository.findAll();
    }

    public Urunler findOne(long id) {
        return urunlerRepository.getOne(id);
    }

    public void delete(Urunler urunler) {
        urunlerRepository.delete(urunler);
    }

    @Override
    public Urunler findByUrunadedi(int adet) {
        return urunlerRepository.findByUrunadedi(adet);
    }

    @Override
    public List<Urunler> findByUrunadi(String name) {
        return  urunlerRepository.findByUrunadi(name);
    }


}
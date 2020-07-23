package com.example.teamfour.Service;


import com.example.teamfour.Entity.Musteri;
import com.example.teamfour.Repository.MusteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusteriService {

    @Autowired
    MusteriRepository musteriRepositor;

    public Musteri save(Musteri musteri){
        return musteriRepositor.save(musteri);
    }

    public List<Musteri> findAll(){
        return musteriRepositor.findAll();
    }

    public Musteri findOne(long id){
        return musteriRepositor.getOne(id);
    }

    public void delete(Musteri musteri){
        musteriRepositor.delete(musteri);
    }

}

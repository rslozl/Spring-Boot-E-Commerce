package com.example.teamfour.Service;

import com.example.teamfour.Entity.Satis;
import com.example.teamfour.Repository.SatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatisService{
    @Autowired
    SatisRepository satisRepository;

    public Satis save(Satis satis){
        return satisRepository.save(satis);
    }

    public List<Satis> findAll(){
        return satisRepository.findAll();
    }

}

package com.example.teamfour.Service;

import com.example.teamfour.Entity.Urunler;

import java.util.List;

public interface UrunlerService {


    Urunler findByUrunadedi(int adet);
   List<Urunler> findByUrunadi(String name);


}


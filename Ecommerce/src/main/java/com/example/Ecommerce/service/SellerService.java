package com.example.Ecommerce.service;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;


    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }


    public Seller findById(Long seller_id) {
        return sellerRepository.findById(seller_id).orElseThrow(()->new RuntimeException("Seller not found"));
    }


    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }


    public Seller update(Seller seller) {
        return sellerRepository.save(seller);
    }
    public void delete(Long seller_id) {
        sellerRepository.deleteById(seller_id);
    }
}

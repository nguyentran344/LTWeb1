package com.example.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class sellerController {
    @Autowired
    private SellerService sellerService;


    // Khởi tạo danh sách Seller
    public sellerController() {
    }


    // Trả về danh sách Seller
    @GetMapping("/sellers")
    @ResponseBody
    public List<Seller> getSellerList() {
        return sellerService.findAll();
    }


    // Trả về một User cụ thể theo ID
    @GetMapping("/sellers/{seller_id}")  // Thêm dấu ngoặc kép đóng
    public ResponseEntity<Seller> getSellerById(@PathVariable("seller_id") int sellerId) {
        // Không cần @ResponseBody vì ResponseEntity đã bao gồm body
        for (Seller seller : sellerService.findAll()) {
            if (seller.getSellerId() == sellerId) {
                return ResponseEntity.status(200).body(seller);
            }
        }
        return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy
    }


    @DeleteMapping("/sellers/{seller_id}")
    @ResponseBody
    public List<Seller> removeSellerById(@PathVariable("seller_id") Long sellerId) {
        sellerService.delete(sellerId);
        return  sellerService.findAll();
    }


    @PostMapping("/seller")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        sellerService.save(seller);
        return ResponseEntity.status(201).body(seller);
    }


    @PutMapping("/seller/{seller_id}")
    public ResponseEntity<Seller> udpateSeller(@PathVariable("seller_id") Long sellerId, @RequestBody Seller updateSeller){
        Seller seller = sellerService.findById(sellerId);
        if (seller != null) {
            seller.setFirstName(updateSeller.getFirstName());
            seller.setLastName(updateSeller.getLastName());
            seller.setEmailId(updateSeller.getEmailId());
            sellerService.save(seller);
            return ResponseEntity.status(200).body(seller);
        }
        return ResponseEntity.status(404).body(null);
    }
}

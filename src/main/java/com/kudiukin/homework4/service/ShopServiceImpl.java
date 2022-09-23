package com.kudiukin.homework4.service;

import com.kudiukin.homework4.model.Shop;
import com.kudiukin.homework4.repository.ShopRepository;
import com.kudiukin.homework4.utils.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Long deleteShop(Long shopId) throws NotFoundException {
        if (shopRepository.existsById(shopId)) {
            shopRepository.deleteById(shopId);
            return shopId;
        } else {
            throw new NotFoundException("Shop with ID #" + shopId + " is not found");
        }
    }

    @Override
    public Shop getShopById(Long shopId) throws NotFoundException {
        if (shopRepository.findById(shopId).isPresent()) {
            return shopRepository.findById(shopId).get();
        } else {
            throw new NotFoundException("Shop with ID #" + shopId + " is not found");
        }
    }

    @Override
    public List<Shop> getAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }
}

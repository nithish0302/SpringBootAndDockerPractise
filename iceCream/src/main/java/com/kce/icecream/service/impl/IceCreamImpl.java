package com.kce.icecream.service.impl;

import com.kce.icecream.entity.IceCream;
import com.kce.icecream.repository.IceCreamRepository;
import com.kce.icecream.service.IceCreamService;
import com.kce.icecream.util.NoIceCreamFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IceCreamImpl implements IceCreamService {

    IceCreamRepository iceCreamRepository;

    @Autowired
    public IceCreamImpl(IceCreamRepository iceCreamRepository) {
        this.iceCreamRepository = iceCreamRepository;
    }

    @Override
    public List<IceCream> findAll() {
        return iceCreamRepository.findAll();
    }

    @Override
    public IceCream findById(long id) {
        Optional<IceCream> existing = iceCreamRepository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        }
        else
        {
            throw new NoIceCreamFoundException("no ice cream found");
        }
    }

    @Override
    public IceCream findByName(String name) {
        Optional<IceCream>existing=iceCreamRepository.findByName(name);
        if (existing.isPresent()) {
            return existing.get();
        }
        else {
            throw new NoIceCreamFoundException("no ice cream found");
            }
    }

    @Override
    public IceCream add(IceCream iceCream) {
        return iceCreamRepository.save(iceCream);
    }

    @Override
    public IceCream updateById(long id, IceCream iceCream) {
        Optional<IceCream>existing=iceCreamRepository.findById(id);
        if (existing.isPresent()) {
            IceCream iceCream1= existing.get();
            iceCream1.setName(iceCream.getName());
            iceCream1.setDescription(iceCream.getDescription());
            iceCream1.setPrice(iceCream.getPrice());
            iceCream1.setAvailable(iceCream.isAvailable());
            iceCream1.setUpdated(new Date());
            iceCream1.setQuantity(iceCream.getQuantity());
            return iceCreamRepository.save(iceCream1);
        }
        else{
            throw new NoIceCreamFoundException("no ice cream found");
        }
    }

    @Override
    public IceCream deleteById(long id) {
        Optional<IceCream>existing=iceCreamRepository.findById(id);
        if (existing.isPresent()) {
            iceCreamRepository.delete(existing.get());
            return  existing.get();
        }
        else
        {
            throw new NoIceCreamFoundException("no ice cream found");
        }
    }
}

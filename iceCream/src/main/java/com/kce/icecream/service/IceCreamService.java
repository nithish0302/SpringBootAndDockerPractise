package com.kce.icecream.service;


import com.kce.icecream.entity.IceCream;

import java.util.List;

public interface IceCreamService {
    List<IceCream> findAll();
    IceCream findById(long id);
    IceCream findByName(String name);
    IceCream add(IceCream iceCream);
    IceCream updateById(long id, IceCream iceCream);
    IceCream deleteById(long id);


}

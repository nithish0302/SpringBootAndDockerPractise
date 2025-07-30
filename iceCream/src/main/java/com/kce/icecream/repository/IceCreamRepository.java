package com.kce.icecream.repository;

import com.kce.icecream.entity.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCream, Long> {

    public Optional<IceCream> findByName(String name);
}

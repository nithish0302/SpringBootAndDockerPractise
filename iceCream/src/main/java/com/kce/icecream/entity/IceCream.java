package com.kce.icecream.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IceCream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String description;
    private Date created = new Date();
    private boolean isAvailable;
    private  long price;
    private  Date updated=new Date();
    private  long quantity;

}

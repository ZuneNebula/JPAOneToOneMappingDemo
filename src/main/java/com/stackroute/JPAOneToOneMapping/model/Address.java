package com.stackroute.JPAOneToOneMapping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="houseNo")
    private String houseNo;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @OneToOne(mappedBy = "address")
    private User user;


}

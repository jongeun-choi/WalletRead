package com.clonecoin.walletread.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "coinRead")
@Getter
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "coinName")
    private String coinName;

    @Column(name = "avgPrice")
    private Double avgPrice;

    @Column(name = "coinQuantity")
    private Double coinQuantity;

    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    @Override
    public String toString() {
        return "coinName= " + coinName + " , avgPrice= " + avgPrice + " , coinQuantity= " + coinQuantity;
    }

    public Coin setCoin(String coinName, double avgPrice, double coinQuantity) {
        this.coinName = coinName;
        this.avgPrice = avgPrice;
        this.coinQuantity = coinQuantity;
        return this;
    }

}

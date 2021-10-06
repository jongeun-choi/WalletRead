package com.clonecoin.walletread.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "coinRead")
@Getter
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Coin 과 mapping 되는 Wallet 설정
    public Coin setWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    // 변경된 coin 정보에 따라 갱신
    public Coin setCoin(String coinName, double avgPrice, double coinQuantity) {
        this.coinName = coinName;
        this.avgPrice = avgPrice;
        this.coinQuantity = coinQuantity;
        return this;
    }

}

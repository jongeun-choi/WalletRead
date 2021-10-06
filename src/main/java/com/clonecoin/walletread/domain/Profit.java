package com.clonecoin.walletread.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profitRead")
@Getter
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profit")
    private Double profit;

    @Column(name = "investment")
    private Double investment;

    @Column(name = "registerDate")
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    @Override
    public String toString(){
        return "userId : " + wallet.getUserId() + " , profit : " + profit + " , investment : "+investment+" , localDate : " + localDate;
    }

    // Profit 생성
    public Profit createProfit(double totalProfitRatio, double investment, LocalDate localDate) {
        this.profit = totalProfitRatio;
        this.investment = investment;
        this.localDate = localDate;
        return this;
    }

    // Profit 과 mapping 되는 Wallet 설정
    public Profit setWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }
}

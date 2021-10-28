package com.clonecoin.walletread.domain;

import com.clonecoin.walletread.repository.ProfitRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "walletRead")
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId") // wallet 과 proit이 서로 역참조 되는 것을 막아줌
@JsonIdentityReference(alwaysAsId = true)
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "leaderId")
    private Long userId;

    @NotNull
    @Column(name = "leaderName")
    private String userName;

    @Column(name = "balance")
    private Double balance;

    // walletWrite(부모) Entity가 사라지면 profitWrite(자식) Entity도 사라진다.
    // profitWrite가 null이 되는 객체가 있다면 연관관계에서 delete한다.
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Profit> profits = new ArrayList<>();

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Coin> coins = new ArrayList<>();


    public Wallet(){}

    @Override
    public String toString() {
        return "id= " + id + ", userId= " + userId+" , userName : "+userName;
    }


    // Wallet 생성
    public Wallet createWallet(Long userId,String userName) {
        this.userId = userId;
        this.userName = userName;
        return this;
    }

    // Wallet 잔액 설정
    public Wallet setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    // 갱신된 Profit 리턴
    public Profit updateDayProfit(double totalProfitRatio, double investment, LocalDate localDate){
        Profit profit = new Profit();
        profit.createProfit(totalProfitRatio,investment,localDate);
        this.profits.add(profit);
        profit.setWallet(this);
        return profit;
    }

    // 해당 코인의 종류가 처음 인식되었을 때 코인Entity 생성
    public Coin createCoin(String coinName, double avgPrice, double coinQuantity) {
        Coin coin = new Coin();
        coin.setCoin(coinName, avgPrice, coinQuantity);
        this.coins.add(coin);
        coin.setWallet(this);
        return coin;
    }

}

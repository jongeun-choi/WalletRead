package com.clonecoin.walletread.domain.event;

import com.clonecoin.walletread.domain.Coin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LeaderCoinDTO {

    @JsonProperty("leaderId")
    private Long userId;

    @JsonProperty("leaderName")
    private String userName;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("coins")
    private List<Coin> coinList = new ArrayList<>();

    public LeaderCoinDTO() {}

    public LeaderCoinDTO(Long userId, String userName,Double balance, List<Coin> coinList) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
        this.coinList = coinList;
    }
}

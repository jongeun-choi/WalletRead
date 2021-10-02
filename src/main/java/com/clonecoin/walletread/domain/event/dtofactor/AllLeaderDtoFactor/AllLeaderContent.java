package com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllLeaderContent {
    @JsonProperty("leaderId")
    private Long userId;

    @JsonProperty("leaderName")
    private String userName;

    @JsonProperty("all")
    private Double all;

    @JsonProperty("best")
    private Double best;

    @JsonProperty("worst")
    private Double worst;

    @Override
    public String toString() {
        return "userId : " + userId + " , userName : " + userName + " , all : " + all + " , best : " + best + " , worst : " + worst;
    }
}

package com.clonecoin.walletread.domain.event.dtofactor.LeaderPeriodDtoFactor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaderPeriodContent {
    @JsonProperty("date")
    private LocalDate localDate;

    @JsonProperty("profit")
    private Double profit;

    @Override
    public String toString() {
        return "date= " + localDate + " , profit= " + profit;
    }
}

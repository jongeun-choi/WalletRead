package com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnalysisCoins {
    @JsonProperty("name")
    private String name;

    @JsonProperty("coinQuantity")
    private Double coinQuantity;

    @JsonProperty("avgPrice")
    private Double avgPrice;

    public AnalysisCoins() {

    }

    public AnalysisCoins(String name, double coinQuantity, double avgPrice) {
        this.name = name;
        this.coinQuantity = coinQuantity;
        this.avgPrice = avgPrice;
    }
}

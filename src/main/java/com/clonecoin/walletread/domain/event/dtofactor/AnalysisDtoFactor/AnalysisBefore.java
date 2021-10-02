package com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AnalysisBefore {
    @JsonProperty("coins")
    private List<AnalysisCoins> coins = new ArrayList<>();

    @JsonProperty("totalKRW")
    private double totalKRW;

    public AnalysisBefore() {

    }

}

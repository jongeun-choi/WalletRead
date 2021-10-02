package com.clonecoin.walletread.domain.event;

import com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor.AnalysisAfter;
import com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor.AnalysisBefore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisDTO {
    @JsonProperty("leaderId")
    private Long userId;

    @JsonProperty("before")
    private AnalysisBefore before;

    @JsonProperty("after")
    private AnalysisAfter after;

    public AnalysisDTO() {

    }

    public AnalysisDTO(Long userId, AnalysisBefore before, AnalysisAfter after) {
        this.userId = userId;
        this.before = before;
        this.after = after;
    }
}

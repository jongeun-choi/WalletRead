package com.clonecoin.walletread.domain.event;

import com.clonecoin.walletread.domain.event.dtofactor.LeaderPeriodDtoFactor.LeaderPeriodContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LeaderPeriodDTO {

    @JsonProperty("leaderId")
    private Long userId;

    @JsonProperty("profits")
    private List<LeaderPeriodContent> leaderPeriodContentList = new ArrayList<>();

    public void addLeaderPeriodContent(LeaderPeriodContent leaderPeriodContent) {
        this.leaderPeriodContentList.add(leaderPeriodContent);
    }
}

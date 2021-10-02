package com.clonecoin.walletread.domain.event;

import com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor.AllLeaderContent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AllLeaderDTO {

    @JsonProperty("information")
    List<AllLeaderContent> allLeaderContentList = new ArrayList<>();

    public void addContent(AllLeaderContent allLeaderContent){
        this.allLeaderContentList.add(allLeaderContent);
    }

}

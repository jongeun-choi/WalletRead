package com.clonecoin.walletread.domain.event;

import com.clonecoin.walletread.domain.Profit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WalletDTO {

    @JsonProperty("leaderId")
    private Long userId;

    @JsonProperty("leaderName")
    private String userName;

    @JsonProperty("profitDto")
    private ProfitDTO profitDto;

    public WalletDTO() {

    }


    @Override
    public String toString() {
        return "userId : "+userId+" , userName : "+userName;
    }

    /*
    public WalletDTO setProfits(Profit profit){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.profitsDto.add(modelMapper.map(profit, ProfitDTO.class));
        return this;
    }
     */

}

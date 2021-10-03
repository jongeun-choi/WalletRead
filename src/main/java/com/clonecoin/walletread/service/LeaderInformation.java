package com.clonecoin.walletread.service;

import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.domain.event.LeaderCoinDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor.AllLeaderContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaderInformation {
    void updateCoin(AnalysisDTO analysisDTO);
    List<AllLeaderContent> getAllLeader();
    LeaderCoinDTO getLeaderCoin(Long userId);
    LeaderPeriodDTO getLeaderPeriod(Long userId, Long period);
}

package com.clonecoin.walletread.service;

import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.domain.event.LeaderCoinDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import org.springframework.stereotype.Service;

@Service
public interface LeaderInformation {
    void updateCoin(AnalysisDTO analysisDTO);
    AllLeaderDTO getAllLeader();
    LeaderCoinDTO getLeaderCoin(Long userId);
    LeaderPeriodDTO getLeaderPeriod(Long userId, Long period);
}

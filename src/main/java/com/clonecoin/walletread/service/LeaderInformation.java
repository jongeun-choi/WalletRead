package com.clonecoin.walletread.service;

import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import org.springframework.stereotype.Service;

@Service
public interface LeaderInformation {
    AllLeaderDTO getAllLeader();
    void updateCoin(AnalysisDTO analysisDTO);
    LeaderPeriodDTO getLeaderPeriod(Long userId, Long period);
}

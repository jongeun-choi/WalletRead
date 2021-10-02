package com.clonecoin.walletread.rest;

import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import com.clonecoin.walletread.service.LeaderInformation;
import com.clonecoin.walletread.service.WalletService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/walletread")
@RequiredArgsConstructor
public class WalletResource {

    private final LeaderInformation leaderInformation;
    private final WalletService walletService;

    @GetMapping("/leaders")
    public AllLeaderDTO getLeader() {
        return leaderInformation.getAllLeader();
    }

    @GetMapping("/leader")
    public LeaderPeriodDTO getLeaderPeriod(@RequestParam(value = "leaderId") Long leaderId,
                                           @RequestParam(value = "period") Long period) {
        return leaderInformation.getLeaderPeriod(leaderId, period);
    }

    @GetMapping("/make")
    public void makeWallet() {
        walletService.makeWallet();
    }
}

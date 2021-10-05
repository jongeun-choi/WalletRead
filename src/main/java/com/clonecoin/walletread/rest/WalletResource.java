package com.clonecoin.walletread.rest;

import com.clonecoin.walletread.domain.event.LeaderCoinDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor.AllLeaderContent;
import com.clonecoin.walletread.service.LeaderInformation;
import com.clonecoin.walletread.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/walletread")
@RequiredArgsConstructor
public class WalletResource {

    private final LeaderInformation leaderInformation;
    private final WalletService walletService;

    // 모든 리더의 정보 가져오기
    @GetMapping("/leaders")
    public ResponseEntity getLeader() {
        return ResponseEntity.ok().body(leaderInformation.getAllLeader());
    }

    // 특정 리더의 기간별 수익률 가져오기
    @GetMapping("/leader")
    public ResponseEntity getLeaderPeriod(@RequestParam(value = "leaderId") Long leaderId,
                                           @RequestParam(value = "period") Long period) {
        return ResponseEntity.ok().body(leaderInformation.getLeaderPeriod(leaderId, period));
    }

    // 특정 리더의 코인 정보 가져오기
    @GetMapping("/leaderCoin")
    public ResponseEntity getLeaderCoin(@RequestParam(value = "leaderId") Long leaderId) {
        return ResponseEntity.ok().body(leaderInformation.getLeaderCoin(leaderId));
    }
}

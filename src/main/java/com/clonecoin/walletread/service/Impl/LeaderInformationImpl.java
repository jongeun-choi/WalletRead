package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Coin;
import com.clonecoin.walletread.domain.Profit;
import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.domain.event.LeaderCoinDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor.AllLeaderContent;
import com.clonecoin.walletread.domain.event.dtofactor.LeaderPeriodDtoFactor.LeaderPeriodContent;
import com.clonecoin.walletread.repository.CoinRepository;
import com.clonecoin.walletread.repository.WalletRepository;
import com.clonecoin.walletread.service.LeaderInformation;
import com.clonecoin.walletread.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderInformationImpl implements LeaderInformation {
    public final WalletRepository walletRepository;
    public final WalletService walletService;
    public final CoinRepository coinRepository;

    // 모든 리더에 대한 all, best, worst 정보 제공
    public AllLeaderDTO getAllLeader() { 
        AllLeaderDTO allLeaderDTO = new AllLeaderDTO();

        List<Wallet> walletList = walletRepository.findAll();
        walletList.stream().forEach(wallet -> {
            AllLeaderContent allLeaderContent = new AllLeaderContent();
            allLeaderContent.setUserId(wallet.getUserId()); // leader 아이디
            allLeaderContent.setUserName(wallet.getUserName()); // leader 이름

            Double all=wallet.getProfits().stream().mapToDouble(Profit::getProfit).sum()/wallet.getProfits().size(); // 수익률의 평균
            allLeaderContent.setAll(all);

            Double best=all;
            Double worst=all;

            for (Profit profit : wallet.getProfits()) {
                best = Math.max(best, profit.getProfit());
                worst = Math.min(worst, profit.getProfit());
            }

            allLeaderContent.setBest(best);
            allLeaderContent.setWorst(worst);

            allLeaderDTO.addContent(allLeaderContent);
        });
        allLeaderDTO.getAllLeaderContentList().stream().forEach(allLeaderContent -> System.out.println(allLeaderContent.toString()));
        return allLeaderDTO;
    }

    // Analysis 서버로부터 들어오는 리더의 코인 변경 정보를 update 해준다.
    @Transactional
    public void updateCoin(AnalysisDTO analysisDTO) {
        Wallet wallet = walletService.findWallet(analysisDTO.getUserId());
        List<Coin> coinList = wallet.getCoins();

        analysisDTO.getAfter().getCoins().stream().forEach(AnalysisCoin->{

            Optional<Coin> findCoin = coinList.stream()
                    .filter(coin -> coin.getCoinName().equals(AnalysisCoin.getName()))
                    .findFirst();

            if (findCoin.isEmpty()) {
                wallet.createCoin(AnalysisCoin.getName(), AnalysisCoin.getAvgPrice(), AnalysisCoin.getCoinQuantity());
            }

            if (findCoin.isPresent()) {
                findCoin.get().setCoin(AnalysisCoin.getName(), AnalysisCoin.getAvgPrice(), AnalysisCoin.getCoinQuantity());
            }
        });

        LeaderCoinDTO leaderCoinDTO = new LeaderCoinDTO(wallet.getUserId(), wallet.getCoins());
        System.out.println("\nLeaderCoinDto 확인 ");
        leaderCoinDTO.getCoinList().stream().forEach(coin -> System.out.println(coin.toString()));
        System.out.println();

        // Websocket 으로 보내주는 로직




    }

    // 리더의 (1, 7, 30) 기간별 수익률 제공
    public LeaderPeriodDTO getLeaderPeriod(Long userId, Long period) {

        LeaderPeriodDTO leaderPeriodDTO = new LeaderPeriodDTO();
        leaderPeriodDTO.setUserId(userId);

        List<LeaderPeriodContent> leaderPeriodContentList = new ArrayList<>();

        Wallet wallet = walletService.findWallet(userId);

        wallet.getProfits().stream().forEach(profit -> {
            LeaderPeriodContent leaderPeriodContent = new LeaderPeriodContent();
            leaderPeriodContent.setProfit(profit.getProfit());
            leaderPeriodContent.setLocalDate(profit.getLocalDate());
            leaderPeriodContentList.add(leaderPeriodContent);
        });



        if (period == 1) {
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList);
        }
        if (period == 7) {
            List<LeaderPeriodContent> leaderPeriodContentList_7 = getLeaderPeriodContentList(leaderPeriodContentList, 7);
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList_7);
        }
        if (period == 30) {
            List<LeaderPeriodContent> leaderPeriodContentList_30 = getLeaderPeriodContentList(leaderPeriodContentList, 30);
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList_30);
        }

        return leaderPeriodDTO;
    }

    // 1일 기준 수익률 -> 원하는 기간별 수익률
    public List<LeaderPeriodContent> getLeaderPeriodContentList(List<LeaderPeriodContent> leaderPeriodContentList, long period) {
        List<LeaderPeriodContent> leaderPeriodContentList2 = new ArrayList<>();
        int count = 0;
        double sum = 0;
        for (LeaderPeriodContent leaderPeriodContent : leaderPeriodContentList) {
            sum += leaderPeriodContent.getProfit();
            count++;
            if (count == period) {
                LeaderPeriodContent leaderPeriodContent2 = new LeaderPeriodContent();
                leaderPeriodContent2.setProfit(sum / 7);
                leaderPeriodContent2.setLocalDate(leaderPeriodContent.getLocalDate());
                leaderPeriodContentList2.add(leaderPeriodContent2);

                sum=0;
                count=0;
            }
        }
        if (count != 0) {
            LeaderPeriodContent leaderPeriodContent2 = new LeaderPeriodContent();
            leaderPeriodContent2.setProfit(sum / count);
            leaderPeriodContent2.setLocalDate(LocalDate.now());
            leaderPeriodContentList2.add(leaderPeriodContent2);
        }

       leaderPeriodContentList2.stream().forEach(leaderPeriodContent -> System.out.println(leaderPeriodContent.toString()));
        System.out.println("\n\n");
        return leaderPeriodContentList2;
    }

}

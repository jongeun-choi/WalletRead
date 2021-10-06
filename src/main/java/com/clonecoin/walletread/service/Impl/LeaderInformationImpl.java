package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Coin;
import com.clonecoin.walletread.domain.Profit;
import com.clonecoin.walletread.domain.Wallet;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderInformationImpl implements LeaderInformation {
    private final WalletRepository walletRepository;
    private final WalletService walletService;
    private final CoinRepository coinRepository;
    private final SimpMessagingTemplate template;

    // 모든 리더에 대한 all, best, worst 정보 제공
    public List<AllLeaderContent> getAllLeader() {
        List<AllLeaderContent> allLeaderContentList = new ArrayList<>();

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

            allLeaderContentList.add(allLeaderContent);
        });
        allLeaderContentList.stream().forEach(allLeaderContent -> System.out.println(allLeaderContent.toString()));
        return allLeaderContentList;
    }

    // Analysis 서버로부터 들어오는 리더의 코인 변경 정보를 update 해준다.
    @Transactional
    public void updateCoin(AnalysisDTO analysisDTO) {
        Wallet wallet = walletService.findWallet(analysisDTO.getUserId());
        wallet.setBalance(analysisDTO.getAfter().getTotalKRW());
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

        LeaderCoinDTO leaderCoinDTO = new LeaderCoinDTO(wallet.getUserId(), wallet.getUserName(),analysisDTO.getAfter().getTotalKRW(), wallet.getCoins());
        System.out.println("\nLeaderCoinDto 확인 ");
        leaderCoinDTO.getCoinList().stream().forEach(coin -> System.out.println(coin.toString()));
        System.out.println();

        // Websocket 으로 보내주는 로직
        template.convertAndSend("/topic/group/" + analysisDTO.getUserId(), leaderCoinDTO);
    }

    // 리더의 코인별 정보와 잔액을 제공
    public LeaderCoinDTO getLeaderCoin(Long userId){
        LeaderCoinDTO leaderCoinDTO = new LeaderCoinDTO();
        leaderCoinDTO.setUserId(userId);
        Wallet wallet = walletService.findWallet(userId);
        leaderCoinDTO.setUserName(wallet.getUserName());
        leaderCoinDTO.setBalance(wallet.getBalance());
        leaderCoinDTO.setCoinList(wallet.getCoins());
        leaderCoinDTO.getCoinList().stream().forEach(coin -> System.out.println(coin.toString()));
        return leaderCoinDTO;
    }


    // 리더의 (1일, 7일, 30일) 기준, 기간별 수익률 제공
    public LeaderPeriodDTO getLeaderPeriod(Long userId, Long period) {

        Wallet wallet = walletService.findWallet(userId);

        LeaderPeriodDTO leaderPeriodDTO = new LeaderPeriodDTO();
        leaderPeriodDTO.setUserId(userId);
        leaderPeriodDTO.setUserName(wallet.getUserName());

        List<LeaderPeriodContent> leaderPeriodContentList = new ArrayList<>(); // 리더의 1일 수익률을 list 에 저장
        wallet.getProfits().stream().forEach(profit -> {
            LeaderPeriodContent leaderPeriodContent = new LeaderPeriodContent();
            leaderPeriodContent.setProfit(profit.getProfit());
            leaderPeriodContent.setLocalDate(profit.getLocalDate());
            leaderPeriodContentList.add(leaderPeriodContent);
        });

        if (period == 1) { // 1일 기준 수익률을 요청했을 시
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList);
        }
        if (period == 7) { // 7일 기준 수익률을 요청했을 시
            List<LeaderPeriodContent> leaderPeriodContentList_7 = getLeaderPeriodContentList(leaderPeriodContentList, 7);
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList_7);
        }
        if (period == 30) { // 30일 기준 수익률을 요쳥했을 시
            List<LeaderPeriodContent> leaderPeriodContentList_30 = getLeaderPeriodContentList(leaderPeriodContentList, 30);
            leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList_30);
        }

        return leaderPeriodDTO;
    }

    // 1일 기준 수익률 -> 원하는 기간별 수익률로 변환
    public List<LeaderPeriodContent> getLeaderPeriodContentList(List<LeaderPeriodContent> leaderPeriodContentList, int period) {
        List<LeaderPeriodContent> leaderPeriodContentList2 = new ArrayList<>();
        int count = 0;
        double sum = 0;
        for (LeaderPeriodContent leaderPeriodContent : leaderPeriodContentList) {
            sum += leaderPeriodContent.getProfit();
            count++;
            if (count == period) {
                LeaderPeriodContent leaderPeriodContent2 = new LeaderPeriodContent();
                leaderPeriodContent2.setProfit(sum / period);
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

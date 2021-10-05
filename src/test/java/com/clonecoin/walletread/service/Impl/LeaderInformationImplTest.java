package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Coin;
import com.clonecoin.walletread.domain.Profit;
import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.AllLeaderDTO;
import com.clonecoin.walletread.domain.event.AnalysisDTO;
import com.clonecoin.walletread.domain.event.LeaderCoinDTO;
import com.clonecoin.walletread.domain.event.LeaderPeriodDTO;
import com.clonecoin.walletread.domain.event.dtofactor.AllLeaderDtoFactor.AllLeaderContent;
import com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor.AnalysisAfter;
import com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor.AnalysisBefore;
import com.clonecoin.walletread.domain.event.dtofactor.AnalysisDtoFactor.AnalysisCoins;
import com.clonecoin.walletread.domain.event.dtofactor.LeaderPeriodDtoFactor.LeaderPeriodContent;
import com.clonecoin.walletread.repository.CoinRepository;
import com.clonecoin.walletread.repository.ProfitRepository;
import com.clonecoin.walletread.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
@DataJpaTest // JPA관련 Component만 로드 된다. 테스트 종료 후 rollback이 되기 때문에 실제 repository에는 저장되지 않는다.
class LeaderInformationImplTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private ProfitRepository profitRepository;

    @Autowired
    private CoinRepository coinRepository;

    void makeWallet() {
        Wallet w1 = new Wallet();
        Wallet w2 = new Wallet();
        Wallet w3 = new Wallet();
        w1.createWallet(5L, "jong");
        w2.createWallet(10L, "eun");
        w3.createWallet(12L, "choi");

        w1.createCoin("BTC", 12, 5);
        w1.createCoin("ETH", 15, 8);

        List<LocalDate> l1 = new ArrayList<>();
        l1.add(LocalDate.parse("2021-09-01", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-02", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-03", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-04", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-05", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-06", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-07", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-08", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-09", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-10", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-11", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-12", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-13", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-14", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-15", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-16", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-17", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-18", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-19", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-20", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-21", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-22", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-23", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-24", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-25", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-26", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-27", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-28", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-29", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-09-30", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-01", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-02", DateTimeFormatter.ISO_DATE));

        List<LocalDate> l2 = new ArrayList<>();
        l2.add(LocalDate.parse("2021-10-01", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-02", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-03", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-04", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-05", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-06", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-07", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-08", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-09", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-10", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-11", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-12", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-13", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-14", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-15", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-16", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-17", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-18", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-19", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-20", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-21", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-22", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-23", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-24", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-25", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-26", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-27", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-28", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-29", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-30", DateTimeFormatter.ISO_DATE));
        l2.add(LocalDate.parse("2021-10-31", DateTimeFormatter.ISO_DATE));

        for (int i = 0; i < l1.size(); i++) {
            profitRepository.save(w1.updateDayProfit(i+1, 1000 * i, l1.get(i)));
        }

        /*
        profitRepository.save(w1.updateDayProfit(111, 1000, localDate1));
        profitRepository.save(w1.updateDayProfit(111.111, 1000, localDate2));
        profitRepository.save(w1.updateDayProfit(111.11122, 1000, localDate3));

        profitRepository.save( w2.updateDayProfit(222, 2000, localDate1));

        profitRepository.save(w3.updateDayProfit(222, 3000, localDate1));
        profitRepository.save( w3.updateDayProfit(2223, 3000, localDate2));
        profitRepository.save(w3.updateDayProfit(22233, 3000, localDate2));
        profitRepository.save(w3.updateDayProfit(222.33, 3000, localDate4));

         */

        walletRepository.save(w1);
       // walletRepository.save(w2);
        //walletRepository.save(w3);
    }

    @Test
    void bringAllLeader() {
        
        AllLeaderDTO allLeaderDTO = new AllLeaderDTO();

        makeWallet();

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
            System.out.println(allLeaderContent.toString());
        });

        allLeaderDTO.getAllLeaderContentList().stream().forEach(allLeaderContent -> System.out.println(allLeaderContent.toString()));

    }

    @Test
    void getLeaderPeriod() {

        makeWallet();

        LeaderPeriodDTO leaderPeriodDTO = new LeaderPeriodDTO();
        leaderPeriodDTO.setUserId(5L);

        List<LeaderPeriodContent> leaderPeriodContentList = new ArrayList<>();
        Wallet wallet = walletRepository.findByUserId(5L).get();

        wallet.getProfits().stream().forEach(profit -> {
            LeaderPeriodContent leaderPeriodContent = new LeaderPeriodContent();
            leaderPeriodContent.setProfit(profit.getProfit());
            leaderPeriodContent.setLocalDate(profit.getLocalDate());
            leaderPeriodContentList.add(leaderPeriodContent);
        });
        
        // 1일 기준
        leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList);
        System.out.println(leaderPeriodDTO.getUserId());
        leaderPeriodDTO.getLeaderPeriodContentList().stream().forEach(leaderPeriodContent -> System.out.println(leaderPeriodContent.toString()));

        System.out.println("\n\n");


        // 7일 기준
        List<LeaderPeriodContent> leaderPeriodContentList2 = new ArrayList<>();
        int count = 0;
        double sum = 0;
        for (LeaderPeriodContent leaderPeriodContent : leaderPeriodDTO.getLeaderPeriodContentList()) {
            sum += leaderPeriodContent.getProfit();
            count++;
            if (count == 7) {
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
        leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList2);
        System.out.println(leaderPeriodDTO.getUserId());
        leaderPeriodDTO.getLeaderPeriodContentList().stream().forEach(leaderPeriodContent -> System.out.println(leaderPeriodContent.toString()));

        System.out.println("\n\n");
        
        // 30일 기준
        List<LeaderPeriodContent> leaderPeriodContentList3 = new ArrayList<>();
        int count2 = 0;
        double sum2 = 0;
        for (LeaderPeriodContent leaderPeriodContent : leaderPeriodDTO.getLeaderPeriodContentList()) {
            sum2 += leaderPeriodContent.getProfit();
            count2++;
            if (count2 == 30) {
                LeaderPeriodContent leaderPeriodContent2 = new LeaderPeriodContent();
                leaderPeriodContent2.setProfit(sum2 / 30);
                leaderPeriodContent2.setLocalDate(leaderPeriodContent.getLocalDate());
                leaderPeriodContentList3.add(leaderPeriodContent2);

                sum2=0;
                count2=0;
            }
        }
        if (count2 != 0) {
            LeaderPeriodContent leaderPeriodContent2 = new LeaderPeriodContent();
            leaderPeriodContent2.setProfit(sum2 / count2);
            leaderPeriodContent2.setLocalDate(LocalDate.now());
            leaderPeriodContentList3.add(leaderPeriodContent2);
        }
        leaderPeriodDTO.setLeaderPeriodContentList(leaderPeriodContentList3);
        System.out.println(leaderPeriodDTO.getUserId());
        leaderPeriodDTO.getLeaderPeriodContentList().stream().forEach(leaderPeriodContent -> System.out.println(leaderPeriodContent.toString()));

        System.out.println("\n\n");
    }

    AnalysisDTO makeAnalysisDto() {
        AnalysisDTO analysisDTO = new AnalysisDTO();

        List<AnalysisCoins> analysisCoinsList = new ArrayList<>();
        AnalysisBefore analysisBefore = new AnalysisBefore();
        AnalysisAfter analysisAfter = new AnalysisAfter();

        AnalysisCoins analysisCoins1 = new AnalysisCoins("BTC", 5.3, 50000);
        AnalysisCoins analysisCoins2 = new AnalysisCoins("ETH", 2.1, 3000);
        analysisCoinsList.add(analysisCoins1);
        analysisCoinsList.add(analysisCoins2);

        analysisBefore.setCoins(analysisCoinsList);
        analysisBefore.setTotalKRW(502);
        analysisAfter.setCoins(analysisCoinsList);
        analysisAfter.setTotalKRW(56);

        analysisDTO.setUserId(5L);
        analysisDTO.setAfter(analysisAfter);
        analysisDTO.setBefore(analysisBefore);

        return analysisDTO;
    }

    @Test
    void updateCoin() {

        makeWallet();
        Wallet wallet = walletRepository.findByUserId(5L).get();
        AnalysisDTO analysisDTO = makeAnalysisDto();

        System.out.print("실행 전 : ");
        wallet.getCoins().stream().forEach(coin -> System.out.println(coin.toString()));
        
        analysisDTO.getAfter().getCoins().stream().forEach(AnalysisCoin->{

            List<Coin> coinList = wallet.getCoins();
            coinList.stream().forEach(coin -> {
                if (AnalysisCoin.getName().equals(coin.getCoinName())) {
                    coin.setCoin(AnalysisCoin.getName(), AnalysisCoin.getAvgPrice(), AnalysisCoin.getCoinQuantity());
                }
            });
        });
        System.out.print("실행 후 : ");
        wallet.getCoins().stream().forEach(coin -> System.out.println(coin.toString()));

        System.out.println("\n\nDTO 확인");
        LeaderCoinDTO leaderCoinDTO = new LeaderCoinDTO(wallet.getUserId(), wallet.getUserName(),wallet.getBalance(), wallet.getCoins());
        leaderCoinDTO.getCoinList().stream().forEach(coin -> System.out.println(coin.toString()));
    }
}
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
            profitRepository.save(w1.updateDayProfit(i + 1, 1000 * i, l1.get(i)));
        }

        walletRepository.save(w1);
    }

    @Test
    void getAllLeader() {
        makeWallet();
        List<AllLeaderContent> allLeaderContentList = new ArrayList<>();
        List<Wallet> walletList = walletRepository.findAll();
        walletList.stream().forEach(wallet -> {
            AllLeaderContent allLeaderContent = new AllLeaderContent();
            allLeaderContent.setUserId(wallet.getUserId());
            allLeaderContent.setUserName(wallet.getUserName());

            Double all = wallet.getProfits().stream().mapToDouble(Profit::getProfit).sum() / wallet.getProfits().size();
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

        assertThat(allLeaderContentList.get(0).getAll(), is(16.5));
        assertThat(allLeaderContentList.get(0).getBest(), is(32.0));
        assertThat(allLeaderContentList.get(0).getWorst(), is(1.0));

    }

    @Test
    void UpdateCoin() {
        makeWallet();
        Wallet wallet = walletRepository.findByUserId(5L).get();

        Coin coin1 = new Coin();
        Coin coin2 = new Coin();
        Coin coin3 = new Coin();
        coin1.setCoin("BTC", 20, 20);
        coin2.setCoin("ETH", 30, 30);
        coin3.setCoin("KLAY", 40, 40);

        List<Coin> coinList = new ArrayList<>();
        coinList.add(coin1);
        coinList.add(coin2);
        coinList.add(coin3);

        coinList.stream().forEach(coin -> {
            Optional<Coin> findCoin = wallet.getCoins().stream()
                    .filter(filterCoin -> filterCoin.getCoinName().equals(coin.getCoinName()))
                    .findFirst();

            if (findCoin.isEmpty()) {
                wallet.createCoin(coin.getCoinName(), coin.getAvgPrice(), coin.getCoinQuantity());
            }

            if (findCoin.isPresent()) {
                findCoin.get().setCoin(coin.getCoinName(), coin.getAvgPrice(), coin.getCoinQuantity());
            }
        });

        wallet.getCoins().stream().forEach(coin -> {
            if (coin.getCoinName().equals("BTC")) {
                assertThat(coin.getAvgPrice(),is(20.0));
                assertThat(coin.getCoinQuantity(),is(20.0));
            }
            if (coin.getCoinName().equals("ETH")) {
                assertThat(coin.getAvgPrice(),is(30.0));
                assertThat(coin.getCoinQuantity(),is(30.0));
            }
            if (coin.getCoinName().equals("KLAY")) {
                assertThat(coin.getAvgPrice(),is(40.0));
                assertThat(coin.getCoinQuantity(),is(40.0));
            }
        });
    }

    @Test
    void getLeaderCoin() {
        makeWallet();
        Wallet wallet = walletRepository.findByUserId(5L).get();
        LeaderCoinDTO leaderCoinDTO = new LeaderCoinDTO();
        leaderCoinDTO.setUserId(wallet.getUserId());
        leaderCoinDTO.setUserName(wallet.getUserName());
        leaderCoinDTO.setBalance(wallet.getBalance());
        leaderCoinDTO.setCoinList(wallet.getCoins());


        leaderCoinDTO.getCoinList().stream().forEach(coin -> {
            if (coin.getCoinName().equals("BTC")) {
                assertThat(coin.getAvgPrice(),is(12.0));
                assertThat(coin.getCoinQuantity(),is(5.0));
            }
            if (coin.getCoinName().equals("ETH")) {
                assertThat(coin.getAvgPrice(), is(15.0));
                assertThat(coin.getCoinQuantity(), is(8.0));
            }
        });
    }

    @Test
    void getLeaderPeriod() {
        makeWallet();
        Wallet wallet = walletRepository.findByUserId(5L).get();
        LeaderPeriodDTO leaderPeriodDTO = new LeaderPeriodDTO();
        leaderPeriodDTO.setUserId(wallet.getUserId());
        leaderPeriodDTO.setUserName(wallet.getUserName());

        List<LeaderPeriodContent> leaderPeriodContentList = new ArrayList<>(); // 리더의 1일 수익률을 list 에 저장
        wallet.getProfits().stream().forEach(profit -> {
            LeaderPeriodContent leaderPeriodContent = new LeaderPeriodContent();
            leaderPeriodContent.setProfit(profit.getProfit());
            leaderPeriodContent.setLocalDate(profit.getLocalDate());
            leaderPeriodContentList.add(leaderPeriodContent);
        });

        // period == 7
        List<LeaderPeriodContent> leaderPeriodContents_7 = getLeaderPeriodContentList(leaderPeriodContentList, 7);
        assertThat(leaderPeriodContents_7.size(),is(5));

        // period == 30
        List<LeaderPeriodContent> leaderPeriodContents_30 = getLeaderPeriodContentList(leaderPeriodContentList, 30);
        assertThat(leaderPeriodContents_30.size(),is(2));
    }

    // 1일 기준 수익률 -> 원하는 기간별 수익률로 변환
    List<LeaderPeriodContent> getLeaderPeriodContentList(List<LeaderPeriodContent> leaderPeriodContentList, int period) {
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
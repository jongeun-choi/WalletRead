package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Profit;
import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.WalletDTO;
import com.clonecoin.walletread.repository.ProfitRepository;
import com.clonecoin.walletread.repository.WalletRepository;
import com.clonecoin.walletread.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    public final WalletRepository walletRepository;
    public final ProfitRepository profitRepository;

    public void makeWallet() {
        Wallet w1 = new Wallet();
        Wallet w2 = new Wallet();
        Wallet w3 = new Wallet();
        w1.createWallet(5L, "jong");
        w2.createWallet(10L, "eun");
        w3.createWallet(12L, "choi");

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
        l1.add(LocalDate.parse("2021-10-03", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-04", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-05", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-06", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-07", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-08", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-09", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-10", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-11", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-12", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-13", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-14", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-15", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-16", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-17", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-18", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-19", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-20", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-21", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-22", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-23", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-24", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-25", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-26", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-27", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-28", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-29", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-30", DateTimeFormatter.ISO_DATE));
        l1.add(LocalDate.parse("2021-10-31", DateTimeFormatter.ISO_DATE));

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

    // Wallet 생성
    public void createWallet(WalletDTO walletDTO){
        Optional<Wallet> result = walletRepository.findByUserId(walletDTO.getUserId());
        if (result.isPresent()) {
            // 이미 존재하는 wallet
            System.out.println("\n\n 이미 존재하는 wallet 입니다. : "+walletDTO.getUserId());
            return;
        }

        Wallet wallet = new Wallet();
        wallet.createWallet(walletDTO.getUserId(), walletDTO.getUserName());

        walletRepository.save(wallet);
        System.out.println("\n"+wallet.toString());
    }

    public Wallet findWallet(Long userId) {
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if (wallet.isEmpty()) {
            // 비어 있는 경우 예외처리
            System.out.println("\n\n 존재하지 않는 wallet");
            throw new NoSuchElementException();
        }

        // 존재할경우 해당 Wallet 리턴
        System.out.println("\n\n 존재하는 wallet 발견 : "+ wallet.get().toString());
        return wallet.get();
    }

    // 리더별 일일 수익률 저장
    public void updateDayProfit(WalletDTO walletDTO) {
        System.out.println("\n\n"+walletDTO.toString());
        System.out.println(walletDTO.getProfitDto().toString());
        LocalDate date = LocalDate.parse(walletDTO.getProfitDto().getLocalDate(), DateTimeFormatter.ISO_DATE);

        Wallet wallet = findWallet(walletDTO.getUserId());
        System.out.println("\n\n"+walletDTO.getProfitDto().getProfit()+" , "+walletDTO.getProfitDto().getInvestment()+" , "+date);
        Profit profit = wallet.updateDayProfit(walletDTO.getProfitDto().getProfit(), walletDTO.getProfitDto().getInvestment(), date);
        profitRepository.save(profit);
        System.out.println("\n 수익률 저장");

        findWallet(walletDTO.getUserId()).getProfits().stream().forEach(profit2 -> System.out.println(profit2.toString()));
    }

}

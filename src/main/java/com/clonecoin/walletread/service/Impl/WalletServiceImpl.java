package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Profit;
import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.WalletDTO;
import com.clonecoin.walletread.repository.ProfitRepository;
import com.clonecoin.walletread.repository.WalletRepository;
import com.clonecoin.walletread.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    // Wallet 생성
    @Transactional
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
    @Transactional
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

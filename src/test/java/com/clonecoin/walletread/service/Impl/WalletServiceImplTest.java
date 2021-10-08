package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.ProfitDTO;
import com.clonecoin.walletread.domain.event.WalletDTO;
import com.clonecoin.walletread.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RequiredArgsConstructor
@DataJpaTest
class WalletServiceImplTest {

    @Autowired
    private WalletRepository walletRepository;

    WalletDTO makeWalletDTO() {
        ProfitDTO profitDTO =new ProfitDTO(12.34, 5000, "2021-10-01");
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setUserId(1L);
        walletDTO.setUserName("Jong");
        walletDTO.setProfitDto(profitDTO);
        return walletDTO;
    }

    @Test
    void createWallet() {
        WalletDTO walletDTO = makeWalletDTO();

        Wallet wallet = new Wallet();
        wallet.createWallet(walletDTO.getUserId(), walletDTO.getUserName());
        walletRepository.save(wallet);

        Optional<Wallet> res = walletRepository.findByUserId(wallet.getUserId());
        assertThat(res.isPresent(),is(equalTo(true)));
    }

    @Test
    void findWallet() {
        WalletDTO walletDTO = makeWalletDTO();

        Wallet wallet = new Wallet();
        wallet.createWallet(walletDTO.getUserId(), walletDTO.getUserName());
        walletRepository.save(wallet);

        Optional<Wallet> res = walletRepository.findByUserId(wallet.getUserId());
        assertThat(res.get().getUserId(),is(equalTo(wallet.getUserId())));
    }

    @Test
    void updateDayProfit() {


        WalletDTO walletDTO = makeWalletDTO();
        LocalDate date = LocalDate.parse(walletDTO.getProfitDto().getLocalDate(), DateTimeFormatter.ISO_DATE);

        Wallet wallet = new Wallet();
        wallet.createWallet(walletDTO.getUserId(), walletDTO.getUserName());
        walletRepository.save(wallet);

        Optional<Wallet> res = walletRepository.findByUserId(wallet.getUserId());
        if (res.isEmpty()) {
            System.out.println("\n 비었음");
        }

        res.get().updateDayProfit(walletDTO.getProfitDto().getProfit(), walletDTO.getProfitDto().getInvestment(), date);
        System.out.println(res.get().toString());

        res.get().getProfits().stream().forEach(profit -> System.out.println(profit.toString()));
        assertThat(res.get().getProfits().get(0).getProfit(), is(equalTo(12.34)));
        assertThat(res.get().getProfits().get(0).getLocalDate(), is(equalTo(date)));
    }
}
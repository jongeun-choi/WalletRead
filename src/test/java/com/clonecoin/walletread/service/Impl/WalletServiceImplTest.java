package com.clonecoin.walletread.service.Impl;

import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.ProfitDTO;
import com.clonecoin.walletread.domain.event.WalletDTO;
import com.clonecoin.walletread.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
@DataJpaTest // JPA관련 Component만 로드 된다. 테스트 종료 후 rollback이 되기 때문에 실제 repository에는 저장되지 않는다.
class WalletServiceImplTest {

    @Autowired
    private WalletRepository walletRepository;

    @Test
    void updateDayProfit() {


        ProfitDTO profitDTO =new ProfitDTO(12.34, 5000, "2021-10-01");
        LocalDate date = LocalDate.parse(profitDTO.getLocalDate(), DateTimeFormatter.ISO_DATE);


        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setUserId(1L);
        walletDTO.setUserName("Jong");
        walletDTO.setProfitDto(profitDTO);

        Wallet wallet = new Wallet();
        wallet.createWallet(walletDTO.getUserId(), walletDTO.getUserName());
        walletRepository.save(wallet);

        Optional<Wallet> res = walletRepository.findByUserId(wallet.getUserId());
        if (res.isEmpty()) {
            System.out.println("\n 비었음");
        }

        res.get().updateDayProfit(walletDTO.getProfitDto().getProfit(), walletDTO.getProfitDto().getInvestment(), date);
        res.get().updateDayProfit(12.3, 5000, date);
        System.out.println(res.get().toString());



        res.get().getProfits().stream().forEach(profit -> System.out.println(profit.toString()));
    }
}
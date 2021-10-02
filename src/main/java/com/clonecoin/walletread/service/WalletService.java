package com.clonecoin.walletread.service;

import com.clonecoin.walletread.domain.Wallet;
import com.clonecoin.walletread.domain.event.WalletDTO;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

    void createWallet(WalletDTO walletDTO);

    Wallet findWallet(Long userId);

    void updateDayProfit(WalletDTO walletDTO);

    //test용
    void makeWallet();

}

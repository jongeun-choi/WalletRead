package com.clonecoin.walletread.repository;

import com.clonecoin.walletread.domain.Coin;
import com.clonecoin.walletread.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin,Long> {

    Optional<Coin> findByWalletAndCoinName(Wallet wallet, String coinName);

}

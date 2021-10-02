package com.clonecoin.walletread.repository;

import com.clonecoin.walletread.domain.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository extends JpaRepository<Profit,Long> {
}

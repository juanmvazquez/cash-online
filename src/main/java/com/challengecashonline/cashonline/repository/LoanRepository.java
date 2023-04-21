package com.challengecashonline.cashonline.repository;

import com.challengecashonline.cashonline.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}

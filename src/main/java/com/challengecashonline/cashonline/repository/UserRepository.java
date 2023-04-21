package com.challengecashonline.cashonline.repository;

import com.challengecashonline.cashonline.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Page<User> findByFirstNameIgnoreCase(Pageable pageable, String firstName);

}

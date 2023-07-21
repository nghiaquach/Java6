package com.j6d7.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6d7.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
}

package com.anchil.atmapplication.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchil.atmapplication.api.Model.Account;

public interface AccountRepository extends JpaRepository<Account,String>{

}

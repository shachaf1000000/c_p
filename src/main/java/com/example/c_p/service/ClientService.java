package com.example.c_p.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.c_p.exception.CustomException;
import com.example.c_p.repository.CompanyRepository;
import com.example.c_p.repository.CouponRepository;
import com.example.c_p.repository.CustomerRepository;
@Service
public abstract class ClientService {
	 @Autowired
	 protected CompanyRepository companiesRepo;
	 @Autowired
	 protected CustomerRepository customersRepo;
	 @Autowired
	 protected CouponRepository couponsRepo;
	 public abstract boolean login(String email,String password) throws CustomException ;
}

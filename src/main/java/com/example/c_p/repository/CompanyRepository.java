package com.example.c_p.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.c_p.beans.Company;
import com.example.c_p.beans.Coupon;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
	public Company findByEmailAndPassword(String email,String password);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Company c WHERE c.name = ?1 Or c.email = ?2")
	public Boolean existsByNameOrEmail(String name, String email);

	public Company findById(int id);

	public Boolean existsById(Company company);

	@Query("select c from Company c where c.email = ?1 and c.id <> ?2")
	public Company findCompanyByEmailAndId(String email, int id);

	@Query("select c from Coupon c where companyId = ?1")
	public List<Coupon> findCompanyCoupons(int id);
	public Company findByName(String name);
}

package com.example.c_p.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.c_p.beans.Coupon;
import com.example.c_p.beans.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select id from Customer where email = ?1 and password =?2")
	public Customer findByEmailAndPassword(String email,String password);

	public boolean existsByEmail(String email);

	public Customer findById(int id);

	@Query("select c from Customer c where c.email = ?1 and c.id <> ?2")
	public Customer findCustomerByEmailAndId(String email , int id);

	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id = ?1 and coup.id = ?2)")
	public Coupon findCustomerCoupon(int custId, int cpnId);
	
	@Query("SELECT coup FROM Coupon coup WHERE coup.id Not IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1) and coup.amount > 0 and coup.endDate > CURDATE() and coup.company.getId() > 0")
	public List<Coupon> findCoupon(int cust_id);
}

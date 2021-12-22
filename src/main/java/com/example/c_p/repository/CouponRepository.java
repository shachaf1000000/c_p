package com.example.c_p.repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.c_p.beans.Category;
import com.example.c_p.beans.Coupon;
import com.example.c_p.beans.Customer;



public interface CouponRepository extends JpaRepository<Coupon,Integer>{
	public List<Coupon> findById(int id);
	@Query("SELECT c FROM Customer c WHERE c.id IN (SELECT c.id FROM c.coupons cust WHERE cust.id = ?1)")
	public List<Coupon> getAllCouponsCustomer(int coupon_id);

	public List<Coupon> findByCategoryAndCompanyId(Category category, int companyId);

	@Query("select e from Coupon e where e.price <= ?1 and companyId = ?2")
	public List<Coupon> findByMaxPrice(double price, int companyId);

	public Boolean existsByTitleAndCompanyId(String title, int companyId);
	
	@Query("select e.id from Coupon e where e.title=?1 and e.id=?2")
	public Integer existsByTitleAndId(String title , int couponId);

	@Query("select e from Coupon e join e.customers c where c.id =?1 and e.category = ?2")
	public List<Coupon> findCategoryCustomer(int cust_id, Category category);

	@Query("select e from Coupon e join e.customers c where c.id =?1 and e.price <= ?2")
	public List<Coupon> findMaxPriceCustomer(int cust_id, double maxPrice);

	public List <Coupon> findByEndDateBefore(Date endDate);
}

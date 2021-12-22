package com.example.c_p.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.c_p.service.CustomerService;

@Component
@Order(3)
public class CustomerTest implements CommandLineRunner {
	@Autowired
	private CustomerService customerService;
	@Override
	public void run(String... args) throws Exception {
		testLoginCustomer();
		testGetAllCoupons();
		testGetCustomerCouponsByCategory();
		testGetCustomerCouponsByCustomerId();
		testGetCustomerCouponsByMaxPrice();
		testGetCustomerDetails();
		testPurchaseCoupon();
		
	}
	public void testLoginCustomer() {
		try {
			customerService.login(null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetAllCoupons() {
		try {
			customerService.getAllCoupons();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCustomerCouponsByCategory() {
		try {
			customerService.getCustomerCouponsByCategory(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCustomerCouponsByCustomerId() {
		try {
			customerService.getCustomerCouponsByCustomerId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCustomerCouponsByMaxPrice() {
		try {
			customerService.getCustomerCouponsByMaxPrice(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCustomerDetails() {
		try {
			customerService.getCustomerDetails();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testPurchaseCoupon() {
		try {
			customerService.purchaseCoupon(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

package com.example.c_p.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.c_p.service.CompanyService;

@Component
@Order(2)
public class CompanyTest implements CommandLineRunner {
	@Autowired
	private CompanyService companyService;
	@Override
	public void run(String... args) throws Exception {
		testLoginCompany();
		testAddCoupon();
		testDeleteCoupon();
		testGetCompanyCouponsByCategory();
		testGetCompanyCouponsByCompanyId();
		testGetCompanyCouponsByMaxPrice();
		testGetCompanyDetails();
		testGetCompanyId();
		testSetCompanyId();
		testUpdateCoupon();
		
	}
	public void testLoginCompany() {
		try {
			companyService.login(null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testAddCoupon() {
		try {
			companyService.addCoupon(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testDeleteCoupon() {
		try {
			companyService.deleteCoupon(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCompanyCouponsByCategory() {
		try {
			companyService.getCompanyCouponsByCategory(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCompanyCouponsByCompanyId() {
		try {
			companyService.getCompanyCouponsByCompanyId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCompanyCouponsByMaxPrice() {
		try {
			companyService.getCompanyCouponsByMaxPrice(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCompanyDetails() {
		try {
			companyService.getCompanyDetails();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetCompanyId() {
		try {
			companyService.getCompanyId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testSetCompanyId() {
		try {
			companyService.setCompanyId(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testUpdateCoupon() {
		try {
			companyService.updateCoupon(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

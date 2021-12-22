package com.example.c_p.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.c_p.beans.Company;
import com.example.c_p.service.AdminService;

@Component
@Order(1)
public class AdminTest implements CommandLineRunner{
	@Autowired
	private AdminService adminService;
	@Override
	public void run(String... args) throws Exception {
		testLoginAdmin();
		testAddCompany();
		testAddCustomer();
		testDeleteCompany();
		testDeleteCustomer();
		testGetAllCompanies();
		testGetAllCustomers();
		testGetOneCompany();
		testGetOneCustomer();
		testUpdateCompany();
		testUpdateCustomer();
		
		
	}
	public void testLoginAdmin() {
		//login should be working
		try {
			adminService.login("Admin@gmail.com", "Admin1234");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//login should be not working
		try {
			adminService.login("dmin@gmail.com", "dmin1234");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testAddCompany() {
		try {
			adminService.addCompany(new Company("Tesla", "Tesla@gmail.com", "Tesla1234"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testAddCustomer() {
		try {
			adminService.addCustomer(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testDeleteCompany() {
		try {
			adminService.deleteCompany(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testDeleteCustomer() {
		try {
			adminService.deleteCustomer(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetAllCompanies() {
		try {
			adminService.getAllCompanies();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetAllCustomers() {
		try {
			adminService.getAllCustomers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetOneCompany() {
		try {
			adminService.getOneCompany(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testGetOneCustomer() {
		try {
			adminService.getOneCustomer(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testUpdateCompany() {
		try {
			adminService.updateCompany(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void testUpdateCustomer() {
		try {
			adminService.updateCustomer(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

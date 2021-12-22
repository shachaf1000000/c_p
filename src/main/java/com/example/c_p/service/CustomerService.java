package com.example.c_p.service;

import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.c_p.beans.Category;
import com.example.c_p.beans.Coupon;
import com.example.c_p.beans.Customer;
import com.example.c_p.exception.CustomException;
import com.example.c_p.repository.CouponRepository;
import com.example.c_p.repository.CustomerRepository;



@Service
@Transactional
public class CustomerService extends ClientService{
	private int customerId;

	public CustomerService() {

	}

	
	@Override
	public boolean login(String email, String password) throws CustomException {
		if(email != null && password != null) {	
			Customer id = customersRepo.findByEmailAndPassword(email,password);
			if(id != null) {
				customerId = id.getId();
			}

			if(customerId > 0 && id != null) {
				System.out.println("Welcome Customer!");
				return true;
			}
			else {
				throw new CustomException("Invalid email or password");
			}		
		} else {
			throw new CustomException("Cannot login with empty details");
		}
	}
	
	public void purchaseCoupon(Coupon coupon) throws CustomException {
		if(coupon != null) {
			Coupon c = (Coupon) couponsRepo.findById(coupon.getId());
			System.out.println(c);
			if(c != null) {
				if (customersRepo.findCustomerCoupon(customerId, c.getId()) != null) {
					throw new CustomException("The same coupon cannot be purchased more than once.");	
				}
				else {
					if(c.getAmount() > 0) {					
						if(c.getEndDate() != null && c.getEndDate().getTime() <= Calendar.getInstance().getTime().getTime())
							throw new CustomException("This coupon cannot be purchased because it's expired.");
						else {
							Customer cus = customersRepo.findById(customerId);
							c.setAmount(c.getAmount() - 1);
							cus.addCoupon(c);
							customersRepo.saveAndFlush(cus);
						}
					}
					else {
						throw new CustomException("This coupon cannot be purchased because it's not in stock.");			
					}				
				}	
			}

			else {
				throw new CustomException("No coupon found with this specific Id.");
			}
		}
		else {
			throw new CustomException("Cannot purchase empty coupon.");
		}
	}

	public ArrayList<Coupon> getCustomerCouponsByCustomerId() throws CustomException {
		if(customersRepo.getById(customerId) == null || customersRepo.getById(customerId).getCoupons().isEmpty()) {
			throw new CustomException("No coupons has been found");	
		}
		return new ArrayList<Coupon>(customersRepo.findById(customerId).getCoupons());
	}

	public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) throws CustomException {
		if(couponsRepo.findCategoryCustomer(customerId,category).isEmpty()) {
			throw new CustomException("No coupons found with the selected category");	
		}
		return new ArrayList<Coupon>(couponsRepo.findCategoryCustomer(customerId,category));
	}

	public ArrayList<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws CustomException {
		if(maxPrice > 0) {
			if(couponsRepo.findMaxPriceCustomer(customerId, maxPrice).isEmpty()) {
				throw new CustomException("No coupons found with selected max price");	
			}
			return new ArrayList<Coupon>(couponsRepo.findMaxPriceCustomer(customerId, maxPrice));
		} else {
			throw new CustomException("The max price must be positive");
		}
	}

	public Customer getCustomerDetails() throws CustomException {
		Customer c = customersRepo.findById(customerId);
		if(c == null) {
			throw new CustomException("Customer doesn't exists");	
		}
		return c;
	}

	public ArrayList<Coupon> getAllCoupons() throws CustomException {
		if(customersRepo.findCoupon(customerId).isEmpty()) {
			throw new CustomException("No coupons found in the system");
		}
		return new ArrayList<Coupon>(customersRepo.findCoupon(customerId));
	}


}

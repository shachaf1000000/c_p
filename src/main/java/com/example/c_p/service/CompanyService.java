package com.example.c_p.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.c_p.beans.Category;
import com.example.c_p.beans.Company;
import com.example.c_p.beans.Coupon;
import com.example.c_p.exception.CustomException;

@Service
@Scope("prototype")
@Transactional
public class CompanyService extends ClientService {

	private int companyId;

	public CompanyService() {
		super();

	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public CompanyService(int companyId) {

		this.companyId = companyId;
	}

	@Override
	public boolean login(String email, String password) throws CustomException {
		if (email != null && password != null) {

			Company id = companiesRepo.findByEmailAndPassword(email, password);

			if (companyId > -1 && id != null) {
				System.out.println("Welcome Company!");
				companyId = id.getId();
				return true;
			} else {
				throw new CustomException("Invalid email or password");
			}
		} else {
			throw new CustomException("Cannot login with empty details");
		}
	}

	public void addCoupon(Coupon coupon) throws CustomException {
		
		if (coupon != null) {
			coupon.setCompany(getCompanyDetails());
			
			// Check if the date is after current time
			checkdate((Date) coupon.getStartDate());
			// Check if the start date before end date
			checkdate((Date) coupon.getEndDate());
			if (coupon.getEndDate() != null && coupon.getStartDate().after(coupon.getEndDate())) {
				throw new CustomException("Cannot add coupon with invalid date range");
			}
			
			if (!companiesRepo.existsById(coupon.getCompany().getId()) || companyId != coupon.getCompany().getId()) {
				throw new CustomException("Cannot add coupon because no company found with this specific Id");
			} else {
				if (couponsRepo.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
					throw new CustomException("The title is already exist in the company");
				} else {
					couponsRepo.save(coupon);
					System.out.println("the coupon with id " + coupon.getId() + "added susccsfully");
				}
			}
			
		} else {
			throw new CustomException("Cannot add empty Coupon");
		}
	}

	public void updateCoupon(Coupon coupon) throws CustomException {
		if (coupon != null) {
			coupon.setCompany(getCompanyDetails());
			// check if the date after current time
			checkdate((Date) coupon.getStartDate());
			// Check if the start date before end date
			checkdate((Date) coupon.getEndDate());
			if (coupon.getEndDate() != null && coupon.getStartDate().after(coupon.getEndDate())) {
				throw new CustomException("Cannot update coupon with invalid date range");
			}
			
			if (couponsRepo.findById(coupon.getId()) != null) {
				if (coupon.getCompany().getId() == companyId && coupon.getCompany().getId() >0) {
					// Cannot update title that already exists
					if (couponsRepo.existsByTitleAndId(coupon.getTitle(),coupon.getId()) != null) {
						throw new CustomException("the title is already exist in the company");
					}
					couponsRepo.saveAndFlush(coupon);
				} else {
					throw new CustomException("Coupon's companyId cannot be updated");
				}
			} else {
				throw new CustomException("The coupon does not exist in the system");
			}
		} else {
			throw new CustomException("Cannot update empty Coupon");
		}
	}

	public void deleteCoupon(int couponId) throws CustomException {
		if (couponId > 0) {
			Coupon c = (Coupon) couponsRepo.findById(couponId);
			if (c != null) {
				couponsRepo.delete(c);
			} else {
				throw new CustomException("The coupon does not exist in the system");
			}
		} else {
			throw new CustomException("Cannot delete coupon with invalid Id");
		}
	}

	public ArrayList<Coupon> getCompanyCouponsByCompanyId() throws CustomException {
		if (companiesRepo.findCompanyCoupons(companyId).isEmpty()) {
			throw new CustomException("No coupons exists for this company");
		}
		return new ArrayList<Coupon>(companiesRepo.findCompanyCoupons(companyId));
	}

	public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) throws CustomException {
		if (couponsRepo.findByCategoryAndCompanyId(category, companyId).isEmpty()) {
			throw new CustomException("No coupons found with selected category");
		}
		return new ArrayList<Coupon>(couponsRepo.findByCategoryAndCompanyId(category, companyId));
	}

	public ArrayList<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws CustomException {
		if (maxPrice > 0) {
			if (couponsRepo.findByMaxPrice(maxPrice, companyId).isEmpty()) {
				throw new CustomException("No coupons found with this price limit");
			}
			return new ArrayList<Coupon>(couponsRepo.findByMaxPrice(maxPrice, companyId));
		} else {
			throw new CustomException("The MaxPrice must be positive");
		}
	}

	public Company getCompanyDetails() throws CustomException {
		Company c = companiesRepo.findById(companyId);
		if (c == null) {
			throw new CustomException("Company does not exists with this id");
		}
		return c;
	}

	// Check if the date is after the current date
	private void checkdate(Date date) throws CustomException {
		if (date != null) {
			Date currentDate = Date.valueOf(LocalDate.now());

			if (date.before(currentDate)) {
				throw new CustomException("The date have to be after the current time");
			}
		} else {
			throw new CustomException("The date have to be valid");
		}
	}
}

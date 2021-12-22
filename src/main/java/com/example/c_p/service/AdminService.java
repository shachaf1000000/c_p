package com.example.c_p.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.c_p.beans.Company;
import com.example.c_p.beans.Customer;
import com.example.c_p.exception.CustomException;
import com.example.c_p.repository.CompanyRepository;
import com.example.c_p.repository.CouponRepository;
import com.example.c_p.repository.CustomerRepository;




@Service
@Transactional
public class AdminService extends ClientService{
	
	public AdminService() {
		super();
		
	}

	@Override
	public boolean login(String email, String password) throws CustomException{
		if(email != null && password != null) {
		if (email.equalsIgnoreCase("Admin@gmail.com")&&password.equals("Admin1234")) {
			System.out.println("Welcome Admin!");
			return true;
		}
		}
		throw new CustomException("Invalid email or password");
	}
		
	public void addCompany(Company company) throws CustomException {
		if(company != null) {
			if(!companiesRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
				companiesRepo.save(company);
				System.out.println("the company added sucssfully");
			}
			else {
				throw new CustomException("Company is already exists");
			}
		}
		else {
			throw new CustomException("Cannot add empty company");
		}		
	}
	
	public void updateCompany(Company company) throws CustomException {
		if(company != null) {		
			if(companiesRepo.findById(company.getId()) != null) {
				if(company.getName().equals(companiesRepo.findByName(company.getName()))) { 
					if(companiesRepo.findCompanyByEmailAndId(company.getEmail(), company.getId()) != null) {
						companiesRepo.saveAndFlush(company);
						
					}
					else {
						throw new CustomException("Company email could not be updated because the email is already exists");
					}
				}
				else {	
					throw new CustomException("The name doesn't exist in the system ");
				}
			}
			else {
				throw new CustomException("The id doesn't exist in the system ");
			}
		}
		else {
			throw new CustomException("Cannot update empty company");
		}
	}
	
	public void deleteCompany(int companyId) throws CustomException {
		if(companyId > 0) {		
			Company c =  companiesRepo.findById(companyId);
			if(c == null) {
				throw new CustomException("The company does not exist in the system");	
			}
			else {
				companiesRepo.delete(c);
			}
		}
		else {
			throw new CustomException("can't delete company with invalid Id");
		}
	}

	public ArrayList<Company> getAllCompanies() throws CustomException {
		if(companiesRepo.findAll().isEmpty()) {
			throw new CustomException("There are no companies in the system ");
		}
		return (ArrayList<Company>)companiesRepo.findAll();	
	}

	public Company getOneCompany(int companyId) throws CustomException {
		if(companyId > 0) {
			Company c = companiesRepo.findById(companyId);
			if(c == null) {
				throw new CustomException("No company found with this specific Id");	
			}
			return c;
		} 
		else {
			throw new CustomException("Cannot show company with invalid id ");	
		}
	}
	
	public void addCustomer(Customer customer) throws CustomException {
		if(customer != null) {
			if(!customersRepo.existsByEmail(customer.getEmail())) {
				customersRepo.save(customer);				
			}
			else {
				throw new CustomException("Cannot add this specific email - This email is already exists in the system");	
			}		
		} else {
			throw new CustomException("Cannot add empty customer");
		}
	}

	public void updateCustomer(Customer customer) throws CustomException {
		if(customer != null) {
			if(customersRepo.findById(customer.getId()) != null) {
				if(customersRepo.findCustomerByEmailAndId(customer.getEmail(),customer.getId()) != null) {
					throw new CustomException("Customer email or id is already exists in another customer");	
				}
				customersRepo.saveAndFlush(customer);
			}
			else {
				throw new CustomException("The Id does not exist in the system");	
			}
		} else {
			throw new CustomException("Cannot update empty Customer");
		}
	}

	public void deleteCustomer(int customerId) throws CustomException {
		if(customerId > 0) {
			Customer c = customersRepo.findById(customerId);
			if(c == null) {
				throw new CustomException("No customer found with this specific Id");	
			}
			else {
				c.removeCoupon(c);
				customersRepo.delete(c);
			}
		} else {
			throw new CustomException("cannot delete customer with invalid Id");
		}
	}

	public ArrayList<Customer> getAllCustomers() throws CustomException {
		if((customersRepo.findAll().isEmpty())) {
			throw new CustomException("There are no customers in the system");	
		}
		return (ArrayList<Customer>)customersRepo.findAll();
	}

	public Customer getOneCustomer(int customerId) throws CustomException {
		if(customerId > 0) {
			Customer c = customersRepo.findById(customerId);
			if(c == null) {
				throw new CustomException("No customer found with this specific Id");	
			}
			return c;
		} 
		else {
			throw new CustomException("Cannot show customer with invalid Id");
		}
	}
	
	
}

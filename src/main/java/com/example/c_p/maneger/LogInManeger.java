package com.example.c_p.maneger;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.c_p.exception.CustomException;
import com.example.c_p.service.AdminService;
import com.example.c_p.service.ClientService;
import com.example.c_p.service.CompanyService;
import com.example.c_p.service.CustomerService;



@Service
public class LogInManeger {
	private final ApplicationContext ctx;
	private final AdminService adminService;
	
	public LogInManeger(ApplicationContext ctx, AdminService adminService) {
		this.ctx = ctx;
		this.adminService = adminService;
	}
	
	public ClientService login(String email, String password, ClientType clientType) throws CustomException {
		ClientService clientService = null;
		switch (clientType) {
		case ADMINISTRATOR:
			clientService = (ClientService) adminService;
			break;
		case COMPANY:
			clientService = (ClientService) ctx.getBean(CompanyService.class);
			break;
		case CUSTOMER:
			clientService = (ClientService) ctx.getBean(CustomerService.class);
			break;
		}
		return clientService;
	}
}

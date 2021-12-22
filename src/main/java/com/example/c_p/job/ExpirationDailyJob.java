package com.example.c_p.job;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.c_p.beans.Coupon;
import com.example.c_p.repository.CouponRepository;

@Component

public class ExpirationDailyJob {
	@Autowired
	private CouponRepository couponsRepo;

	

	public ExpirationDailyJob() {
		super();
		
	}
	
//	@Override
//	public void run() {
//		while(!quit) {
//			for (Coupon cpn : couponsRepo.findByEndDateBefore(new Date(Calendar.getInstance().getTime().getTime()))) {
//				couponsRepo.delete(cpn);
//			}
//			try {
//				Thread.sleep(60000);
//			} catch (InterruptedException e) {
//				System.out.println(("Exception in ExpirationDailyJob - " + e.getMessage()));
//			}
//		}
//	}
	//
	@Scheduled(fixedRate = 86_400_000, initialDelay = 15_000 )
	public void removeExpierdCoupon() {
		List<Coupon>couponToDelete=couponsRepo.findByEndDateBefore(new Date(Calendar.getInstance().getTime().getTime()));
		try {
			for (Coupon cpn :couponToDelete ) {
				couponsRepo.delete(cpn);
			}
		} catch (Exception e) {
			System.out.println(("Exception in ExpirationDailyJob - " + e.getMessage()));
		}
	}
}



package com.example.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.dao.entity.Coupon;
import com.example.onlineShopping.model.CouponDto;
import com.example.onlineShopping.service.CouponService;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@PostMapping
	public Coupon createCoupon(@RequestBody CouponDto couponDto) {
		return couponService.createCoupon(couponDto);
	}

	@GetMapping
	public List<CouponDto> getAllCoupons() {
		return couponService.getAllCoupons();
	}
}

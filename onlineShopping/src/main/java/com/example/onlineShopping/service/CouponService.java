package com.example.onlineShopping.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineShopping.constant.ExceptionMessages;
import com.example.onlineShopping.dao.entity.Coupon;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.mapper.Mapper;
import com.example.onlineShopping.model.CouponDto;
import com.example.onlineShopping.repository.CouponRepository;

@Service
public class CouponService {
	@Autowired
	private Mapper mapper;

	@Autowired
	private CouponRepository couponRepository;

	public Coupon createCoupon(CouponDto couponDto) {
		Coupon coupon = mapper.couponDtoToEntity(couponDto);
		return couponRepository.save(coupon);
	}

	public Coupon validateCoupon(String code) {
		Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Coupon not found"));
		if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(ExceptionMessages.COUPON_IS_EXPIRED);
		}
		return coupon;
	}

	public List<CouponDto> getAllCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		List<CouponDto> couponDtos = new ArrayList<>();
		for (Coupon coupon : coupons) {
			CouponDto couponDto = mapper.couponEntityToDto(coupon);
			couponDtos.add(couponDto);
		}
		return couponDtos;
	}
}

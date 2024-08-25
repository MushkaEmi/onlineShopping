package com.example.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineShopping.dao.entity.Product;
import com.example.onlineShopping.model.ProductDto;
import com.example.onlineShopping.model.RatingDto;
import com.example.onlineShopping.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ProductDto getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public Product createProduct(@RequestBody ProductDto productDto) {
		return productService.createProduct(productDto);
	}

	@PutMapping("/{id}")
	public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
		return productService.updateProduct(id, productDto);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/price-range")
	public List<ProductDto> getProductsByPriceRange(@RequestParam("minPrice") double minPrice,
			@RequestParam("maxPrice") double maxPrice) {
		return productService.getProductsByPriceRange(minPrice, maxPrice);
	}

	@PostMapping("/{productId}/ratings")
	public RatingDto addRatingToProduct(@PathVariable("productId") Long productId, @RequestBody RatingDto ratingDto) {
		return productService.addRatingToProduct(productId, ratingDto.getUserId(), ratingDto.getRating(), ratingDto.getComment());
	}

	@GetMapping("/{productId}/ratings")
	public List<RatingDto> getRatingsByProductId(@PathVariable("productId") Long productId) {
		return productService.getRatingsByProductId(productId);
	}
}

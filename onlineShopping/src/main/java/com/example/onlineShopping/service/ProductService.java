package com.example.onlineShopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineShopping.constant.ExceptionMessages;
import com.example.onlineShopping.dao.entity.Product;
import com.example.onlineShopping.dao.entity.Rating;
import com.example.onlineShopping.dao.entity.User;
import com.example.onlineShopping.exceptions.NotFoundException;
import com.example.onlineShopping.mapper.Mapper;
import com.example.onlineShopping.model.ProductDto;
import com.example.onlineShopping.model.RatingDto;
import com.example.onlineShopping.repository.ProductRepository;
import com.example.onlineShopping.repository.RatingRepository;
import com.example.onlineShopping.repository.UserRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RatingRepository ratingRepository;

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = mapper.productEntityListToDtoList(products);
		return productDtos;
	}

	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionMessages.PRODUCT_NOT_FOUND));
		return mapper.productEntityToDto(product);

	}

	public Product createProduct(ProductDto productDto) {
		Product product = mapper.productDtoToEntity(productDto);
		productRepository.save(product);
		return product;
	}

	public ProductDto updateProduct(Long id, ProductDto productDto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setPrice(productDto.getPrice());
		Product updatedProduct = productRepository.save(product);
		return mapper.productEntityToDto(updatedProduct);
	}

	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
		productRepository.delete(product);
	}

	public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
		List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
		return mapper.productEntityListToDtoList(products);
	}

	public RatingDto addRatingToProduct(Long productId, Long userId, Double value, String comment) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessages.PRODUCT_NOT_FOUND));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessages.USER_NOT_FOUND));
		Rating rating = new Rating();
		rating.setRating(value);
		rating.setComment(comment);
		rating.setProduct(product);
		rating.setUser(user);

		Rating savedRating = ratingRepository.save(rating);

		updateProductRating(product);
		return mapper.ratingEntityToDto(savedRating);

	}

	private void updateProductRating(Product product) {
		List<Rating> ratings = product.getRatings();
		double sum = 0.0;
		int count = 0;

		for (Rating rating : ratings) {
			sum += rating.getRating();
			count++;
		}

		double averageRating = (count == 0) ? 0.0 : sum / count;
		product.setRatingAverage(averageRating);
		productRepository.save(product);
	}
	 public List<RatingDto> getRatingsByProductId(Long productId) {
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new NotFoundException(ExceptionMessages.PRODUCT_NOT_FOUND));
	        return mapper.ratingEntityListToDto(product.getRatings());
	    }
}

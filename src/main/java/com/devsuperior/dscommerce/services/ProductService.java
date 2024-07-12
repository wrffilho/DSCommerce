package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		Optional<Product> optProduct = productRepository.findById(id);
		Product product = optProduct.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		ProductDTO productDTO = new ProductDTO(product);

		return productDTO;
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {

		Page<Product> result = productRepository.findAll(pageable);

		Page<ProductDTO> products = result.map(ProductDTO::new);

		return products;
	}

	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		Product entity = new Product();
		copyDtoToEntity(productDTO, entity);
		Product result = productRepository.save(entity);
		return new ProductDTO(result);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO productDTO) {
		Product entity = productRepository.getReferenceById(id);
		copyDtoToEntity(productDTO, entity);
		Product result = productRepository.save(entity);
		return new ProductDTO(result);
	}

	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());

	}

}

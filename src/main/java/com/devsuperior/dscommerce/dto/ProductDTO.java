package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;

	
	@NotBlank(message = "Campo requirido")
	@Size(max = 80, min = 3, message = "Nome precisa ter de 3 a 80 caracteres")
	private String name;

	@NotBlank(message = "Campo requirido")
	@Size(min = 10, message = "Descrição precisa ter no minimo 10 caracteres")
	private String description;

	@Positive(message="O preço deve ser positivo")
	private Double price;

	private String imgUrl;

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.imgUrl = product.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}

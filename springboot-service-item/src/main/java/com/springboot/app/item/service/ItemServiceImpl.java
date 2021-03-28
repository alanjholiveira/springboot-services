package com.springboot.app.item.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	private static final String urlRest = "http://service-products";
	
	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList( clientRest.getForObject(urlRest + "/list", Product[].class) );
		
		return products.stream().map( p -> new Item(p, 1) ).collect( Collectors.toList() );
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		
		Product product = clientRest.getForObject(urlRest + "/view/{id}", Product.class, pathVariables);
		
		return new Item(product, quantity);
	}

	@Override
	public Product save(Product product) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		
		ResponseEntity<Product> response = clientRest.exchange(urlRest + "/create", HttpMethod.POST, body,
				Product.class);
		Product productResponse = response.getBody();
		
		return productResponse;
	}

	@Override
	public Product update(Product product, Long id) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Product> body = new HttpEntity<Product>(product);		
		ResponseEntity<Product> response = clientRest.exchange(urlRest + "/edit/{id}", HttpMethod.PUT, body,
				Product.class, pathVariables);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		
		clientRest.delete(urlRest + "/delete/{id}", pathVariables);
	}

}

package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.entity.Pair;
import com.example.entity.Product;
import org.springframework.stereotype.Component;


@Component
public class CartModel
{

	private HashMap<Product, Integer> products;
	private double sum;

	CartModel() {
		products = new HashMap<>();
		sum = 0.0;
	}

	public void addProduct(Product product) {

		if (products.containsKey(product)) {

			Integer value = products.get(product) + 1;
			System.out.println("VALUE= " + value);
			products.put(product, value);

		} else {
			products.put(product, 1);
		}
	}

	public void removeProduct(Product product) {

		if (products.containsKey(product)) {

			if (products.get(product) == 1) {
				products.remove(product);
			} else {
				Integer value = products.get(product) - 1;
				System.out.println("VALUE= " + value);
				products.put(product, value);
			}

		} else {
			System.out.println("Blad usuwania produktu z koszyka");
		}

	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public List<Pair<Product,Integer>> getProductList(){
		List<Pair<Product,Integer>> productList = new ArrayList<>();

		products.forEach((k,v)-> {
			productList.add(new Pair<Product,Integer>(k,v));
		});

		return productList;
	}

	public List<Integer> getQuantityList(){
		List<Integer> productList = new ArrayList<>();

		products.forEach((k,v)-> {
			productList.add(v);
		});

		return productList;
	}

	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}

	// public static Cart getCartInSession(HttpServletRequest request) {
	public static CartModel getCartInSession(HttpSession session) {
		CartModel cartModel = (CartModel) session.getAttribute("myCart");

		if (cartModel == null) {
			cartModel = new CartModel();

			session.setAttribute("myCart", cartModel);
		}

		return cartModel;
	}

	public double getSum() {
		
		sum = 0.0;
		
		products.forEach((k,v) -> {
			sum += k.getPrice()*v;
		});
		
		return sum;
	}
	
	public int getQuantity() {
		return products.size();
	}
	
	public void removeCart() {
		sum = 0.0;
		products.clear();
	}
}

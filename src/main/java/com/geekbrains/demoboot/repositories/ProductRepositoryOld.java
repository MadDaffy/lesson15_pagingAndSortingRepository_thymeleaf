package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryOld {
    private List<Product> products;

//    @PostConstruct
//    public void init() {
//        products = new ArrayList<>();
//        products.add(new Product(1L, "Meat", 350));
//        products.add(new Product(2L, "Bread", 40));
//        products.add(new Product(3L, "Milk", 90));
//        products.add(new Product(4L, "Cheese", 200));
//    }

    public List<Product> findAll() {
        return products;
    }

    private Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findAny()
                .orElseThrow(() -> new RuntimeException("Элемент не найден"));
    }

    private Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findAny()
                .orElseThrow(() -> new RuntimeException("Элемент не найден"));
    }

    public Product getById(Long id) {
        return findById(id);
    }

    public void save(Product product) {
        products.add(product);
    }

    public void edit(Product product) {
        Product productFromList = getById(product.getId());
        productFromList.setTitle(product.getTitle());
        productFromList.setPrice(product.getPrice());
    }

    public void remove(Long id) {
        products.remove(getById(id));
    }

    public List<Product> findAllByTitle(String title) {
        return products.stream()
                .filter(product -> product.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}

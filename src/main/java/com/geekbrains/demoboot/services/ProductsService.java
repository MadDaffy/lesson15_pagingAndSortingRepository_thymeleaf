package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.ProductRepository;
import com.geekbrains.demoboot.repositories.ProductRepositoryOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepositoryOld productRepositoryOld;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepositoryOld productRepositoryOld) {
        this.productRepositoryOld = productRepositoryOld;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void edit(Product product) {
        productRepository.save(product);
    }

    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByTitle(String title) {
        return productRepository.findAllByTitleContaining(title);
    }

    public List<Product> getAllProductsSortedByView() {
        return productRepository.findFirst3ByOrderByViewDesc();
    }

    public void incrementProductView(Product product) {
        product.setView(product.getView() + 1);
        productRepository.save(product);
    }

    public Page<Product> getProductsWithPagingAndFiltering(Specification<Product> productSpecification, Integer pageNumber, Integer pageSize) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

}

package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.dto.ProductDto;
import com.geekbrains.demoboot.dto.filter.ProductFilter;
import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.exception.ProductNotFoundException;
import com.geekbrains.demoboot.mapper.ProductMapper;
import com.geekbrains.demoboot.repositories.ProductRepository;
import com.geekbrains.demoboot.repositories.specifications.ProductSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//TODO: используешь инъекцию через конструктор
@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    //TODO: использование DTO
    public ProductDto getById(Long id) {
        return productMapper.toDto(findById(id));
    }

    //TODO: каст не нужен. репа итак вернет в нужном типе, на основе конфигурации репозитория
    // Нужно работать с Dto объектами, а не моделями. DTO используешь для того чтобы делать манипуляции, потом маппером перегоняешь в модель и уже сохраняешь.
    // При получении принцип такой же - получил модель и сразу перегнал ее в DTO если не требуется преобразований никаких, и вернул ответ клиенту
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    //TODO: лучше назвать метод update + в реализации у тебя тоже самое что и в добавлении, т.е это не апдейт а просто добавление нового.
    // Нужно делать метод update и маппером проставлять новые значения
    public void edit(Product product) {
        productRepository.save(product);
    }

    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByTitle(String title) {
        return productRepository.findAllByTitleContaining(title);
    }

    public List<ProductDto> findByFilter(ProductFilter filter, Pageable pageable) {
        return findByFilter(new ProductSpecs(filter), pageable).map(productMapper::toDto).stream().collect(Collectors.toList());
    }

    private Page<Product> findByFilter(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                                .orElseThrow(
                                        () -> new ProductNotFoundException(String.format("Product not found with id %s", id))
                                );
    }

}

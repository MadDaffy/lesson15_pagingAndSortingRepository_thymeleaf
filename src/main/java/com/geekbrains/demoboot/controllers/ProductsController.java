package com.geekbrains.demoboot.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.geekbrains.demoboot.dto.ProductDto;
import com.geekbrains.demoboot.dto.filter.ProductFilter;
import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Operation(summary = "Получение списка продуктов с учетом фильтра")
    @PostMapping(value = "/search")
    public ResponseEntity<List<ProductDto>> search(@RequestBody ProductFilter filter,
                                                   @RequestParam(value = "pageNumber") Integer pageNumber,
                                                   @RequestParam(value = "count") Integer count) {

        return ok(productsService.findByFilter(filter, PageRequest.of(pageNumber, count)));
    }

    //    @Operation(summary = "Получение списка продуктов с учетом фильтра thymeleaf")
    //    @GetMapping(value = "/search/thymeleaf")
    //    public String showProductsList(Model model,
    //                                   @RequestBody ProductFilter filter,
    //                                   @RequestParam(value = "pageNumber") Integer pageNumber,
    //                                   @RequestParam(value = "count") Integer count) {
    //
    //        Page<ProductDto> productsPage = productsService.findByFilter(filter,  PageRequest.of(pageNumber, count));
    //        model.addAttribute("products", productsPage.getContent());
    //        model.addAttribute("title", filter.getTitle());
    //        model.addAttribute("minPrice", filter.getPriceFrom());
    //        model.addAttribute("maxPrice", filter.getPriceTo());
    //        model.addAttribute("pageTotalElements", IntStream.range(0, productsPage.getTotalPages()).toArray());
    //
    //        return "/products";
    //    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product") Product product) {
        productsService.edit(product);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productsService.removeById(id);
        return "redirect:/products";
    }

    @GetMapping("/sortByASC")
    public String sortByASC(Model model) {
        Product product1 = new Product();
        model.addAttribute("products", productsService.getAllProducts().stream()
                                                      .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                                                      .collect(Collectors.toList()));
        model.addAttribute("product", product1);
        return "/products";
    }

    @GetMapping("/sortByDESC")
    public String sortByDESC(Model model) {
        Product product1 = new Product();
        model.addAttribute("products", productsService.getAllProducts().stream()
                                                      .sorted(Comparator.comparingInt(Product::getPrice))
                                                      .collect(Collectors.toList()));
        model.addAttribute("product", product1);
        return "/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productsService.getById(id));
        return "product-page";
    }
}

package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.specifications.ProductSpecs;
import com.geekbrains.demoboot.services.ProductsService;
import com.geekbrains.demoboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Principal principal, Model model,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                   @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        if(principal != null) {
           model.addAttribute("username", principal.getName());
        }
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            --pageNumber;
        }
        Specification<Product> specification = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (title != null) {
            specification = specification.and(ProductSpecs.titleContains(title));
            filters.append("$title=" + title);
        }
        if (minPrice != null) {
            specification = specification.and(ProductSpecs.priceGreaterThanOrEq(minPrice));
            filters.append("$minPrice=" + minPrice);
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecs.priceLesserThanOrEq(maxPrice));
            filters.append("$maxPrice=" + maxPrice);
        }

        Page<Product> productsPage = productsService.getProductsWithPagingAndFiltering(specification, pageNumber, 5);
        Product product = new Product();
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("product", product);
        model.addAttribute("title", title);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("pageTotalElements", IntStream.range(0, productsPage.getTotalPages()).toArray());


        return "/products";
    }

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

//    @GetMapping("/find")
//    public String findProduct(Model model,
//                              @RequestParam(value = "title") String title,
//                              @RequestParam(value = "minPrice") int minPrice,
//                              @RequestParam(value = "maxPrice") int maxPrice) {
////        Product product = new Product();
////        model.addAttribute("products", productsService.getAllProductsByTitle(title));
////        model.addAttribute("product", product);
////        model.addAttribute("title", title);
////        System.out.printf("Min %d, Max %d", minPrice, maxPrice);
//        Specification<Product> specification = Specification.where(null);
//        StringBuilder filters = new StringBuilder();
//        if (title != null) {
//            specification = specification.and(ProductSpecs.titleContains(title));
//            filters.append("$title=" + title);
//        }
//        if (minPrice != 0) {
//            specification = specification.and(ProductSpecs.priceGreaterThanOrEq(minPrice));
//            filters.append("$minPrice=" + minPrice);
//        }
//        if (maxPrice != 0) {
//            specification = specification.and(ProductSpecs.priceLesserThanOrEq(maxPrice));
//            filters.append("$maxPrice=" + maxPrice);
//        }
//
//        Page<Product> products = productsService.getProductsWithPagingAndFiltering(0, 5, specification);
//        Product product = new Product();
//        model.addAttribute("products", products.getContent());
//        model.addAttribute("product", product);
//        model.addAttribute("title", title);
//        model.addAttribute("minPrice", minPrice);
//        model.addAttribute("maxPrice", maxPrice);
//
//
//        return "/products";
//    }

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productsService.removeById(id);
        return "redirect:/products";
    }
    @GetMapping("/auth")
    public String authUser() {
        return "redirect:/products";
    }

    @GetMapping("/sortByASC")
    public String sortByASC(Model model) {
        Product product1 = new Product();
        model.addAttribute("products", productsService.getAllProducts().stream().sorted(Comparator.comparingInt(Product::getPrice).reversed()).collect(Collectors.toList()));
        model.addAttribute("product", product1);
        return "/products";
    }

    @GetMapping("/sortByDESC")
    public String sortByDESC(Model model) {
        Product product1 = new Product();
        model.addAttribute("products", productsService.getAllProducts().stream().sorted(Comparator.comparingInt(Product::getPrice)).collect(Collectors.toList()));
        model.addAttribute("product", product1);
        return "/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        productsService.incrementProductView(product);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/showTop")
    public String showTopProduct(Model model) {
        model.addAttribute("topProducts", productsService.getAllProductsSortedByView());
        return "top-products";
    }

}

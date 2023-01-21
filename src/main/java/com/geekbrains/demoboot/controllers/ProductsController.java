package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("product", product);
        return "products";
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

    @GetMapping("/find")
    public String findProduct(Model model, @RequestParam(value = "title") String title) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProductsByTitle(title));
        model.addAttribute("product", product);
        model.addAttribute("title", title);
        return "/products";
    }

    @GetMapping("/remove/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productsService.removeById(id);
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
        model.addAttribute("product", product);
        return "product-page";
    }

}

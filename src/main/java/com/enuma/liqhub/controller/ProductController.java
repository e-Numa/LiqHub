package com.enuma.liqhub.controller;

import com.enuma.liqhub.model.ProductModel;
import com.enuma.liqhub.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductModel> products = productService.getAllProduct();
        model.addAttribute("productsRequest", products);
        return "product_list";
    }

    @PostMapping("/products")
    public String processProductForm() {
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showAddProductForm() {
        return "add_product";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin_dash";
    }

    @GetMapping("/beer")
    public String showBeerPage() {
        return "beer";
    }

    @GetMapping("/redWine")
    public String showRedWine() {
        return "redWine";
    }

    @GetMapping("/softDrink")
    public String showSoftDrink() {
        return "softDrink";
    }

    @GetMapping("/whiskey")
    public String showWhiskey() {
        return "whiskey";
    }


    @PostMapping("/add")
    public String addProduct(String name, String description, double price, int category_id, int quantity) {

        ProductModel addItems = productService.addProduct(name, description, price, category_id, quantity);
        if (addItems != null) {
            return "admin_dash";

        } else {
            return "error_page";
        }
    }

    @PostMapping("/products/{product_id}")
    public String deleteProduct(@PathVariable Long product_id) {
        productService.deleteProductById(product_id);
        return "redirect:/deleteProducts";
    }

    @GetMapping("/deleteProducts")
    public String deleteProductView(Model model) {
        List<ProductModel> products = productService.getAllProduct();
        model.addAttribute("productsRequestDelete", products);
        return "delete_product";
    }

}

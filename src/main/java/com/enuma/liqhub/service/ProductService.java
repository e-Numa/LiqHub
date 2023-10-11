package com.enuma.liqhub.service;

import com.enuma.liqhub.model.ProductModel;
import com.enuma.liqhub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProduct(){
        return productRepository.findAll();
    }



    public ProductModel addProduct(String name, String description, double price,  int category_id, int quantity) {
        ProductModel productModel = new ProductModel();

        productModel.setName(name);
        productModel.setDescription(description);
        productModel.setPrice(price);
        productModel.setCategory_id(category_id);
        productModel.setQuantity(quantity);

        return productRepository.save(productModel);
    }

    public void deleteProductById(Long product_id) {
        productRepository.deleteById(product_id);
    }

    public ProductModel getProductById(Long productId) {
        // Implement logic to retrieve the product by ID
        // You can use a ProductRepository or any other data access method
        return productRepository.findById(productId).orElse(null);
    }

}

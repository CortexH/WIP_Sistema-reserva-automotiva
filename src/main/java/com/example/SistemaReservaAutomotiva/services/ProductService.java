package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.product.Product;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.dto.input.NewProductDTO;
import com.example.SistemaReservaAutomotiva.dto.output.ProductCreatedDTO;
import com.example.SistemaReservaAutomotiva.dto.output.ReturnProductDTO;
import com.example.SistemaReservaAutomotiva.exceptions.NotFound404Exception;
import com.example.SistemaReservaAutomotiva.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    public ProductCreatedDTO registerNewProduct(NewProductDTO data, String token){
        User user = userService.getUserByToken(token);

        Product product = Product.builder()
                .name(data.name())
                .assurance_months(data.assurance_months())
                .quantity(data.quantity())
                .category(data.category())
                .description(data.description())
                .details(data.details())
                .sell_price(data.price())
                .manufacturer(data.manufacturer())
                .seller(user)
                .build();

        Product saved_product = productRepository.save(product);

        return new ProductCreatedDTO(saved_product.getId().toString());
    }

    public Product getProductById(String id){
        return productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFound404Exception("No such product with specified registry"));

    }

    public ReturnProductDTO findAndFormatProduct(String id){

        Product product = getProductById(id);

        return new ReturnProductDTO(
                product.getName(), product.getDetails(),
                product.getCategory(), product.getManufacturer(),
                product.getQuantity(), product.getSell_price(),
                product.getDescription(), product.getAssurance_months()
        );
    }

}

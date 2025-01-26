package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertType;
import com.example.SistemaReservaAutomotiva.domain.product.Product;
import com.example.SistemaReservaAutomotiva.domain.purchase.Purchase;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.dto.input.NewAlertDTO;
import com.example.SistemaReservaAutomotiva.dto.input.NewPurchaseDTO;
import com.example.SistemaReservaAutomotiva.dto.output.PurchaseCreatedDTO;
import com.example.SistemaReservaAutomotiva.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;

    // your purchase is ready for collection within 30 days

    public PurchaseCreatedDTO createNewPurchase(NewPurchaseDTO data, String token){

        Product product = productService.getProductById(data.product_registry());
        User user = userService.getUserByToken(token);

        Purchase purchase = Purchase.builder()
                .purchase_date(LocalDateTime.now())
                .product(product)
                .user(user)
                .build();

        alertService.createAlert(new NewAlertDTO(
                user, "Your purchase of product " + product.getName() +
                " has been completed! You can collect your purchase within 90 days. " +
                "After that it will be archived and you will need permission to collect it.", AlertType.INFO
        ));

        Purchase saved_purchase = purchaseRepository.save(purchase);

        return new PurchaseCreatedDTO(saved_purchase.getId().toString());
    }


}

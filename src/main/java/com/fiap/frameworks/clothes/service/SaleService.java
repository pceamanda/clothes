package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.repository.CustomerRepository;
import com.fiap.frameworks.clothes.repository.ProductRepository;
import com.fiap.frameworks.clothes.repository.SaleRepository;
import com.fiap.frameworks.clothes.request.OrderRequest;
import com.fiap.frameworks.clothes.request.ProductOrderRequest;
import com.fiap.frameworks.clothes.response.SaleResponse;
import com.fiap.frameworks.clothes.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private static final Logger LOGGER = LogManager.getLogger(SaleService.class);

    @Autowired
    private SaleRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sale(OrderRequest order) throws APIException {

        if(order.getProductOrderRequests() == null || order.getProductOrderRequests().isEmpty()){
            throw new APIException(HttpStatus.BAD_REQUEST);
        }

        CustomerEntity customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST));

        SaleEntity sale = new SaleEntity();
        sale.setDate(LocalDateTime.now());
        sale.setCustomer(customer);
        sale.setCoo(order.getCoo());
        List<SaleProductEntity> saleProducts = new ArrayList<>();

        BigDecimal fullPrice = BigDecimal.ZERO;
        for (ProductOrderRequest productOrder : order.getProductOrderRequests()) {

            ProductEntity product = productRepository.findById(productOrder.getProductId())
                    .orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST));

            SaleProductEntity saleProduct = new SaleProductEntity();
            saleProduct.setPrice(product.getPrice().multiply(BigDecimal.valueOf(productOrder.getAmount())));
            saleProduct.setProduct(product);
            saleProduct.setAmount(productOrder.getAmount());

            fullPrice = fullPrice.add(saleProduct.getPrice());

            saleProducts.add(saleProduct);
        }

        sale.setFullPrice(fullPrice);

        SaleEntity savedSale = repository.save(sale);

        String chv = sale.getId() + order.getCustomerId() + String.valueOf(sale.getFullPrice());
        String hash = Utils.generateHash(chv);
        savedSale.setHash(Utils.formatHash(hash));

        saleProducts.forEach(saleProduct -> {
            saleProduct.setSale(savedSale);
        });

        savedSale.setSaleProducts(saleProducts);
        repository.save(sale);

    }

    public void generateInvoice(final Long saleId) throws APIException {

        List<SaleEntity> sales = new ArrayList<>();

        if(saleId != null){
            sales.add(repository.findById(saleId).orElseThrow(
                    () -> new APIException(HttpStatus.BAD_REQUEST)));
        } else {
            sales.addAll(repository.findAll());
        }

        try {
            sales.stream().parallel().forEach(sale -> {
                LOGGER.info("send invoice to queue " + sale.getId());
                jmsTemplate.convertAndSend("invoice", new SaleResponse(sale));
            });
        } catch (Exception e) {
            throw new APIException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<SaleResponse> findAll() {
        List<SaleResponse> response = new ArrayList<>();
        repository.findAll().forEach(sale -> response.add(new SaleResponse(sale)));
        return response;
    }

}

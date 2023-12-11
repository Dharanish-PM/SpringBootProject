package com.quinbay.customergateway.serviceimplementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.customergateway.data.Product;
import com.quinbay.customergateway.data.Products;
import com.quinbay.customergateway.data.PublishProduct;
import com.quinbay.customergateway.data.PurchaseOrder;
import com.quinbay.customergateway.service.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service("customerGateway")
public class CustomerGatewayImplementation implements CustomerGateway {

    @Autowired
    RestTemplate restTemplate;

    private String INVENTORY_URL="http://localhost:9010/inventoryService";
    private String ORDER_SERVICE_URL="http://localhost:9045/orderService";
    private String PERSONALISE_URL="http://localhost:9055/personaliseService";



    @Override
    public List<Products> getAllProduct() {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>(headers);
        UriComponents builder=UriComponentsBuilder.fromHttpUrl(INVENTORY_URL+"/getAllProducts").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET , entity , List.class ).getBody();


    }

    @Override
    public Product getProductById(Long productId) {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>(headers);
        UriComponents builder=UriComponentsBuilder.fromHttpUrl(INVENTORY_URL+"/getProductById/"+productId).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET , entity , Product.class ).getBody();

    }


    @Override
    public String addProduct(Products product) {

        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Products> entity=new HttpEntity<>(product,headers);

        return restTemplate.exchange(INVENTORY_URL+"/addProduct", HttpMethod.POST, entity , String.class ).getBody();

    }


    @Override
    public String updateProduct(Product product) {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> entity=new HttpEntity<>(product,headers);


        String res= restTemplate.exchange(INVENTORY_URL+"/updateProduct", HttpMethod.POST, entity , String.class ).getBody();
        System.out.println(res);
        if(res.equals("Successful")){
            PublishProduct prod=new PublishProduct();
            prod.setId(product.getId());
            prod.setName(product.getName());
            prod.setPrice(product.getPrice());
            prod.setQuantity(product.getQuantity());
            prod.setResponse("Product Updation..");
            HttpHeaders headersNew=new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            System.out.println(prod);

            HttpEntity<PublishProduct> entityPublish=new HttpEntity<>(prod,headersNew);

            restTemplate.exchange(PERSONALISE_URL+"/publish", HttpMethod.POST, entityPublish, String.class ).getBody();

        }
        return res;

    }

    @Override
    public String deleteProduct(Long productId) {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Long> entity=new HttpEntity<>(productId,headers);

        return restTemplate.exchange(INVENTORY_URL+"/deleteProduct"+"/"+productId, HttpMethod.DELETE, entity , String.class ).getBody();

    }

    @Override
    public String purchaseProduct(PurchaseOrder purchaseOrder) {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PurchaseOrder> entity=new HttpEntity<>(purchaseOrder,headers);

        String orderResponse= restTemplate.exchange(ORDER_SERVICE_URL+"/purchaseProduct", HttpMethod.POST, entity , String.class ).getBody();
        return orderResponse;

    }

    @KafkaListener(topics="com.quinbay.product.create",groupId = "group-id")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        PublishProduct resProduct=objectMapper.readValue(message,PublishProduct.class);
        System.out.println("Received message in group - group-id "+resProduct);

    }

}

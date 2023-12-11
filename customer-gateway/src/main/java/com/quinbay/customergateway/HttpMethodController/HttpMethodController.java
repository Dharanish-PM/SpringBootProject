package com.quinbay.customergateway.HttpMethodController;
import com.quinbay.customergateway.data.Product;
import com.quinbay.customergateway.data.Products;
import com.quinbay.customergateway.data.PurchaseOrder;
import com.quinbay.customergateway.service.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customerGateway")
public class HttpMethodController {


    @Autowired
    CustomerGateway customerGateway;


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Products>> getAllProducts(){
        try{
            return new ResponseEntity<>(customerGateway.getAllProduct(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Products product){
            String res= customerGateway.addProduct(product);
            if(res.equals("Success")){
                return new ResponseEntity<>("Success",HttpStatus.OK);
            }
            return new ResponseEntity<>("Unsuccesful",HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        String res= customerGateway.updateProduct(product);
        if(res.equals("Successful")){

            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Unsuccesful",HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        String res= customerGateway.deleteProduct(productId);
        if(res.equals("Success")){
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Unsuccesful",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/purchaseProduct")
    public String purchaseProduct(@RequestBody PurchaseOrder purchaseOrder){
        return customerGateway.purchaseProduct(purchaseOrder);

    }





}


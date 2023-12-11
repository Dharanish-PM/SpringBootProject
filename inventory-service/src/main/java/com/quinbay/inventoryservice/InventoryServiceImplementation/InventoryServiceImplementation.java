package com.quinbay.inventoryservice.InventoryServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.inventoryservice.dao.api.CategoryDao;
import com.quinbay.inventoryservice.dao.api.ProductDao;
import com.quinbay.inventoryservice.data.PurchaseOrder;
import com.quinbay.inventoryservice.model.entity.Category;
import com.quinbay.inventoryservice.model.entity.Product;
import com.quinbay.inventoryservice.model.vo.CategoryVo;
import com.quinbay.inventoryservice.model.vo.ProductVo;
import com.quinbay.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("inventoryService")
public class InventoryServiceImplementation implements InventoryService{

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;


    @Override

    public List<CategoryVo> getAllProducts() {
        try{
            List<com.quinbay.inventoryservice.model.entity.Category> productList=categoryDao.findAll();
            ObjectMapper objectMapper=new ObjectMapper();

            List<CategoryVo> productsList= objectMapper.convertValue(productList,List.class);

            return productsList;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    @Override
    public String addProduct(CategoryVo categoryVo) {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            categoryDao.save(objectMapper.convertValue(categoryVo, com.quinbay.inventoryservice.model.entity.Category.class));

            return "Success";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Unsuccessful";
    }

    @Override
    public ProductVo getProductByName(String productName) {

        Product product=productDao.findByName(productName);

        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.convertValue(product, com.quinbay.inventoryservice.model.vo.ProductVo.class);

    }


    @Override
    public String updateProduct(ProductVo productVo) {
        Long productId=productVo.getId();
        Product product=productDao.findById(productId).get();

        if(productVo.getName().length()>0){
            product.setName(productVo.getName());
        }
        if(productVo.getPrice()>-1){
            product.setPrice((double)productVo.getPrice());
        }
        if(productVo.getQuantity()>-1){
            product.setQuantity((int)productVo.getQuantity());
        }
        try{
            productDao.save(product);

            return "Successful";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Unsucessful";



    }

    @Override
    public String deleteProduct(Long productId){

        try{
            productDao.deleteById(productId);
            return "Successful";
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return "Not deleted";

    }

    @Override
    public String purchaseProduct(PurchaseOrder purchaseOrder) {
        ObjectMapper objectMapper=new ObjectMapper();
        Product product=productDao.findById(purchaseOrder.getProductId()).get();

        if(product.getQuantity()>purchaseOrder.getProductQuantity()){
            product.setQuantity((int)product.getQuantity()-purchaseOrder.getProductQuantity());
            productDao.save(product);
            return "Item Available";
        }
        return "Item not available";



    }

    @Override
    public ProductVo getProductById(Long productId) {
        Product product=productDao.findById(productId).get();
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.convertValue(product, com.quinbay.inventoryservice.model.vo.ProductVo.class);

    }


    //    @Override
//    public CategoryVo getProductById(Long productId) {
//
//        Category response=categoryDao.findById(productId).get();
//        ObjectMapper objectMapper=new ObjectMapper();
//        return objectMapper.convertValue(response, com.quinbay.inventoryservice.model.vo.CategoryVo.class);
//
//    }

    //
//
//    @Override
//    public ResponseEntity<List<ProductVo>> getallProducts() {
//
//        try{
//            List<com.quinbay.inventoryservice.model.entity.Product> productList=productDao.findAll();
//            ObjectMapper objectMapper=new ObjectMapper();
//
//            List<ProductVo> productsList= objectMapper.convertValue(productList,List.class);
//
////        List<ProductVo> response =new ArrayList<>();
////
////        for(Product prodInstance:productsList){
////            System.out.println(prodInstance);
////            ProductVo prod=objectMapper.convertValue(prodInstance,com.quinbay.inventoryservice.model.vo.ProductVo.class);
////            response.add(prod);
////            System.out.println(prod);
////        }
////        System.out.println(response);
//            return new ResponseEntity<>(productsList,HttpStatus.OK);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public ResponseEntity<String> addProduct(ProductVo productVo) {
//        try{
//            ObjectMapper objectMapper=new ObjectMapper();
//            productDao.save(objectMapper.convertValue(productVo, com.quinbay.inventoryservice.model.entity.Product.class));
//            return new ResponseEntity<>("Success",HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Unsuccessful",HttpStatus.BAD_REQUEST);
//
//    }
//
//    @Override
//    public ResponseEntity<List<ProductVo>> getProductByCategory(int category) {
//        try{
////            List<com.quinbay.inventoryservice.model.entity.Product> productList=productDao.findByCategory(category);
////            ObjectMapper objectMapper=new ObjectMapper();
////            List<ProductVo> productsList= objectMapper.convertValue(productList,List.class);
////            return new ResponseEntity<>(productsList,HttpStatus.OK);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//    }
//
//
//


}

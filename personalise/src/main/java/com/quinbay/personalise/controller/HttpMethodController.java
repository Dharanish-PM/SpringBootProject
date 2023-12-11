package com.quinbay.personalise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.personalise.model.vo.RecommendationVo;
import com.quinbay.personalise.service.PersonaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quinbay.personalise.data.PublishProduct;

import java.util.List;

@RestController
@RequestMapping("/personaliseService")
public class HttpMethodController {

//    @GetMapping("/getCustomerOrders/{customerId}")
//    public List<Order>
      @Autowired
      PersonaliseService personaliseService;

      @PostMapping("/publish")
      public void publishMsg(@RequestBody PublishProduct prod) throws JsonProcessingException {
            personaliseService.sendMessage(prod);
      }

      @GetMapping("/getRecommendations/{customerId}")
      public List<RecommendationVo> getRecommendations(@PathVariable Long customerId ){
            return personaliseService.getRecommendations(customerId);
      }

      @PostMapping("/addRecommendation")
      public String addRecommendation(@RequestBody RecommendationVo recommendationVo){
            return personaliseService.addRecommendation(recommendationVo);

      }



}

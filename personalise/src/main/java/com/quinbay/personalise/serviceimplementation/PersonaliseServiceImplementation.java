package com.quinbay.personalise.serviceimplementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.personalise.dao.api.RecommendationsDao;
import com.quinbay.personalise.data.PublishProduct;
import com.quinbay.personalise.model.vo.RecommendationVo;
import com.quinbay.personalise.service.PersonaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;


@Service("personaliseService")
public class PersonaliseServiceImplementation implements PersonaliseService {

    @Autowired
    RecommendationsDao recommendationsDao;

    public static final String topic="com.quinbay.product.create";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void sendMessage(PublishProduct product) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        kafkaTemplate.send(topic,objectMapper.writeValueAsString(product));

    }

    @Override
    public List<RecommendationVo> getRecommendations(Long customerId) {

        try{
            List<com.quinbay.personalise.model.entity.Recommendations> productList=recommendationsDao.findAll();
            ObjectMapper objectMapper=new ObjectMapper();

            List<RecommendationVo> recommendations= objectMapper.convertValue(productList,List.class);

            return recommendations;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String addRecommendation(RecommendationVo recommendationVo) {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            recommendationsDao.save(objectMapper.convertValue(recommendationVo, com.quinbay.personalise.model.entity.Recommendations.class));
            return "Success";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Unsuccessful";
    }
}

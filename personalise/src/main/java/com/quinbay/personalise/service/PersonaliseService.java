package com.quinbay.personalise.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.personalise.data.PublishProduct;
import com.quinbay.personalise.model.vo.RecommendationVo;

import java.util.List;

public interface PersonaliseService {
    void sendMessage(PublishProduct product) throws JsonProcessingException;
    List<RecommendationVo> getRecommendations(Long customerId);
    String addRecommendation(RecommendationVo recommendationVo);

}

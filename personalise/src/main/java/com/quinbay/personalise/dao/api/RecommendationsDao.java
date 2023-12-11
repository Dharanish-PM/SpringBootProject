package com.quinbay.personalise.dao.api;

import com.quinbay.personalise.model.entity.Recommendations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationsDao extends JpaRepository<Recommendations,Long> {

    @Override
    List<Recommendations> findAll();

    @Override
    Recommendations save(Recommendations recommendations);

}

package com.example.be_study.service.user.repository;

import com.example.be_study.service.user.domain.UserMetric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface UserMetricRepository extends JpaRepository<UserMetric, Long>, UserMetricDSLRepository {
    Page<UserMetric> findAll(Pageable pageable);

    List getUserAgeGroup();

    List<UserMetric> findTop100ByOrderByUserIdDesc();
}

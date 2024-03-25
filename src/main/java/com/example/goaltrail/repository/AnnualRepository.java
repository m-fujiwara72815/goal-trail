package com.example.goaltrail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goaltrail.entity.Annual;

public interface AnnualRepository extends JpaRepository<Annual, Long>{

	Annual findByAnnualId(Long annualId);
	List<Annual> findAllByUserId(Long userId);
	List<Annual> findAllByUserIdAndYear(Long userId, int year);
	boolean existsByUserIdAndYear(Long userId, int year);
}

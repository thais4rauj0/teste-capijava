package com.capijava.capijava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capijava.capijava.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel,Long> {
	
	public List<CategoriaModel> findAllByTiposContainingIgnoreCase(String tipos);
	}



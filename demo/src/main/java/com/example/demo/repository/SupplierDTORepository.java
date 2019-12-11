package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.SupplierDTO;

@Repository
public interface SupplierDTORepository extends JpaRepository<SupplierDTO, Long>{
}
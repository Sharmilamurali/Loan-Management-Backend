package com.cts.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.loan.entity.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {

}

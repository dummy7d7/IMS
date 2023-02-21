package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.DomainCategory;

public interface IDomainCategoryDao  extends JpaRepository<DomainCategory, Integer>
{

}

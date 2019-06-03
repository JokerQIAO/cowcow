package com.cowcow.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowcow.demo.entity.ArticleEntity;

public interface ArticleDao extends JpaRepository<ArticleEntity,Long>{

}

package com.cowcow.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowcow.demo.entity.WordEntity;

public interface WordDao extends JpaRepository<WordEntity, Long> {

}

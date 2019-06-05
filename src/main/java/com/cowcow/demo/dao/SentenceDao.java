package com.cowcow.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowcow.demo.entity.SentenceEntity;

public interface SentenceDao extends JpaRepository<SentenceEntity, Long> {

}

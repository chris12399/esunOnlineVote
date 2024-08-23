package com.esun.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esun.vote.entity.VoteRecord;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {

}

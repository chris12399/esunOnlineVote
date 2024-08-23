package com.esun.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esun.vote.entity.VoteItem;

public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {

}

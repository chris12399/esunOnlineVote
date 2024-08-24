package com.esun.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esun.vote.entity.VoteItem;
import com.esun.vote.entity.VoteRecord;
import com.esun.vote.entity.Voter;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
	boolean existsByVoteItemAndVoter(VoteItem voteItem, Voter voter);
	Long countByVoteItem(VoteItem voteItem);
}

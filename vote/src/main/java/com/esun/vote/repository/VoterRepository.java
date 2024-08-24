package com.esun.vote.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.esun.vote.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Integer> {

	Voter findByVotername(String votername);

//	Optional<Voter> findByVotername(String voterName);
}

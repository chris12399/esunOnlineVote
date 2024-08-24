package com.esun.vote.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.entity.Voter;
import com.esun.vote.repository.VoterRepository;

@Service
public class VoterService {
	
	
	@Autowired
	private VoterRepository voterRepo;

	public Voter login(String json) {
		JSONObject obj = new JSONObject(json);
	    String voterName = obj.isNull("voterName") ? null : obj.getString("voterName");
	    String password = obj.isNull("password") ? null : obj.getString("password");
	    System.out.println(voterName);
	    System.out.println(password);
		Voter dbVoter = voterRepo.findByVotername(voterName);
		
		if(dbVoter.getPassword().equals(password) ) {
			return dbVoter;
		}
		return null;
	}
}

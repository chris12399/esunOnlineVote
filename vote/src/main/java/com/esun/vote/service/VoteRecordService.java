package com.esun.vote.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.entity.VoteItem;
import com.esun.vote.entity.VoteRecord;
import com.esun.vote.entity.Voter;
import com.esun.vote.repository.VoteItemRepository;
import com.esun.vote.repository.VoteRecordRepository;
import com.esun.vote.repository.VoterRepository;

@Service
public class VoteRecordService {

	
	@Autowired
	private VoteRecordRepository voteRecordRepo;

	@Autowired
	private VoterRepository voterRepo;
	
	@Autowired
	private VoteItemRepository voteItemRepo;
	
	public VoteRecord create(String json) {
		JSONObject obj = new JSONObject(json);
		
	    Integer voteItemNo = obj.isNull("voteItemNo") ? null : obj.getInt("voteItemNo");
	    Integer voterNo = obj.isNull("voterNo") ? null : obj.getInt("voterNo");
	    if (voteItemNo != null && voterNo != null) {
	        VoteItem voteItem = voteItemRepo.findById(voteItemNo).orElse(null);
	        Voter voter = voterRepo.findById(voterNo).orElse(null);
			if(voteItem!= null && voter!=null) {
				
				boolean exists = voteRecordRepo.existsByVoteItemAndVoter(voteItem, voter);
	            if (exists) {
	                // 如果已經存在，返回null或拋出異常
	                return null;
	            }
				VoteRecord newRecord = new VoteRecord();
				newRecord.setVoteItem(voteItem);
				newRecord.setVoter(voter);
				return voteRecordRepo.save(newRecord);
			}
	    }

		return null;
	}
}

package com.esun.vote.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.entity.VoteItem;
import com.esun.vote.repository.VoteItemRepository;

import jakarta.transaction.Transactional;

@Service
public class VoteItemService {

	@Autowired
	private VoteItemRepository voteItemRepo;
	
	public List<VoteItem> findAllVoteItem() {
		return voteItemRepo.findAll();
	}
	
	public VoteItem create(String json) {
		JSONObject obj = new JSONObject(json);
		String voteItemName = obj.isNull("voteItemName") ? null : obj.getString("voteItemName");
		if( voteItemName != null) {
			VoteItem newItem = new VoteItem();
			newItem.setVoteItemName(voteItemName);
			return voteItemRepo.save(newItem);
		}
		return null;
	}
	
	@Transactional
	public VoteItem modify(String json) {
		JSONObject obj = new JSONObject(json);
		Integer voteItemNo = obj.isNull("voteItemNo") ? null : obj.getInt("voteItemNo");
		Optional<VoteItem> optional = voteItemRepo.findById(voteItemNo);
		if(optional.isPresent()) {
			VoteItem update = optional.get();
			String voteItemName = obj.isNull("voteItemName") ? update.getVoteItemName() : obj.getString("voteItemName");
			update.setVoteItemName(voteItemName);
			return voteItemRepo.save(update);
		}
		
		
		return null;
	}
	
}

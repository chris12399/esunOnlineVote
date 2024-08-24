package com.esun.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.vote.dto.VoteItemDTO;
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
	
	// 獲取投票項目及其票數
    public List<VoteItemDTO> getVoteItemsWithVoteCounts() {
        List<VoteItem> voteItems = voteItemRepo.findAll();
        List<VoteItemDTO> voteItemDTOs = new ArrayList<>();

        for (VoteItem item : voteItems) {
            Long voteCount = voteRecordRepo.countByVoteItem(item);
            VoteItemDTO dto = new VoteItemDTO();
            dto.setVoteItemNo(item.getVoteItemNo());
            dto.setVoteItemName(item.getVoteItemName());
            dto.setVoteCount(voteCount);
            voteItemDTOs.add(dto);
        }

        return voteItemDTOs;
    }
    
    // 提交選中的投票項目
    public void submitVotes(List<Integer> voteItemNos, String voterName) {
        // 根據使用者名稱查找使用者
        Voter voter = voterRepo.findByVotername(voterName);


        // 遍歷選擇的投票項目
        for (Integer voteItemNo : voteItemNos) {
            VoteItem voteItem = voteItemRepo.findById(voteItemNo)
                    .orElseThrow(() -> new RuntimeException("VoteItem not found"));

            // 檢查是否已經存在該投票記錄
            boolean exists = voteRecordRepo.existsByVoteItemAndVoter(voteItem, voter);
            if (!exists) {
                // 如果不存在，則創建新的投票記錄
                VoteRecord voteRecord = new VoteRecord();
                voteRecord.setVoteItem(voteItem);
                voteRecord.setVoter(voter);
                voteRecordRepo.save(voteRecord);
            }
        }
    }
    
    public boolean hasVoted(Integer voteItemNo, String voterName) {
        Voter voter = voterRepo.findByVotername(voterName);

        VoteItem voteItem = voteItemRepo.findById(voteItemNo)
                .orElseThrow(() -> new RuntimeException("VoteItem not found"));

        return voteRecordRepo.existsByVoteItemAndVoter(voteItem, voter);
    }
    
	
	
	
	
	
	
	
}

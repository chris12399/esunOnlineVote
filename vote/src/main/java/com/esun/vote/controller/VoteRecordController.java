package com.esun.vote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.dto.VoteItemDTO;
import com.esun.vote.dto.VoteSubmissionDto;
import com.esun.vote.entity.VoteRecord;
import com.esun.vote.service.VoteRecordService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vote")
@CrossOrigin
public class VoteRecordController {
	
	@Autowired
	private VoteRecordService voteRecordService;

	@PostMapping("/user/create")
	public String userCreate(@RequestBody String body) {
		JSONObject responseBody = new JSONObject();
		
		VoteRecord voteRecord = voteRecordService.create(body);
		if (voteRecord == null) {
			responseBody.put("success", false);
			responseBody.put("message", "新增失敗，該投票記錄已經存在或資料無效");
		} else {
			responseBody.put("success", true);
			responseBody.put("message", "新增成功");
		}

		return responseBody.toString();
	}
	
    @GetMapping("/itemsWithCounts")
    public List<VoteItemDTO> getVoteItemsWithCounts() {
        return voteRecordService.getVoteItemsWithVoteCounts();
    }
    
    @PostMapping("/submitVotes")
    public ResponseEntity<Map<String, Object>> submitVotes(@RequestBody VoteSubmissionDto submissionDto) {
        Map<String, Object> response = new HashMap<>();
        boolean hasExistingVote = false;
        
        try {
            for (Integer voteItemNo : submissionDto.getVoteItemNos()) {
                if (voteRecordService.hasVoted(voteItemNo, submissionDto.getVoterName())) {
                    hasExistingVote = true;
                    break;
                }
            }
            if(hasExistingVote) {
                response.put("success", false);
                response.put("message", "已經投過該投票");
            } else {
                // 使用者名稱從 submissionDto 獲取
                String voterName = submissionDto.getVoterName();
                List<Integer> voteItemNos = submissionDto.getVoteItemNos();
                voteRecordService.submitVotes(voteItemNos, voterName);
                response.put("success", true);
                response.put("message", "投票提交成功");
            }
           
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "投票提交失敗");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    
    
    
    
}

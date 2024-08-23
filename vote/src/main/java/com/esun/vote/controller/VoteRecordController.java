package com.esun.vote.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}

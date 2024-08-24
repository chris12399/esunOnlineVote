package com.esun.vote.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.entity.Voter;
import com.esun.vote.service.VoterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vote")
@CrossOrigin
public class VoterController {

	
	@Autowired
	private VoterService voterService;
	
	@PostMapping("/login")
	public String login(@RequestBody String body) {
		JSONObject responseBody = new JSONObject();
		
		Voter voter = voterService.login(body);
		if (voter == null) {
			responseBody.put("success", false);
			responseBody.put("message", "登入失敗");
		} else {
			responseBody.put("success", true);
			responseBody.put("message", "登入成功");
		}
		
		return responseBody.toString();
	}
	
}

package com.esun.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.vote.entity.VoteItem;
import com.esun.vote.service.VoteItemService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/vote")
@CrossOrigin
public class VoteItemController {

	@Autowired
	private VoteItemService voteItemService;

	@GetMapping("/findAll")
	public String findAll() {
		JSONObject responseBody = new JSONObject();
		JSONArray array = new JSONArray();
		List<VoteItem> voteItems = voteItemService.findAllVoteItem();
		if (voteItems != null && !voteItems.isEmpty()) {
			for (VoteItem voteItem : voteItems) {
				JSONObject item = new JSONObject()
						.put("voteItemNo", voteItem.getVoteItemNo())
						.put("voteItemName",voteItem.getVoteItemName());
				array = array.put(item);
			}
		}
		responseBody.put("list", array);
		return responseBody.toString();
	}

	@PostMapping("/create")
	public String create(@RequestBody String body) {
		JSONObject responseBody = new JSONObject();

		VoteItem voteItem = voteItemService.create(body);
		if (voteItem == null) {
			responseBody.put("success", false);
			responseBody.put("message", "新增失敗");
		} else {
			responseBody.put("success", true);
			responseBody.put("message", "新增成功");
		}

		return responseBody.toString();
	}

	@PutMapping("/modify")
	public String modify(@RequestBody String body) {
		JSONObject responseBody = new JSONObject();
		VoteItem modify = voteItemService.modify(body);
		if(modify == null) {
			responseBody.put("success", false);
			responseBody.put("message", "修改失敗");
		} else {
			responseBody.put("success", true);
			responseBody.put("message", "修改成功");
		}

		return responseBody.toString();
	}

}

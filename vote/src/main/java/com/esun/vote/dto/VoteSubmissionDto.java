package com.esun.vote.dto;

import java.util.List;

public class VoteSubmissionDto {
    private String voterName;
    private List<Integer> voteItemNos;
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public List<Integer> getVoteItemNos() {
		return voteItemNos;
	}
	public void setVoteItemNos(List<Integer> voteItemNos) {
		this.voteItemNos = voteItemNos;
	}
    
    
}

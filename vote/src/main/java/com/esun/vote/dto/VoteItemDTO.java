package com.esun.vote.dto;

public class VoteItemDTO {
    private Integer voteItemNo;
    private String voteItemName;
    private Long voteCount;

    // Getters and setters
    public Integer getVoteItemNo() {
        return voteItemNo;
    }

    public void setVoteItemNo(Integer voteItemNo) {
        this.voteItemNo = voteItemNo;
    }

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(String voteItemName) {
        this.voteItemName = voteItemName;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}


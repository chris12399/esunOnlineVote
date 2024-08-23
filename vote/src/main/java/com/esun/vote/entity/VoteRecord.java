package com.esun.vote.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VoteRecord")
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteRecordNo;
    
    @ManyToOne
    @JoinColumn(name = "voter", nullable = false)
    private Voter voter;
    
    @ManyToOne
    @JoinColumn(name = "voteItem",nullable = false)
    private VoteItem voteItem;
}

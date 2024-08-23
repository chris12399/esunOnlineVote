package com.esun.vote.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VoteItem")
public class VoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voteItemNo")
    private Integer voteItemNo;
    
    @Column(name = "voteItemName", nullable = false)
    private String voteItemName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voteItem")
    private List<VoteRecord> voteRecords;
}

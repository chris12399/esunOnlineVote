package com.esun.vote.entity;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Voter")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voterNo")
    private Integer voterNo;
    
    @Column(name = "votername", nullable = false)
    private String votername;
    
    @Column(name = "isAdmin")
    private Boolean isAdmin;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voter")
    private List<VoteRecord> voteRecords;
    
    @PrePersist
    protected void onCreate() {
        if (isAdmin == null) {
            isAdmin = false;  // 如果 isAdmin 為 null，預設為 false
        }
    }
}

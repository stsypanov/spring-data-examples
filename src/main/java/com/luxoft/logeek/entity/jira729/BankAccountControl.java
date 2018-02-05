package com.luxoft.logeek.entity.jira729;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BankAccountControl {
    @Id
    private long id;
 
    @ManyToOne
    @JoinColumn(name ="uac_id", nullable = false)
    private UserAccount userAccount;
 

}
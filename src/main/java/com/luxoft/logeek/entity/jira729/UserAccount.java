package com.luxoft.logeek.entity.jira729;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccount {

    @Id
    @Column(name = "uac_id")
    private long id;

}
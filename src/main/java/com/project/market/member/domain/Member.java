package com.project.market.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	@Id
	@Column(name = "member_id")
	private String id;
	private String name;
	private String password;
}

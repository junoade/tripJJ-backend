package com.trip.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
	
	private String userId;
	private String userName;
	private String userPass;
	private String userEmail;
	private String joinDate;
	
}

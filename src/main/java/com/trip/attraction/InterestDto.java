package com.trip.attraction;

import java.util.List;

import lombok.*;

// 관심 관광지 정보
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterestDto {
	
	String userId;
	List<Integer> addInterests;
	List<Integer> delInterests;
	
}

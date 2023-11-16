package com.trip.attraction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SidoGugunDto {	// 관광지 검색 조건
	
	private String sidoCode;
	private String sidoName;
	
	private String gugunCode;
	private String gugunName;

}

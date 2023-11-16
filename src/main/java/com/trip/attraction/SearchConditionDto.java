package com.trip.attraction;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchConditionDto {
	
	private String sidoCode;
	private String gugunCode;
	private String contentTypeId;
	private String title;

}

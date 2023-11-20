package com.trip.attraction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionInfoDto {

	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String firstImage;
	private String overview;
	
//	위치
	private double latitude;
	private double longitude;

}

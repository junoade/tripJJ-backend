package com.trip.attraction.dto;

import com.trip.attraction.AttractionInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AttractionInfo_Kakao {
	  private Integer content_id;
      private String title;
      private String addr1; // KAKAO API의 도로명 주소(road_address_name)와 DB의 addr1이 맵핑됨
      private Integer readcount;
      
      private Double latitude; // KAKAO API의 y좌표(위도)
      private Double longitude; // KAKAO API의 x좌표(경도)
      
      //  카카오맵 API의 고유 정보
      private String phone;
      private String categoryGroupCode;
      private String categoryGroupName;
      private String categoryName;
      private String placeUrl;
}

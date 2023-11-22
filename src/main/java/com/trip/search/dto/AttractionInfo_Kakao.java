package com.trip.search.dto;

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
public class AttractionInfo_Kakao extends AttractionInfo {
      //  카카오맵 API의 고유 정보
      private String phone;
      private Integer readCount;
      private String categoryGroupCode;
      private String categoryGroupName;
      private String categoryName;
      private String placeUrl;
}

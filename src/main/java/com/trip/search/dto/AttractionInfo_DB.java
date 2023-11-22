package com.trip.search.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AttractionInfo_DB extends AttractionInfo{
    private Integer contentTypeId;
    private Integer readCount;
    private String addr2;
    private String firstImage;
    private String firstImage2;
    private String overview;
}

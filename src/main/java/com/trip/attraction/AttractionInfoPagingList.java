package com.trip.attraction;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionInfoPagingList {
	
	private List<AttractionInfoDto> attractions;
	private int currentPage;
	private int totalPage;
	
}

package com.trip.qna;

import lombok.*;
import java.util.*;

@Getter
@Setter
public class BoardQnaPagingList {

	private List<BoardQnaDto> articles;
	private int currentPage;
	private int totalPageCount;
	
}

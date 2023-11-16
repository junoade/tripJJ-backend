package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.AttractionInfoPagingList;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.dao.AttractionDao;

@Service
public class AttractionServiceImpl implements AttractionService{

	@Autowired
    private AttractionDao dao;
    
    @Override
    public AttractionInfoPagingList listAttraction(Map<String, Object> param) throws SQLException {
    	Map<String, Object> map = new HashMap();
    	String sidoCode = (String)param.getOrDefault("sidoCode", "");
    	String gugunCode = (String)param.getOrDefault("gugunCode", "");
    	String contentTypeId = (String)param.getOrDefault("contentTypeId", "");
    	String title = (String)param.getOrDefault("title", "");
    	int currentPage = Integer.parseInt((String) param.getOrDefault("pgno", "1"));
		int sizePerPage = Integer.parseInt((String) param.getOrDefault("spp", "10"));
		int start = currentPage * sizePerPage - sizePerPage;
    	
    	map.put("sidoCode", sidoCode);
    	map.put("gugunCode", gugunCode);
    	map.put("contentTypeId", contentTypeId);
    	map.put("title", title);
    	map.put("start", start);
    	map.put("listsize",sizePerPage);
    	
    	List<AttractionInfoDto> attractions = dao.listAttraction(map);
        int totalAttractionCount = dao.getTotalAttractionCount(param);
        int	totalPageCount = (totalAttractionCount - 1) / sizePerPage + 1;
        
        AttractionInfoPagingList attractionInfoPagingList = new AttractionInfoPagingList();
        attractionInfoPagingList.setAttractions(attractions);
        attractionInfoPagingList.setTotalPage(totalPageCount);
        attractionInfoPagingList.setCurrentPage(currentPage);
        
        return attractionInfoPagingList;
    }
    
    @Override
    public int saveHotplace(HotplaceDto dto) {
    	return dao.saveHotplace(dto);
    }
    
    @Override
    public List<HotplaceDto> hotPlaceList(String userId) {
    	// TODO Auto-generated method stub
    	return dao.hotPlaceList(userId);
    }

    @Override
    public int deleteHotPlace(int placeNo) {
        return dao.deleteHotPlace(placeNo);
    }

    @Override
    public HotplaceDto select(int placeNo) throws SQLException {
        return dao.select(placeNo);
    }
    
}

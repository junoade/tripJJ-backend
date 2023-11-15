package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.attraction.AttractionInfoDto;
import com.trip.attraction.HotplaceDto;
import com.trip.attraction.dao.AttractionDao;

@Service
public class AttractionServiceImpl implements AttractionService{

	@Autowired
    private AttractionDao dao;
    
    @Override
    public List<AttractionInfoDto> listAttraction(Map<String, String> param) throws SQLException {
        return dao.listAttraction(param);
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

package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.attraction.SidoGugunDto;
import com.trip.attraction.dao.SidoGugunDao;

@Service
public class SidoGugunServiceImpl implements SidoGugunService {
	
	@Autowired
	private SidoGugunDao dao;
	
	
	@Override
	public List<SidoGugunDto> getSido() throws SQLException {
		return dao.getSido();
	}

	@Override
	public List<SidoGugunDto> getGugun(String sidoCode) throws SQLException {
		return dao.getGugun(sidoCode);
	}

}

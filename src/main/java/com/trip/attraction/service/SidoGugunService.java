package com.trip.attraction.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.attraction.SidoGugunDto;

public interface SidoGugunService {

	List<SidoGugunDto> getSido() throws SQLException;
	List<SidoGugunDto> getGugun(String sidoCode) throws SQLException;
	
}

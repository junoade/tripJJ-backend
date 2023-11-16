package com.trip.attraction.dao;

import java.sql.SQLException;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;

import com.trip.attraction.SidoGugunDto;

@Mapper
public interface SidoGugunDao {
	
	List<SidoGugunDto> getSido() throws SQLException;
	List<SidoGugunDto> getGugun(String sidoCode) throws SQLException;
	
}

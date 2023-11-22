package com.trip.search.service;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.search.dto.AttractionInfo;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface AttractionFactory {
    AttractionInfo createAttractionInfo(Map<String, Object> area) throws SQLException, InvalidPlaceException;
}

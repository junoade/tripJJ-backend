package com.trip.search.service;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.search.dto.AttractionInfo;
import com.trip.search.dto.AttractionInfo_DB;
import com.trip.search.dto.AttractionInfo_Kakao;
import com.trip.search.mapper.PlaceSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttractionFactoryImpl implements AttractionFactory {

    private final PlaceSearchMapper placeSearchMapper;

    @Transactional
    @Override
    public AttractionInfo createAttractionInfo(Map<String, Object> area) throws SQLException, InvalidPlaceException {
        String road_address_name = (String)area.get("road_address_name");
        String place_name = (String)area.get("place_name");
        validateAddressInfo(road_address_name, place_name);


        Optional<AttractionInfo_DB> infoDb = placeSearchMapper.findAttractionByAddress(road_address_name, place_name);
        if(infoDb.isPresent()) {
            return infoDb.get();
        }

        placeSearchMapper.insertKakaoAttraction(area);
        Optional<AttractionInfo_Kakao> infoKakao = placeSearchMapper.selectKakaoAttraction(road_address_name, place_name);
        if(infoKakao.isPresent()) {
            return infoKakao.get();
        } else {
            throw new InvalidPlaceException();
        }
    }

    private void validateAddressInfo(String road_address_name, String place_name) throws InvalidPlaceException{
        if(road_address_name.equals("") || place_name.equals("")) {
            throw new InvalidPlaceException();
        }
    }
}

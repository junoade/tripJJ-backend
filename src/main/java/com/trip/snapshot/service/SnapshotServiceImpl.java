package com.trip.snapshot.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trip.search.mapper.PlaceSearchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.Snapshot;
import com.trip.snapshot.mapper.SnapshotMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotMapper snapshotMapper;
    private final PlaceSearchMapper placeSearchMapper;

    // 파일 업로드 관련 Mapper 필요


    @Transactional
    @Override
    public int uploadSnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException {

        String road_address_name = (String) area.get("road_address_name");
        String place_name = (String) area.get("place_name");
        validateAddressInfo(road_address_name, place_name);

        int contentId = placeSearchMapper.findAttractionIdByAddress(road_address_name, place_name);
        if (contentId == 0) {
            throw new InvalidPlaceException();
        }

        snapshot.setContentId(contentId);

        return snapshotMapper.insertSnapshot(snapshot);
    }

    private void validateAddressInfo(String road_address_name, String place_name) throws InvalidPlaceException {
        if (road_address_name.equals("") || place_name.equals("")) {
            throw new InvalidPlaceException();
        }
    }

    @Override
    public int modifySnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteSnapshot(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public List<Snapshot> getSnapshotList() throws SQLException {
    	return snapshotMapper.findSnapshotList();
    }

    @Override
    public List<Snapshot> getSnapshotByUserId(String userId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Snapshot> getSnapshotBySnapshotId(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }


}

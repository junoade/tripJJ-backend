package com.trip.snapshot.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.Snapshot;

public interface SnapshotService {
	
	int uploadSnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException;
	int modifySnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException;
	int deleteSnapshot(Integer id) throws SQLException;
	
	List<Snapshot> getSnapshotList() throws SQLException;
	List<Snapshot> getSnapshotByUserId(String userId) throws SQLException;
	Optional<Snapshot> getSnapshotBySnapshotId(Integer id) throws SQLException;
	
}

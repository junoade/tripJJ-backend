package com.trip.snapshot.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.Snapshot;
import org.springframework.web.multipart.MultipartFile;

public interface SnapshotService {
	
	int uploadSnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException;
	int uploadSnapshot(Snapshot snapshot, Map<String, Object> area, List<MultipartFile> files) throws SQLException, InvalidPlaceException, IOException;
	int modifySnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException;
	int deleteSnapshot(Integer id) throws SQLException;
	
	List<Snapshot> getSnapshotList() throws SQLException;
	List<Snapshot> getSnapshotByUserId(String userId) throws SQLException;
	Optional<Snapshot> getSnapshotBySnapshotId(Integer id) throws SQLException;

	int saveFiles(Integer snapshotId, List<MultipartFile> files) throws IOException;
	
}

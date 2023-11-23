package com.trip.snapshot.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.trip.snapshot.dto.Snapshot;

@Mapper
public interface SnapshotMapper {
	int insertSnapshot(Snapshot snapshot) throws SQLException;
	int modifySnapshot(Snapshot snapshot) throws SQLException;
	int deleteSnapshot(Integer no) throws SQLException;
	
	List<Snapshot> findSnapshotList() throws SQLException;
	List<Snapshot> findSnapshotByUserId(String userId) throws SQLException;
	Optional<Snapshot> findSnapshotBySnapshotId(Integer id) throws SQLException;
}

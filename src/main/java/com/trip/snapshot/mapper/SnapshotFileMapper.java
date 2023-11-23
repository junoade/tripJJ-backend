package com.trip.snapshot.mapper;

import com.trip.snapshot.dto.SnapFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface SnapshotFileMapper {
    int insertSnapshotFiles(Integer snapId, Integer contentId, List<MultipartFile> files);
    List<SnapFile> findBySnapId(Integer snapId);
}

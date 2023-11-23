package com.trip.snapshot.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.trip.search.mapper.PlaceSearchMapper;
import com.trip.snapshot.dto.SnapFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.Snapshot;
import com.trip.snapshot.mapper.SnapshotMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotMapper snapshotMapper;
    private final PlaceSearchMapper placeSearchMapper;

    // private final SnapshotFileMapper snapshotFileMapper;

    @Value("${upload.path}")
    private String USER_DIR;

    // 파일 업로드 관련 Mapper 필요


    @Transactional
    @Override
    public int uploadSnapshot(Snapshot snapshot, Map<String, Object> area) throws SQLException, InvalidPlaceException {

        String address = getRoadAddressOrAddress(area);
        String place_name = (String) area.get("place_name");
        validateAddressInfo(address, place_name);
        log.debug("road_address_name : {}, place_name : {}", address, place_name);

        int contentId = placeSearchMapper.findAttractionIdByAddress(address, place_name);
        if (contentId == 0) {
            throw new InvalidPlaceException();
        }

        snapshot.setContentId(contentId);

        return snapshotMapper.insertSnapshot(snapshot);
    }

    @Transactional
    @Override
    public int uploadSnapshot(Snapshot snapshot, Map<String, Object> area, List<MultipartFile> files) throws SQLException, InvalidPlaceException, IOException {
        String address = getRoadAddressOrAddress(area);
        String place_name = (String) area.get("place_name");
        validateAddressInfo(address, place_name);
        log.debug("road_address_name : {}, place_name : {}", address, place_name);

        int contentId = placeSearchMapper.findAttractionIdByAddress(address, place_name);
        if (contentId == 0) {
            throw new InvalidPlaceException();
        }

        snapshot.setContentId(contentId);

        // snapShotId로 파일 저장
        snapshotMapper.insertSnapshot(snapshot);
        // Integer snapId = snapshotMapper.getLastInsertId();
        // log.debug(String.valueOf(snapId));
        log.debug("after insert : {}", snapshot);
        saveFiles(snapshot.getId(), files);
        return 1;
    }

    @Override
    public int saveFiles(Integer snapshotId, List<MultipartFile> files) throws IOException {

        if(files.isEmpty()) {
            return 0;
        }

        makeUploadDirIfNotExists();
        log.debug("saveFiles : {}", snapshotId);
        List<SnapFile> list = new ArrayList<>();
        for (MultipartFile file : files) {
            log.debug("클라이언트의 업로드 이미지 정보 : {} {} {}", file.getName(), file.getOriginalFilename(), file.getResource());
            SnapFile dto = SnapFile.builder()
                    .originalFilename(file.getOriginalFilename())
                    .originalExtension(file.getContentType())
                    .size(file.getSize()).build();

            log.debug("==> converted : SnapFile dto : {}", dto);

            dto.setSnapId(snapshotId);
            dto.setStoredFilename(getUniqueFilename() + "."+splitExtension(dto.getOriginalExtension()));
            dto.setStorePathPrefix(USER_DIR);

            log.debug("==> save : SnapFile dto : {}", dto);
            list.add(dto);

            log.debug("==> save : File on server");
            file.transferTo(new File(USER_DIR + dto.getStoredFilename()));
        }

        log.debug(list.toString());
        // INSERT 호출
        return snapshotMapper.insertSnapshotFiles(snapshotId, list);
    }

    private String getUniqueFilename() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return currentTime.format(formatter);
    }

    private String splitExtension(String originalExtension) {
        return originalExtension.split("/")[1];
    }

    private void makeUploadDirIfNotExists() {
        File dir = new File(USER_DIR);
        if (!dir.exists()) {
            dir.mkdir();
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
        return snapshotMapper.findSnapshotByUserId(userId);
    }

    @Override
    public Optional<Snapshot> getSnapshotBySnapshotId(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SnapFile> getAllGroupRepresentImage() throws SQLException {
        return snapshotMapper.getAllGroupRepresentImage();
    }

    @Override
    public SnapFile getGroupRepresentImage(String snapId) throws SQLException {
        return snapshotMapper.getGroupRepresentImage(snapId);
    }

    private void validateAddressInfo(String road_address_name, String place_name) throws InvalidPlaceException {
        if (road_address_name.equals("") || place_name.equals("")) {
            throw new InvalidPlaceException();
        }
    }

    private String getRoadAddressOrAddress(Map<String, Object> area) {
        String result = (String) area.get("road_address_name");
        if (result == null || result.equals("")) {
            result = (String) area.get("address_name");
        }
        return result;
    }
}

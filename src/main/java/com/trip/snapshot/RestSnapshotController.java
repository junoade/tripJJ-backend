
package com.trip.snapshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.trip.exceptions.InvalidPlaceException;
import com.trip.snapshot.dto.KakaoApiArea;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trip.snapshot.dto.Snapshot;
import com.trip.snapshot.service.SnapshotService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/snapshot")
@RequiredArgsConstructor
public class RestSnapshotController {

	private final SnapshotService service;

	@Value("${upload.path}")
	private String USER_DIR;
	
	
	@PostMapping
	public ResponseEntity<?> uploadStory(@RequestPart("snapshot") Snapshot snapshot,
										 @RequestPart("area") Map<String, Object> area,
										 @RequestPart("multipartFile") List<MultipartFile> files) throws IOException{
		
		log.debug("uploadStory 호출");
		log.debug("snapshot : {}, area : {}", snapshot, area);
		
//		String uploadDir = "C:/SSAFY/trip_jj/triprest/images/" ;
		
		for(MultipartFile file : files) {
			log.debug("업로드 이미지 : {} {} {}", file.getName(), file.getOriginalFilename(), file.getResource());
			file.transferTo(new File(USER_DIR + file.getOriginalFilename()));
		}
		
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			service.uploadSnapshot(snapshot, area);
			status = HttpStatus.OK;
		} catch (InvalidPlaceException e) {
			status = HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		
		return new ResponseEntity<>(status);
	}
	
}

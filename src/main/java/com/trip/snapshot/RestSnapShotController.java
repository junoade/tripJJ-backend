
package com.trip.snapshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.trip.snapshot.dto.KakaoApiArea;
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

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/snapshot")
public class RestSnapShotController {

	@PostMapping
	public ResponseEntity<?> uploadStory(@RequestPart("snapshot") Snapshot snapshot,
										 @RequestPart("area") Map<String, Object> area,
										 @RequestPart("multipartFile") List<MultipartFile> files) throws IOException{
		
		log.debug("uploadStory 호출");
		log.debug("snapshot : {}, area : {}", snapshot, area);
		for(MultipartFile file : files) {
			log.debug("업로드 이미지 : {} {} {}", file.getName(), file.getOriginalFilename(), file.getResource());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

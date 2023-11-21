
package com.trip.snapshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
	public ResponseEntity<?> uploadStory(@RequestPart("snapshot") Snapshot snapshot, @RequestPart("multipleFiles") List<MultipartFile> files) throws IOException{
		
		log.debug("uploadStory 호출");
		log.debug("스냅샷 : {}", snapshot);
		for(MultipartFile file : files) {
			log.debug("업로드 이미지 : {} {} {}", file.getName(), file.getOriginalFilename(), file.getResource());
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

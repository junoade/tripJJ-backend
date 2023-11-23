
package com.trip.snapshot;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.trip.exceptions.InvalidPlaceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private final SnapshotService snapshotService;

	@Value("${upload.path}")
	private String USER_DIR;
	
	
	@PostMapping
	public ResponseEntity<?> uploadStory(@RequestPart("snapshot") Snapshot snapshot,
										 @RequestPart("area") Map<String, Object> area,
										 @RequestPart("multipartFile") List<MultipartFile> files) throws IOException{
		
		log.debug("uploadStory 호출");
		log.debug("snapshot : {}, area : {}", snapshot, area);

		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			snapshotService.uploadSnapshot(snapshot, area, files);
			status = HttpStatus.OK;
		} catch (InvalidPlaceException e) {
			status = HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		
		return new ResponseEntity<>(status);
	}
	
	private void checkUploadDir() {
		File dir = new File(USER_DIR);
		if(!dir.exists()) {
			dir.mkdir();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getStories() throws SQLException {
		log.debug("getStories 호출");
		List<Snapshot> list = snapshotService.getSnapshotList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserStories(@PathVariable String userId) throws SQLException {
		log.debug("getStories 호출");
		List<Snapshot> list = snapshotService.getSnapshotByUserId(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/fileTest", produces= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> getFiles() {
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

		formData.add("first_name",  "ganesh");
		formData.add("last_name", "patil");
		formData.add("file-data_1", new FileSystemResource("C:/springrest-images/seoul_skyline.jpg"));
//		formData.add("file-data_2", new FileSystemResource("C:\Users\ganeshg\Desktop\download.jpg"));
//		formData.add("file-data_3", new FileSystemResource("C:\Users\ganeshg\Desktop\odstext.txt"));
//		formData.add("file-data_4", new FileSystemResource("D:\Agent\152845.docx"));
//		formData.add("file-data_5", new FileSystemResource("D:\testxls.xlsx"));
		return new ResponseEntity<>(formData, HttpStatus.OK);
	}
}

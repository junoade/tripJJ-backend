package com.trip.snapshot.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class FileServiceImpl implements FileService {

	@Value("${upload.path}")
	private String USER_DIR;
	
	@Override
	public void loadFiles(MultiValueMap<String, Object> formData, List<String> filePath) {
		
	}

	
}

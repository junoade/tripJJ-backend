package com.trip.snapshot.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

public interface FileService {
	void loadFiles(MultiValueMap<String, Object> formData, List<String> filePath);
}

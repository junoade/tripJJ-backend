package com.trip.snapshot.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SanapFileUpload {
	private Integer snapId;
	private List<MultipartFile> files;
}

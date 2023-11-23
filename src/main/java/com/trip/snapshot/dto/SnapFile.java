package com.trip.snapshot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SnapFile {
	private Integer id;
	private Integer snapId;
	private String originalFilename; 
	private String originalExtension;
	private String storedFilename;
	private String storePathPrefix;
	private Long size;
	private String createdDate;

	@Builder
	public SnapFile(String originalFilename, String originalExtension, Long size) {
		this.originalFilename = originalFilename;
		this.originalExtension = originalExtension;
		this.size = size;
	}
}

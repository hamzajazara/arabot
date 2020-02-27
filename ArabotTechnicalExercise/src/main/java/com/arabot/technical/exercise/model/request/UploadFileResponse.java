package com.arabot.technical.exercise.model.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Upload File Response")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {

	private String fileName;

	private String fileType;

	private long size;
}

package com.lex.controller;

import com.lex.model.ImageInfo;
import com.lex.service.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : Lex Yu
 */
@Slf4j
@RestController
public class DownloadController {

	private final RestClient restClient;

	@Autowired
	public DownloadController(RestClient restClient) {
		this.restClient = restClient;
	}

	@GetMapping("/download/image")
	public Mono<ResponseEntity<ImageInfo>> download_image_from_url(String url) throws IOException {
		return restClient
				.downloadImage(url)
				.map(imageInfo -> ResponseEntity
						.status(HttpStatus.OK)
						.body(imageInfo))
				.onErrorResume(err-> {
					log.error("=====================================================================================>");
					log.error("| Error = {}", err.getMessage());
					for (StackTraceElement ste : err.getStackTrace()) {
						log.error("| {}", ste.toString());
					}
					return Mono.just(ResponseEntity
							.status(HttpStatus.BAD_REQUEST)
							.body(ImageInfo.builder().msg(HttpStatus.BAD_REQUEST.getReasonPhrase()).build()));
				});
	}
}

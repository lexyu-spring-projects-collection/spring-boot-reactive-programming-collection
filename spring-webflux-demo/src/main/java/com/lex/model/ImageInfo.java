package com.lex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lex Yu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
	private String name;
	private Long size;
	private String msg;
}
